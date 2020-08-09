package me.dev.oliver.youtubesns.dto;

import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import me.dev.oliver.youtubesns.security.SecurityUtil;
import org.hibernate.validator.constraints.Length;

@Getter
public class UserDto {
  /** @NotBlank : null을 허용하지 않음, 적어도 white-space가 아닌 문자가 한개 이상 포함되어야 함 - null과 빈 공백 문자열(" ")을 허용하지 않음
   * @NotEmpty : null과 공백 문자열("") 을 허용하지 않음
   * @Pattern : 정규표현식에 맞는 문자열이어야 함
   * @Email : 이메일 양식이어야 함
   */

  private int id;

  @NotEmpty(message = "아이디는 필수 입니다")
  private String userId;

  /** (?=.*[0-9]) - 숫자는 적어도 하나, (?=.*[a-zA-Z]) - 영문 대,소문자중적어도 하나
   * (?=.*\\W) - 특수문자 적어도 하나, (?=\\S+$) - 공백제거
   */
  @NotBlank(message = "비밀번호는 필수 입니다")
  @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})", message = "비밀 번호는 8~20자리로 숫자와 특수 문자가 포함된 영문 대소문자로 입력해 주세요")
  private String pw;

  @NotEmpty(message = "이름은 필수 입니다")
  private String name;

  @NotEmpty(message = "이메일은 필수 입니다")
  @Email(message = "이메일 형식으로 입력해 주세요")
  private String email;

  @NotEmpty(message = "주소는 필수 입니다")
  private String addr;

  private Date signupDate;

  @NotEmpty(message = "핸드폰 번호는 필수 입니다")
  @Length(min = 1, max = 10, message = "전화 번호는 '-'를 제외하고 10자리 이하로 입력해 주세요")
  private String phone;

  public UserDto() {
  }

  // 회원가입시 사용할 생성자
  public UserDto(String userId, String pw, String name, String email, String addr, String phone) throws Exception {
    this.userId = userId;
    SecurityUtil securityUtil = new SecurityUtil();
    this.pw = securityUtil.encryptSha256(pw);
    this.name = name;
    this.email = email;
    this.addr = addr;
    this.phone = phone;
  }

  // 회원탈퇴를 위한 생성자, pw 변경을 위한 생성자
  public UserDto(String userId, String pw) throws Exception {
    this.userId = userId;
    SecurityUtil securityUtil = new SecurityUtil();
    this.pw = securityUtil.encryptSha256(pw);
  }

  // pw 변경을 위한 생성자
  public UserDto(int id,
      @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})", message = "비밀 번호는 8~20자리로 숫자와 특수 문자가 포함된 영문 대소문자로 입력해 주세요")
      String pw) throws Exception {
    this.id = id;
    SecurityUtil securityUtil = new SecurityUtil();
    this.pw = securityUtil.encryptSha256(pw);
  }

}

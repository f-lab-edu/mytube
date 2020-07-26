package me.dev.oliver.youtubesns.dto;

import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
public class UserDto {
  // @NotBlank : null을 허용하지 않음, 적어도 white-space가 아닌 문자가 한개 이상 포함되어야 함 - null과 빈 공백 문자열(" ")을 허용하지 않음
  // @NotEmpty : null과 공백 문자열("") 을 허용하지 않음
  // @Pattern : 정규표현식에 맞는 문자열이어야 함
  // @Email : 이메일 양식이어야 함
  // 아이디
  private Integer id;

  // 유저 아이디
  @NotEmpty(message = "아이디는 필수 입니다")
  private String user_id;

  // 비밀번호
  @NotBlank(message = "비밀번호는 필수 입니다")
  // (?=.*[0-9]) - 숫자는 적어도 하나, (?=.*[a-zA-Z]) - 영문 대,소문자중적어도 하나
  // (?=.*\\W) - 특수문자 적어도 하나, (?=\\S+$) - 공백제거
  @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})"
      , message = "비밀 번호는 8~20자리로 숫자와 특수 문자가 포함된 영문 대소문자로 입력해 주세요")
  private String pw;

  // 이름
  @NotEmpty(message = "이름은 필수 입니다")
  private String name;

  // 이메일
  @NotEmpty(message = "이메일은 필수 입니다")
  @Email(message = "이메일 형식으로 입력해 주세요")
  private String email;

  // 주소
  @NotEmpty(message = "주소는 필수 입니다")
  private String addr;

  // 가입 날짜
  private Date signup_date;

  // 핸드폰
  @NotEmpty(message = "핸드폰 번호는 필수 입니다")
  @Length(min = 1, max = 10, message = "전화 번호는 '-'를 제외하고 10자리 이하로 입력해 주세요")
  private String phone;
}

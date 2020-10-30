package me.dev.oliver.mytubesns.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * {@literal @Setter} : Service단에서 비즈니스 로직으로써 암호화를 사용하여 저장하기 위해 사용함.
 * {@literal @ToString} : 예외 상횡시 회원 정보가 null값이나 의도하지 않은 다른 값을 가지고 있는지 확인하기 위해 사용함.
 */
@Getter
@Setter
@ToString
public class UserDto {

  /**
   * {@literal @NotBlank} : null을 허용하지 않음, 적어도 white-space가 아닌 문자가 한개 이상 포함되어야 함. null과 빈 공백 문자열(" ")을 허용 안함.
   * {@literal @NotEmpty} : null과 공백 문자열("") 을 허용하지 않음.
   * {@literal @Pattern} : 정규표현식에 맞는 문자열이어야 함.
   * {@literal @Email} : 이메일 양식이어야 함.
   */

  @NotEmpty(message = "아이디는 필수 입니다")
  private String userId;

  /**
   * (?=.*[0-9]) : 숫자는 적어도 하나.
   * (?=.*[a-zA-Z]) : 영문 대,소문자중적어도 하나.
   * (?=.*\\W) or (?=.*[~`!@#$%\\^&*()-]) : 특수문자 적어도 하나.
   * (?=\\S+$) :공백제거.
   */

  @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,20})", message = "비밀번호는 8~20자리로 숫자와 특수 문자가 포함된 영문 대문자와 소문자를 모두 입력해 주세요")
  private String pw;

  @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,20})", message = "새로운 비밀번호는 8~20자리로 숫자와 특수 문자가 포함된 영문 대문자와 소문자를 모두 입력해 주세요")
  private String newPw;

  @Length(min = 2, max = 20, message = "이름은 2~20자리 이하로 입력해 주세요")
  private String name;

  @Email(message = "이메일 형식으로 입력해 주세요")
  private String email;

  @Length(min = 2, max = 30, message = "주소는 2~30자리 이하로 입력해 주세요")
  private String addr;

  @Length(min = 9, max = 11, message = "전화 번호는 '-'를 제외하고 9~11자리 이하로 입력해 주세요")
  private String phone;

  public static boolean hasNullIdandPw(UserDto user) {
    return user.getUserId() == null || user.getPw() == null;
  }

  /**
   * 회원 정보를 가져올 때 null 유무를 확인.
   *
   * @return user 정보에서 가져온 변수 값들이 하나라도 null이면 true 아니면 false.
   */
  public static boolean hasNullData(UserDto user) {
    return user.getUserId() == null || user.getName() == null
        || user.getEmail() == null || user.getAddr() == null
        || user.getPhone() == null;
  }

}

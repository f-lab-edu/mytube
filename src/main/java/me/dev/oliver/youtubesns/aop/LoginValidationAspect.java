package me.dev.oliver.youtubesns.aop;

import lombok.AllArgsConstructor;
import me.dev.oliver.youtubesns.service.LoginService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * 401 Unauthorized 클라이언트 오류 상태 응답 코드는 해당 리소스에 유효한 인증 자격 증명이 없기 때문에 요청이 적용되지 않았음을 나타냄.
 */
@AllArgsConstructor
@Component
@Aspect
public class LoginValidationAspect {

  private final LoginService loginService;

  @Before("@annotation(LoginValidation)")
  public void userIsLogin() {

    String userId = loginService.getLoginId();
    if (userId == null) {
      throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED) {
      };
    }
  }

}

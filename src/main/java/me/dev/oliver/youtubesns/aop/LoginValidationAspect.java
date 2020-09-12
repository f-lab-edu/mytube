package me.dev.oliver.youtubesns.aop;

import me.dev.oliver.youtubesns.util.SessionKeys;
import me.dev.oliver.youtubesns.util.SessionUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * 401 Unauthorized 클라이언트 오류 상태 응답 코드는 해당 리소스에 유효한 인증 자격 증명이 없기 때문에 요청이 적용되지 않았음을 나타냄.
 */
@Component
@Aspect
public class LoginValidationAspect {

  @Before("@annotation(LoginValidation)")
  public void userIsSignedin() {

    String userId = SessionUtil.getAttribute(SessionKeys.USER_ID);
    if(userId == null) {
      throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED) {};
    }
  }

}

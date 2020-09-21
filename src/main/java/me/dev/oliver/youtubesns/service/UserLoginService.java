package me.dev.oliver.youtubesns.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import me.dev.oliver.youtubesns.dto.UserDto;

/**
 * 로그인 방식은 토큰 방식, SNS 같은 서드파티 로그인 등 다양하게 확장의 가능성이 있다.
 * 로그인한다라는 행위로 추상화를 함.
 */
public interface UserLoginService {

  boolean signin(UserDto user);

  void signout();
}

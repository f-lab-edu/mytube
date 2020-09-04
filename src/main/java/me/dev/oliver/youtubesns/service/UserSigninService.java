package me.dev.oliver.youtubesns.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import me.dev.oliver.youtubesns.dto.UserDto;

/**
 * 로그인은 확장의 근거가 있다 (JWT, 토큰 방식, 네이버, 카카오, 등등).
 * 로그인한다라는 행위로 추상화를 함.
 */
public interface UserSigninService {

  boolean signin(UserDto user);

  void signout();
}

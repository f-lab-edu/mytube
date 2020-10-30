package me.dev.oliver.mytubesns.service;

import me.dev.oliver.mytubesns.dto.UserDto;

/**
 * 로그인 방식은 토큰 방식, SNS 같은 서드파티 로그인 등 다양하게 확장의 가능성이 있다.
 * 로그인한다라는 행위로 추상화를 함.
 */
public interface UserLoginService {

  boolean login(UserDto user);

  void logout();

  boolean isLogin();
}

package me.dev.oliver.youtubesns.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import me.dev.oliver.youtubesns.dto.UserDto;

public interface UserSigninService {

  boolean signin(UserDto user, HttpServletRequest request);

  void signout(HttpServletRequest request);
}

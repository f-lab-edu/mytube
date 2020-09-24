package me.dev.oliver.youtubesns.controller;

import lombok.AllArgsConstructor;
import me.dev.oliver.youtubesns.dto.UserDto;
import me.dev.oliver.youtubesns.service.UserLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Controller
@RequestMapping("users")
public class FirstPartyLoginController {

  private final UserLoginService userLoginService;

  /**
   * @param user userId, pw를 받기위한 user 객체.
   */
  @PostMapping("login")
  public void login(@RequestBody UserDto user) {

    userLoginService.login(user);
  }

  @PostMapping("logout")
  public void logout() {

    userLoginService.logout();
  }

}

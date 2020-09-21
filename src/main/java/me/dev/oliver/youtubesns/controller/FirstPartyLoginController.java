package me.dev.oliver.youtubesns.controller;

import me.dev.oliver.youtubesns.dto.UserDto;
import me.dev.oliver.youtubesns.service.UserLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("users")
public class FirstPartyLoginController {

  private final UserLoginService userLoginService;

  public FirstPartyLoginController(UserLoginService userLoginService) {

    this.userLoginService = userLoginService;
  }

  /**
   * @param user userId, pw를 받기위한 user 객체.
   */
  @PostMapping("signin")
  public void signin(@RequestBody UserDto user) {

    userLoginService.signin(user);
  }

  @PostMapping("signout")
  public void signout() {

    userLoginService.signout();
  }

}

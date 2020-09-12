package me.dev.oliver.youtubesns.controller;

import me.dev.oliver.youtubesns.dto.UserDto;
import me.dev.oliver.youtubesns.service.UserSigninService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("users")
public class FirstPartySigninController {

  private final UserSigninService userSigninService;

  public FirstPartySigninController(UserSigninService userSigninService) {

    this.userSigninService = userSigninService;
  }

  /**
   * @param user userId, pw를 받기위한 user 객체.
   */
  @PostMapping("signin")
  public void signin(@RequestBody UserDto user) {

    userSigninService.signin(user);
  }

  @PostMapping("signout")
  public void signout() {

    userSigninService.signout();
  }

}

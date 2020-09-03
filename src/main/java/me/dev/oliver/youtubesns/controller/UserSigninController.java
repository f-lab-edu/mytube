package me.dev.oliver.youtubesns.controller;

import javax.servlet.http.HttpServletRequest;
import me.dev.oliver.youtubesns.dto.UserDto;
import me.dev.oliver.youtubesns.service.UserSigninService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("/users")
public class UserSigninController {

  private final UserSigninService userSigninService;

  public UserSigninController(UserSigninService userSigninService) {

    this.userSigninService = userSigninService;
  }

  /**
   * @param user userId, pw를 받기위한 user 객체.
   * @param request Service단에서 Httpsession을 생성하기 위한 request.
   */
  @PostMapping("/signin")
  public void signin(@RequestBody UserDto user, HttpServletRequest request) {

    userSigninService.signin(user, request);
  }

  /**
   * @param request Service단에서 Httpsession을 생성하기 위한 request.
   */
  @PostMapping("/signout")
  public void signout(HttpServletRequest request) {

    userSigninService.signout(request);
  }

}

package me.dev.oliver.youtubesns.controller;

import lombok.extern.slf4j.Slf4j;
import me.dev.oliver.youtubesns.dto.UserDto;
import me.dev.oliver.youtubesns.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users/")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }


  /**
   * 부연설명 : Post는 지정된 URI에 새 리소스를 만듦.
   *
   * @param user 회원 등록할 user 정보를 가지고 있음.
   */
  @PostMapping
  public void registerUser(@RequestBody UserDto user) {

    userService.insertUser(user);
  }


  /**
   * 부연설명 : PATCH는 리소스의 부분 업데이트를 수행. URI는 리소스에 적용할 변경 내용을 지정.
   *
   * @Param userId 회원이 로그인할 때 사용하는 id.
   * @Param pw 회원 현재 password.
   * @Param newPw 회원이 새롭게 생성할 password.
   */
  @PatchMapping("my-infos/password")
  public void changeUserPw(@RequestParam("userId") String userId, @RequestParam("pw") String pw,
      @RequestParam("newPw") String newPw) {

    userService.updateUserPw(userId, pw, newPw);
  }

  /**
   * Delete는 지정된 URI의 리소스를 제거. userId와, password를 입력 받아서 성공하면 제거
   *
   * @Param userId 회원이 로그인할 때 사용하는 id.
   * @Param pw 회원 password.
   */
  @DeleteMapping("my-infos")
  public void deleteUser(@RequestParam String userId, @RequestParam String pw) {

    userService.deleteUser(userId, pw);
  }


  /**
   * userId 중복 확인
   *
   * @Param userId 회원이 로그인할 때 사용하는 id.
   */
  @GetMapping("{userId}/duplicated")
  public boolean isExistsId(@PathVariable("userId") String userId) {

    return userService.isExistsId(userId);
  }
}

package me.dev.oliver.youtubesns.controller;

import lombok.extern.slf4j.Slf4j;
import me.dev.oliver.youtubesns.dto.UserDto;
import me.dev.oliver.youtubesns.service.UserService;
import org.springframework.dao.DuplicateKeyException;
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


  // Post는 지정된 URI에 새 리소스를 만듦. 입력된 아이디가 존재한다면 다시 회원가입 존재 하지 않는다면 회원 등록
  @PostMapping("signup")
  public void registerUser(@RequestBody UserDto user) {

    //id 중복 확인
    if (isExistsId(user.getUserId())) {
      log.error("중복된 아이디");
      throw new DuplicateKeyException("중복된 아이디입니다");
    }

    userService.registerUser(user);
  }


  // PATCH는 리소스의 부분 업데이트를 수행. URI는 리소스에 적용할 변경 내용을 지정.
  @PatchMapping("my-{userId}/passwords")
  public void changeUserPw(@PathVariable("userId") String userId, @RequestParam("pw") String pw,
      @RequestParam("newPw") String newPw) {

    userService.changeUserPw(userId, pw, newPw);
  }


  /**
   * Delete는 지정된 URI의 리소스를 제거. userId와, password를 입력 받아서 성공하면 제거
   */
  @DeleteMapping("{userId}")
  public void deleteUser(@PathVariable String userId, @RequestParam String pw) {

    userService.deleteUser(userId, pw);
  }


  // userId 중복 확인
  @GetMapping("duplication/{userId}")
  public boolean isExistsId(@PathVariable("userId") String userId) {

    return userService.isExistsId(userId);
  }
}

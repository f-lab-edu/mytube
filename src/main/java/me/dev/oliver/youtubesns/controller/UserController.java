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


  /** Post는 지정된 URI에 새 리소스를 만듦. 입력된 아이디가 존재한다면 다시 회원가입 존재 하지 않는다면 회원 등록 */
  @PostMapping
  public void registerUser(@RequestBody UserDto user) {

    userService.insertUser(user);
  }


  /** PATCH는 리소스의 부분 업데이트를 수행. URI는 리소스에 적용할 변경 내용을 지정. */
  @PatchMapping("my-info/password")
  public void changeUserPw(@RequestParam("email") String email, @RequestParam("pw") String pw, @RequestParam("newPw") String newPw) {

    userService.updateUserPw(email, pw, newPw);
  }


  /** Delete는 지정된 URI의 리소스를 제거. userId와, password를 입력 받아서 성공하면 제거 */
  @DeleteMapping("my-info")
  public void deleteUser(@RequestParam String userId, @RequestParam String pw) {

    userService.deleteUser(userId, pw);
  }


  /** userId 중복 확인 */
  @GetMapping("duplicated/{userId}")
  public boolean isExistsId(@PathVariable("userId") String userId) {

    return userService.isExistsId(userId);
  }
}

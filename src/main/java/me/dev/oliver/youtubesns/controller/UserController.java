package me.dev.oliver.youtubesns.controller;

import me.dev.oliver.youtubesns.dto.UserDto;
import me.dev.oliver.youtubesns.security.SecurityUtil;
import me.dev.oliver.youtubesns.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }


  // Post는 지정된 URI에 새 리소스를 만듦. 입력된 아이디가 존재한다면 다시 회원가입 존재 하지 않는다면 회원 등록
  @PostMapping("/signup")
  public void registerUser(@RequestParam("userId") String userId, @RequestParam("pw") String pw,
      @RequestParam("name") String name, @RequestParam("email") String email
      , @RequestParam("addr") String addr, @RequestParam("phone") String phone) throws Exception {
    userService.registerUser(
        new UserDto(userId, SecurityUtil.encryptSha256(pw), name, email, addr, phone));
  }


  // PATCH는 리소스의 부분 업데이트를 수행. URI는 리소스에 적용할 변경 내용을 지정.
  @PatchMapping("passwords/{userId}")
  public void changeUserPw(@PathVariable("userId") String userId, @RequestParam("pw") String pw,
      @RequestParam("newPw") String newPw) throws Exception {
    userService.changeUserPw(new UserDto(userId, SecurityUtil.encryptSha256(pw)),
        SecurityUtil.encryptSha256(newPw));
  }


  /**
   * Delete는 지정된 URI의 리소스를 제거. userId와, password를 입력 받아서 성공하면 제거
   */
  @DeleteMapping("{userId}")
  public void deleteUser(@RequestParam("userId") String userId, @RequestParam("pw") String pw)
      throws Exception {
    userService.deleteUser(new UserDto(userId, SecurityUtil.encryptSha256(pw)));
  }


  // userId 중복 확인
  @GetMapping("duplication/{userId}")
  public void isExistsId(@RequestParam("userId") String userId) {
    userService.isExistsId(new UserDto(userId));
  }
}

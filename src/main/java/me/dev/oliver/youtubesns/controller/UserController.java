package me.dev.oliver.youtubesns.controller;

import lombok.extern.slf4j.Slf4j;
import me.dev.oliver.youtubesns.dto.UserDto;
import me.dev.oliver.youtubesns.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  /** Post는 지정된 URI에 새 리소스를 만듦
   * 입력된 아이디가 존재한다면 다시 회원가입
   * 존재 하지 않는다면 회원 등록
   */
  @PostMapping("/users")
  public void registerUser(@RequestParam("userId") String userId, @RequestParam("pw") String pw, @RequestParam("name") String name, @RequestParam("email") String email
      , @RequestParam("addr") String addr, @RequestParam("phone") String phone) throws Exception {

    UserDto user = new UserDto(userId, pw, name, email, addr, phone);
    int idCheckCount = userService.idCheck(user);

    if(idCheckCount == 1) {
      log.warn("회원가입 재요청, 아이디 중복");
    }else {
      userService.registerUser(user);
    }
  }

  // Patch은 지정된 URI에 리소스를 만들거나 대체
  @PatchMapping("passwords/change")
  public void changeUserPw(@RequestParam("userId") String userId, @RequestParam("pw") String pw, @RequestParam("newPw") String newPw) throws Exception {
    userService.changeUserPw(new UserDto(userId, pw), newPw);
  }

  // Delete는 지정된 URI의 리소스를 제거
  // userId와, password를 입력 받아서 성공하면 제거
  @DeleteMapping("/users/withdrawal")
  public void deleteUser(@RequestParam("userId") String userId, @RequestParam("pw") String pw) throws Exception {
    userService.deleteUser(new UserDto(userId, pw));
  }
}

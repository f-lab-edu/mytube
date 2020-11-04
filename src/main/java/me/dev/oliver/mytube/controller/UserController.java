package me.dev.oliver.mytube.controller;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dev.oliver.mytube.aop.LoginValidation;
import me.dev.oliver.mytube.dto.UserDto;
import me.dev.oliver.mytube.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

  private final UserService userService;

  /**
   * 부연설명 : Post는 지정된 URI에 새 리소스를 만듦.
   *
   * @param user 회원 등록할 user 정보를 가지고 있음.
   */
  @PostMapping
  public void registerUser(@Valid @RequestBody UserDto user) {

    userService.insertUser(user);
  }

  /**
   * 회원 정보를 수정하기 전에 pasword를 다시 요청함.
   * @param user
   */
  @LoginValidation
  @GetMapping("my-infos")
  public UserDto userInfos(@Valid @RequestBody UserDto user) {

    user = userService.getUserInfo(user);
    return user;
  }

  /**
   * 부연설명 : PATCH는 리소스의 부분 업데이트를 수행. URI는 리소스에 적용할 변경 내용을 지정.
   *
   * @Param 회원 userId와 현재 password, 새롭게 변경할 password를 입력 받음.
   */
  @LoginValidation
  @PatchMapping("my-infos/password")
  public void changeUserPw(@Valid @RequestBody UserDto user) {

    userService.updateUserPw(user);
  }

  /**
   * @param user 회원 userId와 회원이 새롭게 변경할 address를 입력 받음.
   */
  @LoginValidation
  @PatchMapping("my-infos/address")
  public void changeUserAddr(@Valid @RequestBody UserDto user) {

    userService.updateUserAddr(user);
  }

  /**
   * Delete는 지정된 URI의 리소스를 제거. userId와, password를 입력 받아서 성공하면 제거.
   *
   * @Param 회원 userId와 password 입력 받음.
   */
  @LoginValidation
  @DeleteMapping("my-infos")
  public void deleteUser(@Valid @RequestBody UserDto user) {

    userService.deleteUser(user);
  }


  /**
   * userId 중복 확인
   *
   * @Param userId 회원이 로그인할 때 사용하는 id.
   */
  @GetMapping("{userId}/duplicate")
  public boolean isExistsId(@Valid @PathVariable("userId") String userId) {

    return userService.isExistsId(userId);
  }
}

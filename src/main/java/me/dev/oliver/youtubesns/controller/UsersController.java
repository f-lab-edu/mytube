package me.dev.oliver.youtubesns.controller;

import me.dev.oliver.youtubesns.dto.UsersDto;
import me.dev.oliver.youtubesns.mapper.UsersMapper;
import me.dev.oliver.youtubesns.service.UsersService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

  private UsersService usersService;

  public UsersController(UsersService usersService) {
    this.usersService = usersService;
  }

  // Get은 지정된 URI에서 리소스의 표현을 검색
  @GetMapping("/users/{id}")
  public UsersDto getUsersInfo(@PathVariable("id") String id) {
    return usersService.getUsersInfo(id);
  }

  // Post는 지정된 URI에 새 리소스를 만듦
  @PostMapping("/users")
  public void postRegisterUser(UsersDto user) {
    usersService.postRegisterUser(user);
  }

  // Patch은 지정된 URI에 리소스를 만들거나 대체
  @PatchMapping("/users/{id}")
  public void patchChangeUserPw(@PathVariable("id") Integer id, @RequestParam("pw") String pw) {
    usersService.patchChangeUserPw(id, pw);
  }

  // Delete는 지정된 URI의 리소스를 제거
  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable("id") Integer id) {
    usersService.deleteUser(id);
  }
}

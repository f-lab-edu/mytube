package me.dev.oliver.youtubesns.controller;

import java.time.LocalDate;
import java.util.List;
import me.dev.oliver.youtubesns.dto.UserDto;
import me.dev.oliver.youtubesns.mapper.UserMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private UserMapper mapper;

  public UserController(UserMapper mapper) {
    this.mapper = mapper;
  }

  @GetMapping("/user/{id}")
  public UserDto getUser(@PathVariable("id") String id) {
    return mapper.getUser(id);
  }

  @GetMapping("/user/all")
  public List<UserDto> geUserList() {
    return mapper.getUserList();
  }

  @PutMapping("/user")
  public void putUser(UserDto user) {
    mapper.insertUser(user);
  }

  @PostMapping("/user/{id}")
  public void postUser(@PathVariable("id") Integer id, @RequestParam("pw") String pw) {
    mapper.changePassword(id, pw);
  }

  @DeleteMapping("/user/{id}")
  public void deleteUser(@PathVariable("id") Integer id) {
    mapper.deleteUser(id);
  }
}

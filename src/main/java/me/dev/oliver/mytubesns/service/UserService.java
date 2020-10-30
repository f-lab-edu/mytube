package me.dev.oliver.mytubesns.service;

import me.dev.oliver.mytubesns.dto.UserDto;

public interface UserService {

  void insertUser(UserDto user);

  UserDto getUserInfo(UserDto user);

  void updateUserPw(UserDto user);

  void updateUserAddr(UserDto user);

  void deleteUser(UserDto user);

  boolean findByIdAndPw(UserDto user);

  boolean isExistsId(String userId);
}

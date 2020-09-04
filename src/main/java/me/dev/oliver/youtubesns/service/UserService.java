package me.dev.oliver.youtubesns.service;

import me.dev.oliver.youtubesns.dto.UserDto;

public interface UserService {

  void insertUser(UserDto user);

  void updateUserPw(UserDto user);

  void deleteUser(UserDto user);

  boolean findByIdAndPw(UserDto user);

  boolean isExistsId(String userId);
}

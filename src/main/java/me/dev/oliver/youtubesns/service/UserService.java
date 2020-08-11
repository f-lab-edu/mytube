package me.dev.oliver.youtubesns.service;

import me.dev.oliver.youtubesns.dto.UserDto;

public interface UserService {

  void registerUser(UserDto user);

  void changeUserPw(UserDto user, String newPw) throws Exception;

  void deleteUser(UserDto user);

  boolean isExistsId(UserDto user);
}

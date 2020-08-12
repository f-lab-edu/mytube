package me.dev.oliver.youtubesns.service;

import me.dev.oliver.youtubesns.dto.UserDto;

public interface UserService {

  void registerUser(UserDto user) ;

  void changeUserPw(String userId, String pw, String newPw) ;

  void deleteUser(String userId, String pw);

  boolean isExistsId(String userId);
}

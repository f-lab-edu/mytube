package me.dev.oliver.youtubesns.service;

import me.dev.oliver.youtubesns.dto.UserDto;

public interface UserService {

  void insertUser(UserDto user);

  void updateUserPw(String email, String pw, String newPw) ;

  void deleteUser(String userId, String pw);

  boolean isExistsId(String userId);
}

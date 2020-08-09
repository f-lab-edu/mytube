package me.dev.oliver.youtubesns.service;

import me.dev.oliver.youtubesns.dto.UserDto;

public interface UserService {
  public void registerUser(UserDto user);
  public void changeUserPw(UserDto user, String newPw) throws Exception;
  public void deleteUser(UserDto user);
  public int idCheck(UserDto user);
}

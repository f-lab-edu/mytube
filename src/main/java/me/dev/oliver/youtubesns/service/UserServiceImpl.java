package me.dev.oliver.youtubesns.service;

import me.dev.oliver.youtubesns.dto.UserDto;
import me.dev.oliver.youtubesns.mapper.UserMapper;
import me.dev.oliver.youtubesns.security.SecurityUtil;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  public UserServiceImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  public void registerUser(UserDto user) {
    userMapper.insertUser(user);
  }

  public void changeUserPw(UserDto user, String newPw) throws Exception {
    int id = checkUser(user);
    if (id > 0) {
      user = new UserDto(id, newPw);
      userMapper.updatePassword(user);
    }
  }

  public void deleteUser(UserDto user) {
    userMapper.deleteUser(user);
  }

  // id와 pw 확인
  private int checkUser(UserDto user) {
    try {
      int id = userMapper.checkUser(user);
      if (id > 0) {
        return id;
      }
    } catch (Exception e) {
    }

    return 0;
  }

  public boolean isExistsId(UserDto user) {
    return userMapper.isExistsId(user);
  }

}

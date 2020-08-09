package me.dev.oliver.youtubesns.service;

import me.dev.oliver.youtubesns.dto.UserDto;
import me.dev.oliver.youtubesns.mapper.UserMapper;
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
    if(id > 0) {
      user = new UserDto(id, newPw);
      userMapper.changePassword(user);
    }
  }

  public void deleteUser(UserDto user) {
    userMapper.deleteUser(user);
  }

  // id와 pw 확인
  private int checkUser(UserDto user) {
    int id = 0;
    try {
      id = userMapper.checkUser(user);
    }catch (Exception e) {
    }
    if(id != 0) return id;
    else return 0;
  }

  public int idCheck(UserDto user) {
    int result = userMapper.idCheck(user);
    return result;
  }

}

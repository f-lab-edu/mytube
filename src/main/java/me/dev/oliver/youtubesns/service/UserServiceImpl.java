package me.dev.oliver.youtubesns.service;

import lombok.extern.slf4j.Slf4j;
import me.dev.oliver.youtubesns.dto.UserDto;
import me.dev.oliver.youtubesns.mapper.UserMapper;
import me.dev.oliver.youtubesns.security.SecurityUtil;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;


  public UserServiceImpl(UserMapper userMapper) {

    this.userMapper = userMapper;
  }


  public void registerUser(UserDto user) {

    try {
      user.setPw(SecurityUtil.encryptSha256(user.getPw()));
      userMapper.insertUser(user);
    } catch (Exception e){
      log.error("registerUser method error");
    }
  }


  public void changeUserPw(String userId, String oldPw, String newPw) {

    try {
      boolean checkIdAndPw = checkUser(userId, oldPw);
      if (checkIdAndPw) {
        userMapper.updatePassword(userId, SecurityUtil.encryptSha256(newPw));
      } else {
        throw new IllegalArgumentException("아이디와 패스워드가 일치하지 않습니다.");
      }
    } catch (Exception e) {
      log.error("{}", e);
      log.error("changeUserPw method error");
    }
  }


  public void deleteUser(String userId, String pw) {

    try {
      boolean checkIdAndPw = checkUser(userId, pw);
      if (checkIdAndPw) {
        userMapper.deleteUser(userId);
      } else {
        throw new IllegalArgumentException("아이디와 패스워드가 일치하지 않습니다.");
      }
    } catch (Exception e) {
      log.error("deleteUser method error");
    }
  }


  // id와 pw 확인
  private boolean checkUser(String userId, String pw) {

    try {
      boolean checkIdAndPw = userMapper.checkIdAndPw(userId, SecurityUtil.encryptSha256(pw));

      if (checkIdAndPw) {
        return true;
      }
    } catch (Exception e) {
      log.error("checkUser method error");
    }

    return false;
  }


  public boolean isExistsId(String userId) {

    return userMapper.isExistsId(userId);
  }

}

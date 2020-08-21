package me.dev.oliver.youtubesns.service;

import me.dev.oliver.youtubesns.dto.UserDto;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;

public interface UserService {

  void insertUser(UserDto user) throws DuplicateMemberException;

  void updateUserPw(String userId, String pw, String newPw) ;

  void deleteUser(String userId, String pw);

  boolean isExistsId(String userId);
}

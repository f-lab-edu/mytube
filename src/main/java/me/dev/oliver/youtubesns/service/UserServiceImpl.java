package me.dev.oliver.youtubesns.service;

import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import me.dev.oliver.youtubesns.dto.UserDto;
import me.dev.oliver.youtubesns.mapper.UserMapper;
import me.dev.oliver.youtubesns.security.SecurityUtil;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;


  public UserServiceImpl(UserMapper userMapper) {

    this.userMapper = userMapper;
  }

  /**
   * 유저 정보 추가.
   *
   * @param user 유저를 등록에 사용할 유저 정보를 담은 객체.
   * @throws RuntimeException             SQL 문법 또는 mybatis 예외
   * @throws DuplicateMemberException sha 256 암호화 알고리즘 예외 NoSuchAlgorithmException.
   */
  public void insertUser(UserDto user) throws DuplicateMemberException{

    if (isExistsId(user.getUserId())) {
      log.error("중복된 아이디 입니다. 중복된 유저 아이디 : {}", user.getUserId());
      throw new DuplicateMemberException("중복된 아이디입니다.");
    }

    user.setPw(SecurityUtil.encryptSha256(user.getPw()));
    if (userMapper.insertUser(user) != 1) {
      throw new RuntimeException("유저 등록에 실패했습니다.");
    }
}

  /**
   * 사용자 패스워드 변경.
   * <p>
   * ******추후에 로그인이 되어 있는지 확인하는 로직 추가해야함******
   *
   * @param userId id랑 이전 password랑 연결시켜 확인할 회원 id
   * @param oldPw  이전 password.
   * @param newPw  새로 변경할 password.
   * @throws RuntimeException         SQL 문법 또는 mybatis 예외
   * @throws IllegalArgumentException 패스워드 일치하지 않을시 예외
   */
  public void updateUserPw(String userId, String oldPw, String newPw) {

    if (checkPwAfterSignin(oldPw)) {
      if (userMapper.updatePassword(userId, SecurityUtil.encryptSha256(newPw)) != 1) {
        log.error("userMapper.updatePassword 매서드 실패. {}", userId);
        throw new RuntimeException("패스워드를 변경할 수 없습니다.");
      }
    } else {
      throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
    }

  }

  /**
   * 사용자 정보 삭제.
   * <p>
   * ******추후에 로그인이 되어있는지 확인하는 로직 추가해야함******
   *
   * @param userId 유저 삭제 확인차 필요한 유저 id
   * @param pw     유저 삭제시 확인차 필요한 password
   * @throws RuntimeException         SQL 문법 또는 mybatis 예외
   * @throws IllegalArgumentException 패스워드 일치하지 않을시 예외
   */
  public void deleteUser(String userId, String pw) {

    if (checkPwAfterSignin(pw)) {
      if (userMapper.deleteUser(userId) != 1) {
        log.error("userMapper.deleteUser 실패");
        throw new RuntimeException("유저 삭제 실패");
      }
    } else {
      throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
    }

  }

  /**
   * 로그인 후 pw 확인.
   * <p>
   * ******추후에 로그인 구현하면 로그인이 되어 있는지 확인하는 로직 추가 해야 함****** 로그인 할 때 메소드는 따로 만들 예정임.
   *
   * @param pw password 확인 시 필요한 password.
   * @return 로그인 후 password 확인 시 일치 하면 true, 아니면 false.
   */
  private boolean checkPwAfterSignin(String pw) {

    return userMapper.checkIdAndPw(SecurityUtil.encryptSha256(pw));
  }

  /**
   * 아이디 중복 확인.
   *
   * @param userId id 중복확인 할 때 회원 id.
   * @return user id가 중복이면 true, 아니면 false.
   */
  public boolean isExistsId(String userId) {

    return userMapper.isExistsId(userId);
  }

}

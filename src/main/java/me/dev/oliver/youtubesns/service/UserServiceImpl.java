package me.dev.oliver.youtubesns.service;

import lombok.extern.slf4j.Slf4j;
import me.dev.oliver.youtubesns.dto.UserDto;
import me.dev.oliver.youtubesns.mapper.UserMapper;
import me.dev.oliver.youtubesns.security.SecurityUtil;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.stereotype.Service;

/**
 * IllegalArgumentException : 허용하지 않는 값이 인수로 건네졌을 때(null은 따로 NullPointerException으로 처리)
 */
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
   * @throws IllegalArgumentException             SQL 문법 또는 mybatis 예외, 또는 SQL where 불일치
   * @throws DuplicateMemberException sha 256 암호화 알고리즘 예외 NoSuchAlgorithmException.
   */
  public void insertUser(UserDto user) throws DuplicateMemberException{

    if (isExistsId(user.getUserId())) {
      log.error("중복된 아이디 입니다. 중복된 유저 아이디 : {}", user.getUserId());
      throw new DuplicateMemberException("중복된 아이디입니다.");
    }

    user.setPw(SecurityUtil.encryptSha256(user.getPw()));
    if (userMapper.insertUser(user) != 1) {
      throw new IllegalArgumentException("유저 등록에 실패했습니다.");
    }
}

  /**
   * 사용자 패스워드 변경.
   * <p>
   * ******추후에 로그인이 되어 있는지 확인하는 로직 추가해야함******
   *
   * @param user id랑 이전 password랑 연결시켜 확인할 회원 id, 이전 password, 새로 변경할 password.
   * @throws IllegalArgumentException SQL 문법 또는 mybatis 예외, 또는 SQL where 불일치
   * @throws IllegalArgumentException 패스워드 일치하지 않을시 예외
   */
  public void updateUserPw(UserDto user) {
//    log.info(user.getUserId() + " " + user.getPw() + " " + user.getNewPw());
    if (checkPw(user)) {
      user.setNewPw(SecurityUtil.encryptSha256(user.getNewPw()));
      if (userMapper.updatePassword(user) != 1) {
        log.error("userMapper.updatePassword 매서드 실패. {}", user.getUserId());
        throw new IllegalArgumentException("패스워드를 변경할 수 없습니다, 아이디가 일치하지 않습니다.");
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
   * @param user id랑 이전 password랑 연결시켜 확인할 회원 id, 이전 password.
   * @throws IllegalArgumentException SQL 문법 또는 mybatis 예외, 또는 SQL where 불일치
   * @throws IllegalArgumentException 패스워드 일치하지 않을시 예외
   */
  public void deleteUser(UserDto user) {

    if (checkPw(user)) {
      if (userMapper.deleteUser(user.getUserId()) != 1) {
        log.error("userMapper.deleteUser 실패");
        throw new IllegalArgumentException("유저 삭제 실패, 아이디가 일치하지 않습니다.");
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
   * @param user 확인 시 필요한 userId, password.
   * @return 로그인 후 password 확인 시 일치 하면 true, 아니면 false.
   */
  private boolean checkPw(UserDto user) {
    // 로그인 구현할 때 session 에 저장해놓은 유저 아이디를 불러와 적용.
    user.setPw(SecurityUtil.encryptSha256(user.getPw()));
    return userMapper.checkPw(user);
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

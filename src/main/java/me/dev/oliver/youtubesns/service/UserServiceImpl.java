package me.dev.oliver.youtubesns.service;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import me.dev.oliver.youtubesns.dto.UserDto;
import me.dev.oliver.youtubesns.mapper.UserMapper;
import me.dev.oliver.youtubesns.util.SecurityUtil;
import org.apache.ibatis.jdbc.Null;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Exception은 수많은 자식클래스를 가지고 있음. 그 중 RuntimeException을 주목해야 함. RuntimeException은 CheckedException과
 * UncheckedException을 구분하는 기준. Exception의 자식 클래스 중 RuntimeException을 제외한 모든 클래스는
 * CheckedException이며, RuntimeException과 그의 자식 클래스들을 Unchecked Exception이라 함.
 * - Checked Exception : 반드시 예외를 처리해야함, 컴파일 단계에 확인 가능,
예외발생시 트랜잭션처리는 roll-back하지 않음, Exception의 상속받는 하위 클래스 중 Runtime
 * Exception을 제외한 모든 예외(IOException, SQLException).
 * - Unchecked Exception : 명시적인 처리를 강제하지 않음, 실행단계에서
 * 확인 가능, roll-back 함, RuntimeException 하위 예외(NullPointerException, IllegalArgumentException,
 * IndexOutOfBoundException, SystemException).
 *
 * 예외 처리 방법 : 예외복구, 예외처리 회피, 예외 전환
 *
 * IllegalArgumentException : 허용하지 않는 값이 인수로 건네졌을 때(null은 * 따로 NullPointerException으로 처리).
 *
 * Transactional annotation : 프록시 객체가 생성되고 `begin`, `commit`을 자동 수행해준다.
 * 예외를 발생시키면, `rollback` 처리를 자동 수행해준다.
 */
@Slf4j
@Transactional
@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;


  public UserServiceImpl(UserMapper userMapper) {

    this.userMapper = userMapper;
  }

  /**
   * 사용자 정보 추가.
   *
   * @param user 유저를 등록에 사용할 유저 정보를 담은 객체.
   * @throws DuplicateKeyException    중복된 아이디 예외.
   * @throws IllegalArgumentException SQL 문법 또는 mybatis 예외, 또는 SQL where 불일치.
   */
  public void insertUser(UserDto user) {

    if (isExistsId(user.getUserId())) {
      log.error("중복된 아이디 입니다. 중복된 유저 아이디 : {}", user.getUserId());
      throw new DuplicateKeyException("중복된 아이디입니다.");
    }

    user.setPw(SecurityUtil.encryptSha256(user.getPw()));

    if (userMapper.insertUser(user) != 1) {
      throw new IllegalArgumentException("유저 등록에 실패했습니다.");
    }
  }

  /**
   * 사용자 정보 가져오기.
   * <p>
   * 로그인이 되어 있어야 하고 더 비밀번호 체크를 해야함.
   * 로그인 유무는 Controller단에서 확인 했으니 여기서 userId null 유무는 pass.
   *
   * @param user
   * @throws NullPointerException 리턴된 데이터 중에 하나라도 null이면 예외처리.
   * @return 유저 정보를 return.
   */
  public UserDto getUserInfo(UserDto user) {

    user.setPw(SecurityUtil.encryptSha256(user.getPw()));

    if(UserDto.hasNullIdandPw(user)) {
      throw new NullPointerException("아이디 또는 패스워드에 Null 값이 확인되었습니다.");
    }

    user = userMapper.findByInfo(user);

    if(UserDto.hasNullData(user)) {
      throw new NullPointerException("사용자 정보를 가져오던 중에 Null 값이 확인 되었습니다.");
    }

    return user;
  }

  /**
   * 사용자 패스워드 변경.
   * <p>
   * 로그인이 되어 있어야 하고 비밀번호를 변경하려면 한번 더 비밀번호 체크를 해야함.
   *
   * @param user id랑 이전 password랑 연결시켜 확인할 회원 id, 이전 password, 새로 변경할 password.
   * @throws IllegalArgumentException SQL 문법 또는 mybatis 예외, 또는 SQL where 불일치
   * @throws IllegalArgumentException 패스워드 일치하지 않을시 예외
   */
  public void updateUserPw(UserDto user) {

    user.setPw(SecurityUtil.encryptSha256(user.getPw()));

    if (findByIdAndPw(user)) {
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
   * 사용자 주소 변경.
   * <p>
   * 로그인이 되어 있어야 함.
   *
   * @param user 사용자 pw
   */
  public void updateUserAddr(UserDto user) {

    userMapper.updateUserAddr(user);
  }

  /**
   * 사용자 정보 삭제.
   * <p>
   * 로그인이 되어 있어야 하고 한번 더 비밀번호 체크를 해야함.
   *
   * @param user id랑 이전 password랑 연결시켜 확인할 회원 id, password.
   * @throws IllegalArgumentException SQL 문법 또는 mybatis 예외, 또는 SQL where 불일치
   * @throws IllegalArgumentException 패스워드 일치하지 않을시 예외
   */
  public void deleteUser(UserDto user) {

    user.setPw(SecurityUtil.encryptSha256(user.getPw()));

    if (findByIdAndPw(user)) {
      if (userMapper.deleteUser(user.getUserId()) != 1) {
        log.error("userMapper.deleteUser 실패");
        throw new IllegalArgumentException("유저 삭제 실패, 아이디가 일치하지 않습니다.");
      }
    } else {
      throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
    }
  }

  /**
   * 아이디와 pw를 확인.
   * 이 메소드를 호출하기 전에 password를 암호화하여 사용해야 함.
   * <p>
   *
   * @param user 확인 시 필요한 userId, password.
   * @return id와 password 확인 시 일치 하면 true, 아니면 false.
   */
  public boolean findByIdAndPw(UserDto user) {

    return Optional.ofNullable(userMapper.findByIdAndPw(user)).orElse(false);
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

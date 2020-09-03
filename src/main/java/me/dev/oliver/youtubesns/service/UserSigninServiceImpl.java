package me.dev.oliver.youtubesns.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import me.dev.oliver.youtubesns.dto.UserDto;
import me.dev.oliver.youtubesns.mapper.UserMapper;
import me.dev.oliver.youtubesns.security.SecurityUtil;
import org.springframework.stereotype.Service;

/**
 * HttpSession session = request.getSession();
 * 서버에 생성된 세션이 있다면 세션을 반환하고 없다면 새롭게 세션을 생성하여 반환 새롭게 생성된
 * 세션인지는 HttpSession의 isNew() 메소드를 통해 알 수 있음.
 *
 * getSession()에 false를 전달하면, 이미 생성된 세션이 있다면 반환하고 없으면 null을 반환
 * HttpSession session2 = request.getSession(false);
 */

@Service
public class UserSigninServiceImpl implements UserSigninService {

  private final UserMapper userMapper;

  public UserSigninServiceImpl(UserMapper userMapper) {

    this.userMapper = userMapper;
  }

  /**
   * userId, pw를 받아 로그인 성공 유무 확인.
   *
   * @param user    로그인시 필요한 userId, pw.
   * @param request HttpSession을 사용하기 위해 사용.
   * @return userId, pw가 일치하여 로그인이 가능하면 true 아니면 false.
   */
  public boolean signin(UserDto user, HttpServletRequest request) {

    user.setPw(SecurityUtil.encryptSha256(user.getPw()));
    boolean result = userMapper.findByIdAndPw(user);

    if (result) {
      HttpSession session = request.getSession();
      session.setAttribute("userId", user.getUserId());
    } else {
      throw new IllegalArgumentException("로그인 실패, 아이디 또는 패스워드가 일치하지 않습니다.");
    }

    return result;
  }

  /**
   * session을 비활성화 시켜 로그아웃.
   *
   * 세션을 유효하지 않게 설정 (세션에 저장된 모든 값을 삭제).
   * session.invalidate();
   *
   * @param request HttpSession을 사용하기 위해 사용.
   */
  public void signout(HttpServletRequest request) {

    HttpSession session = request.getSession();
    session.invalidate();
  }

  /**
   * session을 이용하여 로그인이 되어 있는지 확인.
   *
   * @param request HttpSession을 사용하기 위해 사용.
   * @return 로그인이 되어있으면 true 아니면 false.
   */
  public boolean isSignin(HttpServletRequest request) {

    HttpSession session = request.getSession();
    String userId = (String) session.getAttribute("userId");

    return userId != null;
  }
}

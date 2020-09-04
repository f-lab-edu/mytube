package me.dev.oliver.youtubesns.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import me.dev.oliver.youtubesns.dto.UserDto;
import me.dev.oliver.youtubesns.mapper.UserMapper;
import me.dev.oliver.youtubesns.util.SecurityUtil;
import me.dev.oliver.youtubesns.util.SessionUtil;
import org.springframework.stereotype.Service;

@Service
public class UserSigninServiceImpl implements UserSigninService {

  private final UserService userService;

  public UserSigninServiceImpl(UserService userService) {

    this.userService = userService;
  }

  /**
   * userId, pw를 받아 로그인 성공 유무 확인.
   *
   * @param user 로그인시 필요한 userId, pw.
   * @return userId, pw가 일치하여 로그인이 가능하면 true 아니면 false.
   */
  public boolean signin(UserDto user) {

    user.setPw(SecurityUtil.encryptSha256(user.getPw()));
    boolean result = userService.findByIdAndPw(user);

    if (result) {
      SessionUtil.setAttribute("userId", user.getUserId());
    } else {
      throw new IllegalArgumentException("로그인 실패, 아이디 또는 패스워드가 일치하지 않습니다.");
    }

    return result;
  }

  /**
   * session을 비활성화 시켜 로그아웃.
   */
  public void signout() {

    SessionUtil.invaldateSession();
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

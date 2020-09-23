package me.dev.oliver.youtubesns.service;

import lombok.AllArgsConstructor;
import me.dev.oliver.youtubesns.dto.UserDto;
import me.dev.oliver.youtubesns.util.SecurityUtil;
import me.dev.oliver.youtubesns.util.SessionKeys;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserLoginServiceImpl implements UserLoginService {

  private final UserService userService;
  private final LoginService loginService;

  /**
   * userId, pw를 받아 로그인 성공 유무 확인.
   *
   * @param user 로그인시 필요한 userId, pw.
   * @return userId, pw가 일치하여 로그인이 가능하면 true 아니면 false.
   */
  public boolean login(UserDto user) {

    user.setPw(SecurityUtil.encryptSha256(user.getPw()));
    boolean result = userService.findByIdAndPw(user);

    if (result) {
      loginService.login(SessionKeys.USER_ID, user.getUserId());
    } else {
      throw new IllegalArgumentException("로그인 실패, 아이디 또는 패스워드가 일치하지 않습니다.");
    }

    return result;
  }

  /**
   * session을 비활성화 시켜 로그아웃.
   */
  public void logout() {

    loginService.logout();
  }

  /**
   * session을 이용하여 로그인이 되어 있는지 확인.
   *
   * @return 로그인이 되어있으면 true 아니면 false.
   */
  public boolean isLogin() {

    String userId = (String) loginService.getLoginId();

    return userId != null;
  }
}

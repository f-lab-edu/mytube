package me.dev.oliver.mytube.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import me.dev.oliver.mytube.util.SessionKeys;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * UserLoginService와 분리 시키기 위해 사용됨. Service단에서는 Sevlet의 존재를 알필요가 없음. Session은 로그인 뿐만 아니라 장바구니 구현 등에도
 * 사용될 수 있기 때문에 확장의 가능성이 있음. 상태 정보 유지의 행위를 추상화. 세션을 이용해 동작을 제공하는 클래스로 만들어서 사용함.
 * <p>
 * <p>
 * HttpSession session = request.getSession(); 서버에 생성된 세션이 있다면 세션을 반환하고 없다면 새롭게 세션을 생성하여 반환 새롭게 생성된
 * 세션인지는 HttpSession의 isNew() 메소드를 통해 알 수 있음.
 * <p>
 * getSession()에 false를 전달하면, 이미 생성된 세션이 있다면 반환하고 없으면 null을 반환 HttpSession session2 =
 * request.getSession(false);
 * <p>
 * <p>
 * 싱글톤 vs 정적 클래스
 * <p>
 * 싱글톤은 인스턴스 생성가능, 힙에 저장, OOP 규칙을 준수, 상속가능, 인터페이스 구현 가능, 느슨하고, 비동기적인 초기화가 가능, 보통 첫 사용시 초기화. static
 * 클래스 보다 유연하고 상태를 유지할 수 있음. 메서드를 재정의 할 수 있음. 상태를 디스크에 저장하거나 원격으로 보내야 할 경우 필요한 직렬화 가능.
 * <p>
 * 정적 클래스는 인스턴스 생성 불가능, 스택에 저장, OOP 규칙은 준수 하지 않음, 상속 불가능, 인터페이스 구현 불가능, 클래스가 포함하고 있는 프로그램 혹은
 * Namespace가 로드 될때 CLR(common language runtiom)에서 자동으로 로드 된다. 컴파일 시점에서 검사한다.
 * <p>
 * <p>
 * 개발자가 코드를 잘 작업하면 예외가 나지 않는 상황이 있을텐데 예외처리를 하는 이유는? 예외 처리는 예외가 발생한 상황에서 프로그램을 종료하지 않고 가능한 한 예외를
 * 핸들링하여 프로그램의 흐름을 복구하고, 의도하는 방향으로 프로그램을 수행할 수 있게 하는 것을 의미합니다. 예외상황이 발생하게 되면 개발자 또는 사용자에게 예외에 대한 상황에
 * 대해서 알려 프로그램을 빠르게 처리하기 위해서 예외처리를 하게 됩니다. 예외 처리를 사용하는 목적은 조금 더 안정적이고 오류에 강한 프로그램을 만드는 것입니다. 그래서 이것을
 * 충족시키기 위해서는 예외를 다뤄 프로그램이 동작하는것에 있어서 큰 도움이 됩니다.
 */

@Slf4j
@Service
public class SessionLoginServiceImpl implements LoginService {

  private SessionLoginServiceImpl() {
  }


  private HttpSession getHttpSession() {

    HttpSession session = null;
    HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes()).getRequest();

    if (req != null) {
      session = req.getSession();
    } else {
      log.error("HttpServletRequest 생성 도중에 null이 확인되었습니다.");
      throw new IllegalArgumentException("서버에서 사용자의 정보를 불러오는 도중 예상치 못한 에러가 발생했습니다");
    }

    return session;
  }

  /**
   * 세션 키값 지정.
   *
   * @param key    session key를 지정.
   * @param userId session key에 대한 value 지정.
   */
  public void login(String key, Object userId) {

    getHttpSession().setAttribute(key, userId);
  }

  /**
   * userId값을 확인한 후 value값을 반환
   *
   * @return key에 대한 value를 return.
   */
  public String getLoginId() {

    return (String) getHttpSession().getAttribute(SessionKeys.USER_ID);
  }

  /**
   * 세션을 유효하지 않게 설정 (세션에 저장된 모든 값을 삭제). session.invalidate();
   */
  public void logout() {

    getHttpSession().invalidate();
  }

}

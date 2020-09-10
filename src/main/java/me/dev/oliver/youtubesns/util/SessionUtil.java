package me.dev.oliver.youtubesns.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Service단과 분리 시키기 위해 사용됨. Service단에서는 Sevlet의 존재를 알필요가 없음. Session은 로그인 뿐만 아니라 장바구니 구현 등에도 사용될 수
 * 있기 때문에 확장의 가능성이 있음. 상태 정보 유지의 행위를 추상화.
 * <p>
 * <p>
 * HttpSession session = request.getSession(); 서버에 생성된 세션이 있다면 세션을 반환하고 없다면 새롭게 세션을 생성하여 반환 새롭게 생성된
 * 세션인지는 HttpSession의 isNew() 메소드를 통해 알 수 있음.
 * <p>
 * getSession()에 false를 전달하면, 이미 생성된 세션이 있다면 반환하고 없으면 null을 반환 HttpSession session2 =
 * request.getSession(false);
 * <p>
 * 싱글톤 vs 정적 클래스
 *
 *  싱글톤은 인스턴스 생성가능, 힙에 저장, OOP 규칙을 준수, 상속가능, 인터페이스 구현 가능, 느슨하고, 비동기적인 초기화가 가능, 보통 첫 사용시 초기화.
 *  static 클래스 보다 유연하고 상태를 유지할 수 있음. 메서드를 재정의 할 수 있음. 상태를 디스크에 저장하거나 원격으로 보내야 할 경우 필요한 직렬화 가능.
 *
 *  정적 클래스는 인스턴스 생성 불가능, 스택에 저장, OOP 규칙은 준수 하지 않음, 상속 불가능, 인터페이스 구현 불가능, 클래스가 포함하고 있는 프로그램 혹은
 *  Namespace가 로드 될때 CLR(common language runtiom)에서 자동으로 로드 된다. 컴파일 시점에서 검사한다.
 */

@Component
public class SessionUtil {

  public static final String USER_ID = "userId";

  private SessionUtil() {
  }


  public static HttpSession getHttpSession() {
    HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes()).getRequest();

    if(req == null) throw new NullPointerException("HttpServletRequest 객체가 null입니다.");

    HttpSession session = req.getSession();
    return session;
  }

  /**
   * 해당 키값을 확인한 후 value값을 반환
   *
   * @param key session name을 지정.
   */
  public static String getAttribute(String key) {

    return (String) getHttpSession().getAttribute(key);
  }

  /**
   * 세션을 유효하지 않게 설정 (세션에 저장된 모든 값을 삭제). session.invalidate();
   */
  public static void invaldateSession() {

    getHttpSession().invalidate();
  }
}

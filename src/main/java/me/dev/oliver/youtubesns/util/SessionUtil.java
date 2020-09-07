package me.dev.oliver.youtubesns.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
 */
public class SessionUtil {

  private SessionUtil() {
  }


  private static HttpSession getHttpSession() {
    HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes()).getRequest();
    HttpSession session = req.getSession();
    return session;
  }

  /**
   * @param key session name을 지정.
   * @param obj 여러 종류의 객체를 저장하기 위해 지정.
   */
  public static void setAttribute(String key, Object obj) {

    getHttpSession().setAttribute(key, obj);
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

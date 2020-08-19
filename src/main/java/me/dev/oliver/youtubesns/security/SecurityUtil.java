package me.dev.oliver.youtubesns.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;

/**
 * MessageDigest객체 생성시 알고리즘을 "SHA-256"으로 해서 만듦. 해시된 데이터는 바이트 배열의 바이너리 데이터이므로 16진수 문자열로 변환.
 * [SHA256 알고리즘]
 * 이 알고리즘을 선택한 이유
 * 1. 개인정보보호 법으로 인하여 복호화 되지 않도록 단방향으로 구성되어야 함. 이로써 단방향으로 구성되어
 * 있고 복호화가 불가능함.
 * 2. 출력 속도가 빠름
 * 3. 현재까지 안정성 문제에서도 큰 단점이 발견되지 않았고, 속도가 빠르기 때문에 인증서, 블록체인 등
 * 많이 사용되고있으며, SHA-2라고 하면 SHA-256이라고 말할 정도로 상용화가 잘 되어 있음.
 *
 * [ StringBuilder vs StringBuffer ]
 * StringBuilder는 변경가능한 문자열이지만 동기화(synchronized)가 적용되지 않음. 하지만 StringBuffer는
 * thread-safe라는 말에서처럼 변경가능하지만 multiple thread환경에서 안전한 클래스. StringBuffer는 multi thread환경에서 다른 값을
 * 변경하지 못하도록 하므로 web이나 소켓환경과 같이 비동기로 동작하는 경우가 많을 때는 StringBuffer를 사용하는 것이 안전함.
 * 그러나 thread-safe는 thread-safe 속성으로 인해 StringBuffer 성능이 저하되므로 단점도 있음.
 * 따라서 StringBuilder는 각 클래스의 동일한 메서드를 호출 할 때 StringBuffer보다 빠름.
 *
 * String
 * 단순히 문자열을 참조하거나 탐색 및 검색이 잦을 때 Good.
 *
 * StringBuilder
 * 런타임 때, 반복적인 문자열 추가 연산이 많을 때 Good.
 * 단일 스레드 환경이라면 StringBuffer 보다 성능이 좋을 수 있음.
 *
 * StringBuffer
 * 멀티 스레드 환경에서 반복적인 문자 추가 연산이 많을 때 Good.
 */
@Slf4j
public class SecurityUtil {

  /** Util 클래스들은 private 생성자를 정의하여 객체 생성을 막음. */
  private SecurityUtil() {
  }

  /**
   * password를 받아 sha256 알고리즘을 통해 password를 암호화.
   *
   * @param data sha256 알고리즘을 통해 알호화할 password
   * @throws NoSuchAlgorithmException
   * 이 예외는 어느 암호 알고리즘이 요구되었음에도 불구하고, 현재의 환경에서는 사용 가능하지 않은 경우에 예외를 발생시킴.
   */
  public static String encryptSha256(String data) {
    String retVal = "";
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(data.getBytes());

      byte[] byteData = md.digest();
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < byteData.length; i++) {
        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
      }

      StringBuilder hexString = new StringBuilder();
      for (int i = 0; i < byteData.length; i++) {
        String hex = Integer.toHexString(0xff & byteData[i]);
        if (hex.length() == 1) {
          hexString.append('0');
        }
        hexString.append(hex);
      }

      retVal = hexString.toString();
    } catch (NoSuchAlgorithmException e) {
      log.error("EncBySHA256 Error:" + e.toString());
      throw new RuntimeException("SHA256 암호화 ERROR! {}", e);
    }
    return retVal;
  }
}

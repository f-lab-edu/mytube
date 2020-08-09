package me.dev.oliver.youtubesns.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {

  /* MessageDigest객체 생성시 알고리즘을 "SHA-256"으로 해서 만듦.
   해시된 데이터는 바이트 배열의 바이너리 데이터이므로 16진수 문자열로 변환.*/
  public static String encryptSha256(String data) throws Exception {
    String retVal = "";
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(data.getBytes());

      byte byteData[] = md.digest();
      StringBuffer sb = new StringBuffer();

      for(int i=0; i<byteData.length; i++) {
        sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
      }

      StringBuffer hexString = new StringBuffer();
      for(int i=0; i<byteData.length;i++) {
        String hex = Integer.toHexString(0xff & byteData[i]);
        if(hex.length() == 1) {
          hexString.append('0');
        }
        hexString.append(hex);
      }

      retVal = hexString.toString();
    } catch(NoSuchAlgorithmException e){
      System.out.println("EncBySHA256 Error:" + e.toString());
    }
    return retVal;
  }
}

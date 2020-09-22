package me.dev.oliver.youtubesns.service;

public interface LoginService {

  void sessionLogin(String key, Object value);

  String getSessionLoginId(String key);

  void sessionLogout();
}

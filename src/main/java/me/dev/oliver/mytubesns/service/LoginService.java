package me.dev.oliver.mytubesns.service;

public interface LoginService {

  void login(String key, Object value);

  String getLoginId();

  void logout();
}

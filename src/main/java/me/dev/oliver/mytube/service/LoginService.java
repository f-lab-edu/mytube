package me.dev.oliver.mytube.service;

public interface LoginService {

  void login(String key, Object value);

  String getLoginId();

  void logout();
}

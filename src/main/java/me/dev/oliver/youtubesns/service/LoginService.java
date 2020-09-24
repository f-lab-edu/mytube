package me.dev.oliver.youtubesns.service;

public interface LoginService {

  void login(String key, Object value);

  String getLoginId();

  void logout();
}

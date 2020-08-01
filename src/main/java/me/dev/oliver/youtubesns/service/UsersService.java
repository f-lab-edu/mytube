package me.dev.oliver.youtubesns.service;

import me.dev.oliver.youtubesns.dto.UsersDto;
import org.springframework.web.bind.annotation.PathVariable;

public interface UsersService {
  public UsersDto getUsersInfo(String id);
  public void postRegisterUser(UsersDto user);
  public void patchChangeUserPw(Integer id, String pw);
  public void deleteUser(Integer id);
}

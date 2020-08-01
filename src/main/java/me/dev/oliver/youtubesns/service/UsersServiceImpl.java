package me.dev.oliver.youtubesns.service;

import me.dev.oliver.youtubesns.dto.UsersDto;
import me.dev.oliver.youtubesns.mapper.UsersMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UsersServiceImpl implements UsersService{

  private UsersMapper usersMapper;

  public UsersServiceImpl(UsersMapper usersMapper) {
    this.usersMapper = usersMapper;
  }

  public UsersDto getUsersInfo(String id) {
    return usersMapper.getUser(id);
  }

  public void postRegisterUser(UsersDto user) {
    usersMapper.insertUser(user);
  }

  public void patchChangeUserPw(Integer id, String pw) {
    usersMapper.changePassword(id, pw);
  }

  public void deleteUser(Integer id) {
    usersMapper.deleteUser(id);
  }

}

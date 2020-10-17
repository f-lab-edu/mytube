package me.dev.oliver.youtubesns.service;

import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

  void insertVideo (MultipartFile multipartFile);
}

package me.dev.oliver.youtubesns.service;

import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

  void uploadVideo(MultipartFile multipartFile, String userId, String title, String detailContents);
}

package me.dev.oliver.youtubesns.service;

import me.dev.oliver.youtubesns.dto.VideoDto;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

  void uploadVideo(MultipartFile multipartFile, String userId, String title, String detailContents);

  VideoDto findByVideoUrl(int id);

  VideoDto findByMoreDetail(int id);
}

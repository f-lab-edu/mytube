package me.dev.oliver.youtubesns.service;

import me.dev.oliver.youtubesns.dto.VideoDto;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

  void insertVideo (MultipartFile multipartFile);

  void insertMoreDetail (VideoDto videoDto);

  VideoDto findByVideoUrl(int id);

  VideoDto findByMoreDetail(int id);
}

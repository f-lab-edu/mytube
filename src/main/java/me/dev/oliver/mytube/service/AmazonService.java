package me.dev.oliver.mytube.service;

import org.springframework.web.multipart.MultipartFile;

public interface AmazonService {

  String upload(MultipartFile uploadFile);
}

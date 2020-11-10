package me.dev.oliver.mytube.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadbleService {

  String upload(MultipartFile uploadFile);
}

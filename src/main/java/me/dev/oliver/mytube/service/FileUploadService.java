package me.dev.oliver.mytube.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

  String upload(MultipartFile uploadFile);
}

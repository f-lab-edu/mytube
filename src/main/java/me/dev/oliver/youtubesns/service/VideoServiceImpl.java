package me.dev.oliver.youtubesns.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import lombok.AllArgsConstructor;
import me.dev.oliver.youtubesns.dto.VideoDto;
import me.dev.oliver.youtubesns.mapper.VideoMapper;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Service
public class VideoServiceImpl implements VideoService {

  private final VideoMapper videoMapper;

  /**
   * 게시물 추가 및 쓰기
   *
   * @param multipartFile 게시물 관련 정보를 담은 객체
   */
  public void insertVideo (MultipartFile multipartFile) {

    File targetFile = new File("E:/test/" + multipartFile.getOriginalFilename());

    try {
      InputStream fileStream = multipartFile.getInputStream();
      multipartFile.transferTo(targetFile);
      URL url = targetFile.toURI().toURL();

      // url DB에 저장하기!!
      // 진행중......
    } catch (IOException e) {
      // 고민중
      //runtime 말고 다른 걸로 대체하기!!
      //throw new RuntimeException("~~");
    }
  }
}

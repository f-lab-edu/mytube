package me.dev.oliver.youtubesns.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dev.oliver.youtubesns.dto.VideoDto;
import me.dev.oliver.youtubesns.mapper.VideoMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@AllArgsConstructor
@Service
public class VideoServiceImpl implements VideoService {

  private final VideoMapper videoMapper;

  /**
   * 게시물 추가 및 쓰기, file size는 byte 단위로 저장됨
   *
   * @param multipartFile 게시물 관련 정보를 담은 객체
   */
  public void insertVideo(MultipartFile multipartFile) {

    String fileName = multipartFile.getOriginalFilename();

    if(fileName.isEmpty()) {
      log.error("insertVideo 메서드에서 file을 불러오던 중 null 값이 확인되었습니다");
      throw new IllegalArgumentException("file을 불러오지 못하여 에러가 발생했습니다.");
    }

    String storedPath = "E:/test/" + fileName;
    File targetFile = new File(storedPath);

    try {
      multipartFile.transferTo(targetFile);
      String fileUrl = targetFile.toURI().toURL().getFile();
      long fileSize = multipartFile.getSize();

      VideoDto videoDto = new VideoDto(fileUrl, fileSize);
      videoMapper.insertVideo(videoDto);
    } catch (IOException e) {
      log.error("insertVideo 메서드에서 file 처리 중 에러가 발생했습니다");
      throw new IllegalStateException("서버에서 파일 처리중 예상치 못한 에러가 발생했습니다");
    }
  }

  public void insertMoreDetail(VideoDto videoDto) {

    videoMapper.insertMoreDetail(videoDto);
  }
}

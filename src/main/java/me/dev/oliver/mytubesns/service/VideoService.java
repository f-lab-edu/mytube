package me.dev.oliver.mytubesns.service;

import java.io.File;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dev.oliver.mytubesns.aop.LoginValidation;
import me.dev.oliver.mytubesns.config.VideoConfig;
import me.dev.oliver.mytubesns.dto.VideoUploadDto;
import me.dev.oliver.mytubesns.dto.VideoWatchDto;
import me.dev.oliver.mytubesns.mapper.VideoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * {@literal @RequiredArgsConstructor} : final로 선언된 필드, NonNull 어노테이션을 사용한 필드만을 필요로 하는 생성자를 만듦
 * VideoProperties를 생성자 주입을 선택한 이유는 Value String 변수에 어노테이션만 사용하면 immutaeble(불변성)이 아니라는 문제가 있으므로 생성자로
 * 주입을 함
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class VideoService {

  private final VideoMapper videoMapper;
  private final VideoConfig videoConfig;

  /**
   * 동영상 업로드, file size는 byte 단위로 저장됨 동영상 컨텐츠 내의 세부사항 기록 db에 저장
   *
   * @param multipartFile  게시물 관련 정보를 담은 객체
   * @param userId         회원 아이디
   * @param title          동영상 제목
   * @param detailContents 동영상에 대한 세부 내용
   */
  @Transactional
  @LoginValidation
  public void uploadVideo(MultipartFile multipartFile,
      String userId,
      String title,
      String detailContents) {

    String fileName = multipartFile.getOriginalFilename();

    if (fileName.isEmpty()) {
      log.error("uploadVideo 메서드에서 {} file을 불러오던 중 null 값이 확인되었습니다", fileName);
      throw new IllegalArgumentException("서버에서 파일을 불러오지 못하여 에러가 발생했습니다");
    }

    File targetFile = new File(videoConfig.getFilePath(), fileName);

    try {
      multipartFile.transferTo(targetFile);
      String fileUrl = targetFile.toURI().toURL().getFile();
      long fileSize = multipartFile.getSize();

      VideoUploadDto videoUploadDto = VideoUploadDto.builder()
          .userId(userId)
          .title(title)
          .detailContents(detailContents)
          .fileUrl(fileUrl)
          .fileSize(fileSize)
          .build();
      videoMapper.insertVideo(videoUploadDto);
      videoMapper.insertDetailInfo(videoUploadDto);
    } catch (IOException e) {
      log.error("uploadVideo 메서드에서 {} file 처리 중 에러가 발생했습니다", fileName, e);
      throw new IllegalStateException("서버에서 파일 처리중 예상치 못한 에러가 발생했습니다");
    }
  }

  @LoginValidation
  @Transactional
  public VideoWatchDto watchVideo(int id) {

    String videoFileUrl = videoMapper.findVideoUrlById(id);
    VideoWatchDto videoDetailInfos = videoMapper.findDetailInfoById(id);

    return VideoWatchDto.builder()
        .id(id)
        .fileUrl(videoFileUrl)
        .userId(videoDetailInfos.getUserId())
        .title(videoDetailInfos.getTitle())
        .detailContents(videoDetailInfos.getDetailContents())
        .updatedAt(videoDetailInfos.getUpdatedAt())
        .likeCount(videoDetailInfos.getLikeCount())
        .badCount(videoDetailInfos.getLikeCount())
        .hits(videoDetailInfos.getHits())
        .build();
  }

}

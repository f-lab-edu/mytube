package me.dev.oliver.mytube.service;

import java.io.File;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dev.oliver.mytube.aop.LoginValidation;
import me.dev.oliver.mytube.config.VideoConfig;
import me.dev.oliver.mytube.dto.VideoLikeDto;
import me.dev.oliver.mytube.dto.VideoUploadDto;
import me.dev.oliver.mytube.dto.VideoWatchDto;
import me.dev.oliver.mytube.mapper.VideoMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * {@literal @RequiredArgsConstructor} : final로 선언된 필드, NonNull 어노테이션을 사용한 필드만을 필요로 하는 생성자를 만듦
 * VideoProperties를 생성자 주입을 선택한 이유는 Value String 변수에 어노테이션만 사용하면 immutaeble(불변성)이 아니라는 문제가 있으므로 생성자로
 * 주입을 함
 * <p>
 * {@literal @Transactional} : AOP 프록시를 통해 활성화 되고 내부에는 PlatformTransactionManager 구현과
 * TransactionInterceptor 를 이용하여 메서드 호출을 중심으로 AOP 프록시를 생성. Transactional 어노테이션을 선언적 트랜잭션이라 함. 사용할 때
 * 속성을 설정하지 않으면 기본으로 설정되어있는 속성들로 설정되어 생략이 가능. 트랜잭션의 동작방식에 영향을 줄수 있는 4가지 속성으로 TransactionAttributes
 * 프로퍼티를 통해 전파(propagation), 격리수준(isolation level), 제한시간(time out), 읽기 전용(read only)을 설정할 수 있음.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class VideoService {

  private final VideoMapper videoMapper;
  private final VideoConfig videoConfig;

  /**
   * 동영상 업로드 및 file size는 byte 단위로 저장됨, 동영상 컨텐츠 내의 세부사항 기록 db에 저장
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
  public VideoWatchDto getVideoInfo(int id) {

    return videoMapper.findVideoInfo(id);
  }

  /**
   * like 누를 userId와 동영상 videoId 정보 추가,
   * 좋아요를 한번도 누르지 않으면 null 값으로 받긴 때문에 null처리 유의,
   * 동영상 보기에서 Login 체크를 했으므로 중복 체크 필요 없음
   *
   * @param videoId videoId 정보
   * @param userId userId 정보
   * @throws IllegalArgumentException DuplicateKeyException이 아닌 다른 예외처리
   */
  public void addLikeCount(int videoId, String userId) {

    try {
      videoMapper.insertLike(new VideoLikeDto(videoId, userId));
    } catch (RuntimeException e) {
      if(e.getClass().equals(DuplicateKeyException.class)) {
        // 중복이면 좋아요 취소 하기 기능 추가, 취소하기 이슈에서 진행예정 !!!
      } else {
        log.error("addLikeCount 메서드에서 예상치 못한 에러가 발생했습니다", e);
        throw new IllegalArgumentException("서버에서 좋아요 처리중 예상치 못한 에러가 발생했습니다");
      }
    }
  }

  /**
   * dislike 누를 userId와 동영상 videoId 정보 추가,
   * 싫어요를 한번도 누르지 않으면 null 값으로 받긴 때문에 null처리 유의,
   * 동영상 보기에서 Login 체크를 했으므로 중복 체크 필요 없음
   *
   * @param videoId videoId 정보
   * @param userId userId 정보
   * @throws IllegalArgumentException DuplicateKeyException이 아닌 다른 예외처리
   */
  public void addDislikeCount(int videoId, String userId) {

    try {
      videoMapper.insertDislike(new VideoLikeDto(videoId, userId));
    } catch (RuntimeException e) {
      if(e.getClass().equals(DuplicateKeyException.class)) {
        // 중복이면 싫어요 취소 하기 기능 추가, 취소하기 이슈에서 진행예정 !!!
      } else {
        log.error("addDislikeCount 메서드에서 예상치 못한 에러가 발생했습니다", e);
        throw new IllegalArgumentException("서버에서 싫어요 처리중 예상치 못한 에러가 발생했습니다");
      }
    }
  }

}

package me.dev.oliver.mytube.controller;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import me.dev.oliver.mytube.dto.VideoWatchDto;
import me.dev.oliver.mytube.service.VideoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("video-contents")
public class VideoController {

  private final VideoService videoService;

  /**
   * 동영상 및 세부 사항 업로드
   *
   * @param multipartFile 동영상 파일을 및 userId, title, detail contents를 받아옴
   */
  @PostMapping
  public void uploadVideo(@RequestParam("fileVideo") MultipartFile multipartFile,
      @Valid @RequestParam String userId,
      @Valid @RequestParam String title,
      @Valid @RequestParam String detailContents) {

    videoService.uploadVideo(multipartFile, userId, title, detailContents);
  }
  
  /**
   * video를 보기 위한 컨텐츠를 모두 가져옴
   *
   * @param id DB에서 동영상 넘버링한 값을 넣어줌
   * @return 비디오를 시청하기 위한 정보들을 리턴
   */
  @GetMapping("{id}")
  public VideoWatchDto getVideoInfo(@PathVariable int id) {

    return videoService.getVideoInfo(id);
  }

}
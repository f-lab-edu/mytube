package me.dev.oliver.youtubesns.controller;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import me.dev.oliver.youtubesns.dto.VideoDto;
import me.dev.oliver.youtubesns.service.VideoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("videos-contents")
public class VideoController {

  private final VideoService videoService;

  /**
   * 동영상 업로드
   *
   * @param multipartFile 동영상 파일을 받아옴
   */
  @PostMapping("upload")
  public void uploadVideo(@RequestParam("videoFile") MultipartFile multipartFile) {

    videoService.insertVideo(multipartFile);
  }

  @PostMapping("more-details/upload")
  public void uploadMoreDetail(@Valid @RequestBody VideoDto videoDto) {

    videoService.insertMoreDetail(videoDto);
  }
}

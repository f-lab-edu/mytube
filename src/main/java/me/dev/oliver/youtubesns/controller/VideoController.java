package me.dev.oliver.youtubesns.controller;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import me.dev.oliver.youtubesns.dto.VideoDto;
import me.dev.oliver.youtubesns.service.VideoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  /**
   * 동영상 내의 세부 사항 업로드
   *
   * @param videoDto userId, title, detail contents를 받아옴
   */
  @PostMapping("more-details/upload")
  public void uploadMoreDetail(@Valid @RequestBody VideoDto videoDto) {

    videoService.insertMoreDetail(videoDto);
  }

  /**
   * video를 보기 위한 내용물을 모두 가져온다.
   *
   * @param id
   * @return
   */
  @GetMapping("{id}/videoUrl")
  public VideoDto videoUrl(@PathVariable int id) {

    return videoService.findByVideoUrl(id);
  }

  @GetMapping("{id}/more-detail")
  public VideoDto moreDetail(@PathVariable int id) {

    return videoService.findByMoreDetail(id);
  }
}

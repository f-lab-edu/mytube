package me.dev.oliver.youtubesns.controller;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import me.dev.oliver.youtubesns.dto.VideoDto;
import me.dev.oliver.youtubesns.service.VideoService;
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
  @PostMapping("upload") // @Valid @RequestBody VideoDto videoDto
  public void uploadVideo(@RequestParam("fileVideo") MultipartFile multipartFile,
      @Valid @RequestParam String userId, @Valid @RequestParam String title,
      @Valid @RequestParam String detailContents) {

    videoService.uploadVideo(multipartFile, userId, title, detailContents);
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

  /**
   *
   * @param id
   * @return
   */
  @GetMapping("{id}/more-detail")
  public VideoDto moreDetail(@PathVariable int id) {

    return videoService.findByMoreDetail(id);
  }

}

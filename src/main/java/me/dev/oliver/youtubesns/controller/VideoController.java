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
@RequestMapping("video")
public class VideoController {

  private final VideoService videoService;

  @PostMapping("my-video/upload")
  public String uploadVideo(@RequestParam MultipartFile multipartFile) {

    videoService.insertVideo(multipartFile);

    return "redirect:my-contents/upload";
  }

  @PostMapping("my-contents/upload")
  public void uploadContents(@Valid @RequestBody VideoDto videoDto) {
    // 작성중...
  }
}

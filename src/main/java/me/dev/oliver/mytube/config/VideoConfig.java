package me.dev.oliver.mytube.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Setter를 제거하여 중간에 값이 변경되는 위험상황을 회피
 */
@Getter
@Configuration
public class VideoConfig {

  private final String filePath;

  public VideoConfig (@Value("${video.file.path}") String filePath) {

    this.filePath = filePath;
  }
}

package me.dev.oliver.youtubesns.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Setter를 제거하여 중간에 값이 변경되는 위험상황을 회피
 */
@Getter
@RequiredArgsConstructor
public class VideoConfig {

  private final String filePath;
}

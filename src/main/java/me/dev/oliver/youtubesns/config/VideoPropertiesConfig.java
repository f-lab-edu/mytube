package me.dev.oliver.youtubesns.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * Setter를 제거하여 중간에 값이 변경되는 위험상황을 회피
 * {@literal @ConstructorBinding} 어노테이션을 이용하면 final 필드에 값을 주입 -> immutable
 */
@Getter
@RequiredArgsConstructor
public class VideoPropertiesConfig {

  private final String filePath;
}

package me.dev.oliver.youtubesns.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * VideoProperties 클래스를 스프링 빈으로 등록
 */
@Configuration
@EnableConfigurationProperties(value = {VideoProperties.class})
public class PropertiesConfiguration {
}

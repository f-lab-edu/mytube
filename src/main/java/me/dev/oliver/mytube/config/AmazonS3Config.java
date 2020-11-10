package me.dev.oliver.mytube.config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * {@literal @PropertySource} 어노테이션은 properties파일의 path를 지정하여 Environment 객체에 프로퍼티 값이 자동으로 주입됨.
 */
@Getter
@Configuration
@PropertySource("classpath:application-aws.properties")
public class AmazonS3Config {

}

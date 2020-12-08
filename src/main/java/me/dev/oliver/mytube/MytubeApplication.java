package me.dev.oliver.mytube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * {@literal @EnableCaching} 어노테이션을 선언하면 스프링 부트에서는 @Cacheable과 같은 캐싱 어노테이션의 사용을 인식하게 됨.
 */
@EnableCaching
@SpringBootApplication
public class MytubeApplication {

  public static void main(String[] args) {

    SpringApplication.run(MytubeApplication.class, args);
  }

}

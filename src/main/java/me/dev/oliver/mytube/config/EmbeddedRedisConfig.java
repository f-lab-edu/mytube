package me.dev.oliver.mytube.config;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;


@Configuration
public class EmbeddedRedisConfig {

  @Value("${spring.redis.port}")
  private int port;

  private RedisServer redisServer;

  @PostConstruct
  public void redisServer() throws IOException {
    redisServer = new RedisServer(port);
    redisServer.start();
  }

  @PreDestroy
  public void stopRedis() {
    if (redisServer != null) {
      redisServer.stop();
    }
  }
}
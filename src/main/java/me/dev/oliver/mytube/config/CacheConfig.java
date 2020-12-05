package me.dev.oliver.mytube.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * redis cache를 사용하기 위해서 CacheManager 등록
 */
@Configuration
public class CacheConfig {

  @Autowired
  private RedisConnectionFactory lettuceConnectionFactory;

  /**
   * CacheManager를 Redis 캐시로 사용.
   *
   * @return RedisCacheManager
   */
  @Bean
  public RedisCacheManager redisCacheManager() {

    return RedisCacheManager.RedisCacheManagerBuilder
        .fromConnectionFactory(lettuceConnectionFactory).build();
  }

}

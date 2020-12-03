package me.dev.oliver.mytube.config;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis cache를 사용하기 위해서 CacheManager 등록
 */
@Configuration
public class CacheConfig {

  @Value("${spring.cache.redis.time-to-live}")
  private long defaultExpireSecond;

  @Autowired
  private LettuceConnectionFactory lettuceConnectionFactory;

  @Autowired
  private RedisConfig redisConfig;

  /**
   * CacheManager를 Redis 캐시로 사용.
   * <p>
   * disableCachingNullValues : Null 캐싱을 제외하면 불필요한 접근을 절약 할 수 있음. redis key는 String으로 사용하고 value는 여러
   * 객체를 사용할 수 있는 제네릭한 방법을 사용.
   *
   * @return
   */
  @Bean
  public RedisCacheManager redisCacheManager() {

    RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
        .disableCachingNullValues()
        .entryTtl(Duration.ofSeconds(defaultExpireSecond))
        .serializeKeysWith(RedisSerializationContext.SerializationPair
            .fromSerializer(new StringRedisSerializer()))
        .serializeValuesWith(RedisSerializationContext.SerializationPair
            .fromSerializer(
                new GenericJackson2JsonRedisSerializer(redisConfig.cacheObjectMapper())));

    return RedisCacheManager.RedisCacheManagerBuilder
        .fromConnectionFactory(lettuceConnectionFactory)
        .cacheDefaults(configuration).build();


  }

}

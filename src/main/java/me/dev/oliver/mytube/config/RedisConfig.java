package me.dev.oliver.mytube.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {

  @Value("${spring.redis.host}")
  private String host;

  @Value("${spring.redis.port}")
  private int port;

  /**
   * Redis Connection Factory library 특징 [Lettuce] 동기식, 비동기식 및 반응 형 인터페이스를 지원. netty를 기반으로 하는 확장 가능한
   * 스레드로부터 안전한 Redis 클라이언트. 비동기로 요청하기 때문에 Jedis에 비해 높은 성능을 가지고 있음.
   * <p>
   * [Jedis] 파이프 라인을 제외하고 모두 동기식. 다중 스레드 응용 프로그램을 잘 처리 할 수 있지만 Jedis 연결은 스레드로부터 안전하지 않음. Connection
   * pool을 사용하여 성능, 안정성 개선이 가능하지만 하드웨어적인 cpu 자원이 더 많이 필요함.
   *
   * @return LettuceConnectionFactory 반환.
   */
  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {

    return new LettuceConnectionFactory(new RedisStandaloneConfiguration(host, port));
  }

}

package me.dev.oliver.mytube.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

  @Value("${spring.redis.host}")
  private String host;

  @Value("${spring.redis.port}")
  private int port;

  /**
   * Redis Connection Factory library 특징
   * [Lettuce]
   * 동기식, 비동기식 및 반응 형 인터페이스를 지원.
   * netty를 기반으로 하는 확장 가능한 스레드로부터 안전한 Redis 클라이언트.
   * 비동기로 요청하기 때문에 Jedis에 비해 높은 성능을 가지고 있음.
   * <p>
   * [Jedis]
   * 파이프 라인을 제외하고 모두 동기식.
   * 다중 스레드 응용 프로그램을 잘 처리 할 수 있지만 Jedis 연결은 스레드로부터 안전하지 않음.
   * Connection pool을 사용하여 성능, 안정성 개선이 가능하지만 하드웨어적인 cpu 자원이 더 많이 필요함.
   *
   * @return LettuceConnectionFactory 반환.
   */
  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {

    return new LettuceConnectionFactory(new RedisStandaloneConfiguration(host, port));
  }

  /*
   * ObjectMapper : Object와 Json간 변환을 담당.
   * ObjectMapper는 기본 POJO (Plain Old Java Objects) 또는 범용 JSON 트리 모델 (JsonNode) 간에 JSON을 읽고 쓰기위한 기능과
   * 변환을 수행하기위한 관련 기능을 제공.
   *
   * PolymorphicTypeValidator : activateDefaultTyping()을 사용하기 위해서 하위타입이 역직렬화 될 수 있는지 여부를 결정하는데 사용.
   * BasicPolymorphicTypeValidator : 편의를 위해 구성 가능한 표준 구현이 포함 되어 있으며 일반적인 "허용 목록(클래스 등)"접근 방식을 지원.
   *
   * objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY) : 생성자, getter, setter, 필드등의 모든 접근자를 허용.
   */
  @Bean
  public ObjectMapper objectMapper() {

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectMapper.registerModules(new JavaTimeModule(), new Jdk8Module());

    return objectMapper;
  }

  /**
   * Redis 데이터 액세스 코드를 단순화하는 도우미 클래스.
   * 주어진 개체와 Redis 저장소의 기본 바이너리 데이터간에 자동 직렬화 / 역 직렬화를 수행. 기본적으로 객체에 대해 Java 직렬화를 사용.
   * 저장할 객체가 여러개일 경우 범용 JacksonSerializer인 GenericJackson2JsonRedisSerializer를 이용.
   * 일단 구성되면이 클래스는 스레드로부터 안전.
   * 템플릿이 생성되는 동안 주어진 개체와 바이너리 데이터를 적절하게 변환하는 것은 serializer / deserializer에 달려 있음.
   * json 형식으로 데이터를 받을 때 값이 깨지지 않도록 직렬화.
   *
   * @param objectMapper Json과 Object의 직렬화 / 역직력화를 사용하기 위해 사용
   * @return
   */
  @Bean
  public RedisTemplate<String, Object> redisTemplate(ObjectMapper objectMapper) {

    GenericJackson2JsonRedisSerializer serializer =
        new GenericJackson2JsonRedisSerializer(objectMapper);

    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());

    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(serializer);
    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashValueSerializer(serializer);

    redisTemplate.setEnableTransactionSupport(true);

    return redisTemplate;
  }

}

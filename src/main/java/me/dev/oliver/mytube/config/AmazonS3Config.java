package me.dev.oliver.mytube.config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * {@literal @PropertySource} 어노테이션은 properties파일의 path를 지정하여 Environment 객체에 프로퍼티 값이 자동으로 주입됨.
 * <p>
 * PropertySource vs ConfigurationProperties vs Value
 * PropertySource는 properties 파일을 사용하기 위해 클래스에 지정할 때 사용하는 것이 깔끔하며 Value 어노테이션과 Environment를 이용해 값을
 * 읽어 올수 있음. ConfigurationProperties는 예를들어 properties 또는 yml 파일에 file.path, file.url, file.size와 같이
 * 앞에 동일한 file이라는 키워드와 함께 뒤에 path, url, size와 같이 표현되어 있을 때 자동으로 필드에 주입되기 때문에 사용이 편리함. Value 어노테이션은
 * properties 또는 yml에 등록되어 있을 때 예를들어 {@literal @Value("${cloud.aws.credentials.accessKey}")} 이런식으로
 * 값들을 하나하나 가져올 수 있는 편리함이 있음. 이러한 방법들을 모두 알고있을 때 성능을 테스트 해보지는 못했지만 상황에 맞게 코드가 깔끔해지는 방식을 고려할 수 있음.
 */
@Getter
@Configuration
@PropertySource("classpath:application-aws.properties")
public class AmazonS3Config {

}

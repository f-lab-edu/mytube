package me.dev.oliver.mytube.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AmazonS3Config {

  private final String accessKey;
  private final String secretKey;
  private final String bucket;
  private final String region;


  public AmazonS3Config(@Value("${cloud.aws.credentials.accessKey}") String accessKey,
      @Value("${cloud.aws.credentials.secretKey}") String secretKey,
      @Value("${cloud.aws.s3.bucket}") String bucket,
      @Value("${cloud.aws.region.static}") String region) {

    this.accessKey = accessKey;
    this.secretKey = secretKey;
    this.bucket = bucket;
    this.region = region;
  }
}

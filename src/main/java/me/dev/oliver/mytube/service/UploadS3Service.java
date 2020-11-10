package me.dev.oliver.mytube.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dev.oliver.mytube.config.AmazonS3Config;
import me.dev.oliver.mytube.util.AmazonS3Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Service
public class UploadS3Service implements UploadbleService {

  //private final AmazonS3Config amazonS3Config;
  @Autowired
  Environment env;
  private AmazonS3 s3Client;

  /**
   * s3 정보 초기화 의존성 주입이 이루어진 후 초기화를 수행, bean이 한 번만 초기화.
   */
  @PostConstruct
  private void initializeS3Client() {
    AWSCredentials credentials = new BasicAWSCredentials(env.getProperty(AmazonS3Keys.accessKey),
        env.getProperty(AmazonS3Keys.secretKey));

    s3Client = AmazonS3ClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(credentials))
        //.withRegion(amazonS3Config.getRegion())
        .withRegion(env.getProperty(AmazonS3Keys.region))
        .build();
  }

  /**
   * @param uploadFile
   * @return s3 url 문자열 반환.
   * @throws IOException uploadFile.getInputStream()에서 파일 예외 처리
   */
  public String upload(MultipartFile uploadFile) {
    String fileName = uploadFile.getOriginalFilename();
    String bucket = env.getProperty(AmazonS3Keys.bucket);

    try {
      s3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile.getInputStream(), null)
          .withCannedAcl(CannedAccessControlList.PublicRead));
    } catch (IOException e) {
      log.error("uploadS3 메서드에서 {}을 업로드하던 중 에러가 발생했습니다", fileName, e);
      throw new IllegalStateException("서버에서 파일 처리중 예상치 못한 에러가 발생했습니다");
    }

    return s3Client.getUrl(bucket, fileName).toString();
  }
}

package me.dev.oliver.mytubesns.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * LocalDateTime vs Date
 * Date타입은 mutable하고 LocalDateTime은 immutable함. 가변 클래스인 Date는 deprecated되었고
 * LocalDateTime, LocalDate, LocalTime은 Java8에 추가 됨. Date 클래스는 Depensive copying하기 위해 밖으로 객체를 내보낼 때
 * 새로운 객체를 만들어 사용했지만 Java8에서 추가된 LocalDateTime은 immutable하여 값이 변경 되지 않아 사용하는데 안전.
 */
@Getter
public class VideoWatchDto {

  private final String userId;

  @NotBlank
  private final String title;

  @NotBlank
  private final String detailContents;

  private final String fileUrl;

  private final LocalDateTime updatedAt;

  private final int likeCount;

  private final int badCount;

  //조회수
  private final int hits;

  public VideoWatchDto(
      String userId,
      String title,
      String detailContents,
      LocalDateTime updatedAt,
      int likeCount,
      int badCount,
      int hits,
      String fileUrl) {

    this.userId = userId;
    this.title = title;
    this.detailContents = detailContents;
    this.updatedAt = updatedAt;
    this.likeCount = likeCount;
    this.badCount = badCount;
    this.hits = hits;
    this.fileUrl = fileUrl;
  }
}

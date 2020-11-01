package me.dev.oliver.mytubesns.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

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

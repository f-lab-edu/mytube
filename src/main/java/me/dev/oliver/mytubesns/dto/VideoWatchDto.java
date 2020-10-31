package me.dev.oliver.mytubesns.dto;

import java.time.LocalDate;
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

  private LocalDate createdAt;

  private final LocalDate updatedAt;

  private final int likeCount;

  private final int badCount;

  //조회수
  private final int hits;

  public VideoWatchDto(
      String userId,
      String title,
      String fileUrl,
      String detailContents,
      LocalDate updatedAt,
      int likeCount,
      int badCount,
      int hits) {

    this.userId = userId;
    this.title = title;
    this.fileUrl = fileUrl;
    this.detailContents = detailContents;
    this.updatedAt = updatedAt;
    this.likeCount = likeCount;
    this.badCount = badCount;
    this.hits = hits;
  }
}

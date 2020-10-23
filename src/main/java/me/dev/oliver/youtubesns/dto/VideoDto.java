package me.dev.oliver.youtubesns.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class VideoDto {

  private final String userId;

  @NotBlank
  private final String title;

  @NotBlank
  private final String detailContents;

  private String createdAt;

  private String updatedAt;

  private int likeCount;

  private int badCount;

  /**
   * 조회수
   */
  private int hits;

  private final String fileUrl;

  private final long fileSize;

  public VideoDto(String fileUrl, long fileSize, String userId, String title,
      String detailContents) {
    this.fileUrl = fileUrl;
    this.fileSize = fileSize;
    this.userId = userId;
    this.title = title;
    this.detailContents = detailContents;
  }

}

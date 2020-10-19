package me.dev.oliver.youtubesns.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class VideoDto {

  private String userId;

  @NotBlank
  private String title;

  @NotBlank
  private String detailContents;

  private String createdAt;

  private String updatedAt;

  private int likeCount;

  private int badCount;

  /**
   * 조회수
   */
  private int hits;

  private String fileUrl;

  private long fileSize;

  public VideoDto(String fileUrl, long fileSize) {
    this.fileUrl = fileUrl;
    this.fileSize = fileSize;
  }

}

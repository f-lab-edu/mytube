package me.dev.oliver.youtubesns.dto;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
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

}

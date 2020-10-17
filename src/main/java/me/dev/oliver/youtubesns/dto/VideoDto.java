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

  private Integer likeCount;

  private Integer badCount;

  /**
   * 조회수
   */
  private Integer hits;

  private String fileUrl;

}

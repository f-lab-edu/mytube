package me.dev.oliver.youtubesns.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class BoardDto {

  private String userId;

  @NotBlank
  private String title;

  @NotBlank
  private String detailContents;

  private Integer likeCount;

  private Integer badCount;

  /**
   * 조회수
   */
  private Integer hits;

}

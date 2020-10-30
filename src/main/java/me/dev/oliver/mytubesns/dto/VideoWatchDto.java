package me.dev.oliver.mytubesns.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VideoWatchDto {

  private final Integer id;

  private final String userId;

  @NotBlank
  private final String title;

  @NotBlank
  private final String detailContents;

  private final String fileUrl;

  private final LocalDate createdAt;

  private final LocalDate updatedAt;

  private final Integer likeCount;

  private final Integer badCount;

  /**
   * 조회수
   */
  private final int hits;
}

package me.dev.oliver.mytubesns.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
@AllArgsConstructor
public class VideoWatchDto {

  private final int id;

  private final String userId;

  @NotBlank
  private final String title;

  @NotBlank
  private final String detailContents;

  private final String fileUrl;

  private final LocalDate createdAt;

  private final LocalDate updatedAt;

  private final int likeCount;

  private final int badCount;

  /**
   * 조회수
   */
  private final int hits;
}

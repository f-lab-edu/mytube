package me.dev.oliver.mytube.dto;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VideoUploadDto {

  private final String userId;

  @NotBlank
  private final String title;

  @NotBlank
  private final String detailContents;

  private final String fileUrl;

  private final long fileSize;
}

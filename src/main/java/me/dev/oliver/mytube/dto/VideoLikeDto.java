package me.dev.oliver.mytube.dto;

import lombok.Getter;

@Getter
public class VideoLikeDto {

  private final int videoId;

  private final String userId;

  public VideoLikeDto(int videoId, String userId) {
    this.videoId = videoId;
    this.userId = userId;
  }

}
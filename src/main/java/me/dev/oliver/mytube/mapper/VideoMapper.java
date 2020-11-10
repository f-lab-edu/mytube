package me.dev.oliver.mytube.mapper;

import me.dev.oliver.mytube.dto.VideoLikeDto;
import me.dev.oliver.mytube.dto.VideoUploadDto;
import me.dev.oliver.mytube.dto.VideoWatchDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface VideoMapper<videoLikeBadDto> {

  int insertVideo(VideoUploadDto videoUploadDto);

  int insertDetailInfo(VideoUploadDto videoUploadDto);

  VideoWatchDto findVideoInfo(int id);

  int insertLike(VideoLikeDto videoLikeDto);

  int deleteLike(VideoLikeDto videoLikeDto);
}

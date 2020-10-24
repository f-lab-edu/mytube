package me.dev.oliver.youtubesns.mapper;

import me.dev.oliver.youtubesns.dto.VideoDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface VideoMapper {

  int insertVideo(VideoDto videoDto);

  int insertDetailInfo(VideoDto videoDto);

  VideoDto findByVideoUrl(int id);

  VideoDto findByMoreDetail(int id);
}

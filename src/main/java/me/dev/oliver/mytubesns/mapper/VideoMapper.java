package me.dev.oliver.mytubesns.mapper;

import me.dev.oliver.mytubesns.dto.VideoDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface VideoMapper {

  int insertVideo(VideoDto videoDto);

  int insertDetailInfo(VideoDto videoDto);
}

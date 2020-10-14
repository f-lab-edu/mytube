package me.dev.oliver.youtubesns.mapper;

import me.dev.oliver.youtubesns.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BoardMapper {

  int insertBoard(BoardDto boardDto);
}

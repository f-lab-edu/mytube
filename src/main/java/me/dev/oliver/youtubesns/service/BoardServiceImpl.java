package me.dev.oliver.youtubesns.service;

import lombok.AllArgsConstructor;
import me.dev.oliver.youtubesns.dto.BoardDto;
import me.dev.oliver.youtubesns.mapper.BoardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Transactional
@Service
public class BoardServiceImpl implements BoardService{

  private final BoardMapper boardMapper;

  /**
   * 게시물 추가 및 쓰기
   *
   * @param boardDto 게시물 관련 정보를 담은 객체
   */
  public void insertBoard (BoardDto boardDto) {

    boardMapper.insertBoard(boardDto);
  }
}

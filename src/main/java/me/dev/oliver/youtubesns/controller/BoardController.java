package me.dev.oliver.youtubesns.controller;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import me.dev.oliver.youtubesns.dto.BoardDto;
import me.dev.oliver.youtubesns.service.BoardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("board")
public class BoardController {

  private final BoardService boardService;

  @PostMapping
  public void write(@Valid @RequestBody BoardDto boardDto) {

    boardService.insertBoard(boardDto);
  }
}

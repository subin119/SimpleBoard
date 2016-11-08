package com.ktds.board.service;

import javax.servlet.http.HttpSession;

import com.ktds.board.vo.BoardListVO;
import com.ktds.board.vo.BoardVO;

public interface BoardService {

	public BoardListVO getAllBoards();

	public boolean addNewBoard(BoardVO board, HttpSession session);

	public boolean deleteBoardById(String boardId);

	public BoardVO getBoardById(String boardId, HttpSession session);
	
	public BoardVO getFileNames(String boardId);
}

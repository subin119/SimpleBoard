package com.ktds.board.dao;

import java.util.List;
import java.util.Map;

import com.ktds.board.vo.BoardListVO;
import com.ktds.board.vo.BoardVO;

public interface BoardDao {

	public List<BoardVO> getAllBoards();

	public int addNewBoard(BoardVO board);

	public int deleteBoardById(String boardId);

	public BoardVO getBoardById(String boardId);

	public int updateHitCount(String boardId, String userId);

	public int addReadHistory(String boardId, String userId);

}

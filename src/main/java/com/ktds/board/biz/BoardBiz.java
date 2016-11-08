package com.ktds.board.biz;

import com.ktds.board.vo.BoardListVO;
import com.ktds.board.vo.BoardVO;

public interface BoardBiz {

	public BoardListVO getAllBoards();

	public boolean addNewBoard(BoardVO board);

	public boolean deleteBoardById(String boardId);

	public BoardVO getBoardById(String boardId, String userId);

	public boolean updateHitCount(String boardId, String userId);

	public BoardVO getFileNames(String boardId);


}

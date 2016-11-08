package com.ktds.board.service.impl;

import javax.servlet.http.HttpSession;

import com.ktds.board.biz.BoardBiz;
import com.ktds.board.service.BoardService;
import com.ktds.board.vo.BoardListVO;
import com.ktds.board.vo.BoardVO;
import com.ktds.user.biz.UserBiz;
import com.ktds.user.vo.UserVO;

public class BoardServiceImpl implements BoardService{
	
	private BoardBiz boardBiz;
	private UserBiz userBiz;

	public void setBoardBiz(BoardBiz boardBiz) {
		this.boardBiz = boardBiz;
	}
	
	
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}


	@Override
	public BoardListVO getAllBoards() {
		return boardBiz.getAllBoards();
	}
	
	@Override
	public boolean addNewBoard(BoardVO board, HttpSession session) {
		UserVO user = (UserVO) session.getAttribute("_USER_");
		String userId = user.getUserId();
		int point = user.getPoint();
		userBiz.updatePoint(userId, point);
		return boardBiz.addNewBoard(board);
	}
	
	@Override
	public boolean deleteBoardById(String boardId) {
		return boardBiz.deleteBoardById(boardId);
	}
	
	@Override
	public BoardVO getBoardById(String boardId, HttpSession session) {
		
		UserVO user = (UserVO) session.getAttribute("_USER_");
		String userId = user.getUserId();
		return boardBiz.getBoardById(boardId,userId);
	}
	
	@Override
	public BoardVO getFileNames(String boardId) {
		return boardBiz.getFileNames(boardId);
	}
}

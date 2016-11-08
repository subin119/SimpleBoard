package com.ktds.board.biz.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.ktds.board.biz.BoardBiz;
import com.ktds.board.dao.BoardDao;
import com.ktds.board.vo.BoardListVO;
import com.ktds.board.vo.BoardVO;

public class BoardBizImpl implements BoardBiz{
	
	private BoardDao boardDao;

	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	@Override
	public BoardListVO getAllBoards() {
		
		BoardListVO boardList = new BoardListVO();
		boardList.setBoardList(boardDao.getAllBoards());		
		return boardList;
	}
	
	@Override
	public boolean addNewBoard(BoardVO board) {
		
		MultipartFile uploadFile = board.getFileUpload();
		if(!uploadFile.isEmpty()) {
			//(\\를 사용하지않고 separator를 쓴다!!)
			File dir = new File("D:" + File.separator + "uploadFile2");
			if(!dir.exists()) {
				dir.mkdirs();
			}
			String uploadPath = dir.getAbsolutePath();
			String encryptFileName = UUID.randomUUID().toString();
			String realFileName = uploadFile.getOriginalFilename();
			
			File file = new File(uploadPath + File.separator + encryptFileName);
			try {
				uploadFile.transferTo(file);
			} catch (IllegalStateException e) {
				throw new RuntimeException(e.getMessage(), e);
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
			
			//서버와 사용자 입장이 다르다!!!
			board.setDisplayFileName(realFileName);
			board.setRealFileName(encryptFileName);
		}
		return boardDao.addNewBoard(board) > 0;
	}
	
	@Override
	public boolean deleteBoardById(String boardId) {
		return boardDao.deleteBoardById(boardId) > 0;
	}
	
	@Override
	public BoardVO getBoardById(String boardId, String userId) {
		boardDao.updateHitCount(boardId,userId);
		boardDao.addReadHistory(boardId,userId);
		return boardDao.getBoardById(boardId);
	}
	
	@Override
	public boolean updateHitCount(String boardId, String userId) {
		return boardDao.updateHitCount(boardId, userId) > 0;
	}
	
	@Override
	public BoardVO getFileNames(String boardId) {
		return boardDao.getBoardById(boardId);
	}
}

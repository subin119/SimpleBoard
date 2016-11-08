package com.ktds.board.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.board.dao.BoardDao;
import com.ktds.board.vo.BoardVO;

public class BoardDaoImpl extends SqlSessionDaoSupport implements BoardDao{
	
	@Override
	public List<BoardVO> getAllBoards() {
		return getSqlSession().selectList("boardDao.getAllBoards");
	}
	
	@Override
	public int addNewBoard(BoardVO board) {
		return getSqlSession().insert("boardDao.addNewBoard", board);
	}
	
	@Override
	public int deleteBoardById(String boardId) {
		return getSqlSession().delete("boardDao.deleteBoardById", boardId);
	}
	
	@Override
	public BoardVO getBoardById(String boardId) {
		return getSqlSession().selectOne("boardDao.getBoardById", boardId);
	}
	
	@Override
	public int updateHitCount(String boardId, String userId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("boardId", boardId);
		parameter.put("userId", userId);
		return getSqlSession().update("boardDao.updateHitCount", parameter);
	}
	
	@Override
	public int addReadHistory(String boardId, String userId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("boardId", boardId);
		parameter.put("userId", userId);
		return getSqlSession().insert("boardDao.addReadHistory",parameter);
	}
}

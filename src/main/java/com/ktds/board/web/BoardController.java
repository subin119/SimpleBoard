package com.ktds.board.web;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.board.service.BoardService;
import com.ktds.board.vo.BoardListVO;
import com.ktds.board.vo.BoardVO;
import com.ktds.common.util.DownloadUtil;

@Controller
public class BoardController {

	private BoardService boardService;

	private Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@RequestMapping("/board")
	public ModelAndView viewListPage() {
		
		BoardListVO boardList = boardService.getAllBoards();
		
		ModelAndView view = new ModelAndView();
		view.setViewName("board/list");
		view.addObject("boardList", boardList);
		return view;
	}
	
	@RequestMapping("/board/writeBoard")
	public String viewAddNewBoard() {
		return "board/writeBoard";
	}
	
	@RequestMapping("/board/doWriteBoard")
	public ModelAndView doWriteBoard(BoardVO board, HttpSession session) {
		
		ModelAndView view = new ModelAndView();
		
		boolean isSuccess = boardService.addNewBoard(board,session);
		
		view.setViewName("redirect:/board");
		return view;
	}
	
	@RequestMapping("/board/detailBoard/{boardId}")
	public ModelAndView DetailBoardPage(@PathVariable String boardId, HttpSession session) {
		
		BoardVO board = boardService.getBoardById(boardId, session);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("board/detailBoard");
		view.addObject("board",board);
		return view;
	}
	
	@RequestMapping("/deleteBoard/{boardId}") //PathVariabel은 url에 do가 붙지 않는다!!!
	public ModelAndView doDeleteBoard(@PathVariable String boardId) {
		
		boolean isSuccess = boardService.deleteBoardById(boardId);
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/board");
		return view;
	}
	
	@RequestMapping("/board/doDownLoad/{boardId}")
	public void doDownLoad(@PathVariable String boardId, HttpServletRequest request, HttpServletResponse response) {
		
		BoardVO board = boardService.getFileNames(boardId);
		DownloadUtil downloadFile = DownloadUtil.getInstance("d:"+ File.separator + "uploadfile2");
		try {
			downloadFile.download(request, response, board.getRealFileName(), board.getDisplayFileName());
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}
	
}

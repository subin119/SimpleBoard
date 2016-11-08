package com.ktds.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.common.util.CookieUtil;
import com.ktds.user.service.UserService;
import com.ktds.user.vo.UserVO;

@Controller
public class UserController {
	private Logger logger  = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/login")
	public ModelAndView viewLoginPage(HttpServletRequest request){
		ModelAndView view = new ModelAndView();

		view.addObject("_USER_ID_", CookieUtil.getCookie(request, "_USER_ID_"));
		view.addObject("_REMEMBER_YN_", CookieUtil.getCookie(request, "_REMEMBER_YN_"));
		view.setViewName("user/login");
		return view;
	}

	/**
	 * 쿠키를 만들어 브라우저에게 보낼 때 필요한 클래스 3가지
	 * 1. HttpServletRequest
	 * 2. HttpServletResponse
	 * 3. Cookie
	 */
	@RequestMapping("/doLogin")
	public ModelAndView doLoginAction(@Valid UserVO userVO,Errors errors, HttpServletResponse response, HttpSession session){
		ModelAndView view = new ModelAndView();
		
		boolean isSuccess = userService.doLogin(userVO, session);
		
		if(isSuccess){
			//1. 사용자가 "아이디 기억하기"를 체크했는지 확인다.
			if(userVO.isRememberUserId()){
				//2. "아이디 기억하가"를 체크했다면, 쿠키를 굽는다.
				CookieUtil.addCookie(response, "_USER_ID_", userVO.getUserId());
				CookieUtil.addCookie(response, "_REMEMBER_YN_", "Y");
			} 
			else {
				
				//2. "아이디 기억하가"를 체크 해제 했다면, 쿠키를 지운다.
				CookieUtil.removeCookie(response, "_USER_ID_");
				CookieUtil.removeCookie(response, "_REMEMBER_YN_");
			}
			view.setViewName("redirect:/board");
		}
		else{
			view.setViewName("redirect:login");
		}
		return view;
	}
	
	/**
	 * 이 url은 get으로만 들어올 수 있다.
	 * @return
	 */
	@RequestMapping(value="/signUp", method=RequestMethod.GET)
	public String viewSignUpPage(){
		return "user/signUp";
	}
	
	/**
	 * 이 url은 post로만 들어올 수 있다.
	 * @param userVO
	 * @return
	 */
	@RequestMapping(value="/doSignUp", method=RequestMethod.POST)
	public ModelAndView doSignUpAction(UserVO userVO){
		boolean isSuccess = userService.txSignUp(userVO);
		
		ModelAndView view = new ModelAndView();
		
		view.setViewName("redirect:login");
		
		return view;
	}

}

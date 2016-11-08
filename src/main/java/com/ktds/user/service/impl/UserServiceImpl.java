package com.ktds.user.service.impl;

import javax.servlet.http.HttpSession;

import com.ktds.common.util.SHA256Util;
import com.ktds.user.biz.UserBiz;
import com.ktds.user.service.UserService;
import com.ktds.user.vo.UserVO;

public class UserServiceImpl implements UserService{
	
	UserBiz userBiz;
	
	
	
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}



	/**
	 * 암호화 과정이 필요하다.
	 */
	public boolean txSignUp(UserVO userVO) {
		return userBiz.txSignUp(userVO);
	}



	@Override
	public boolean doLogin(UserVO userVO, HttpSession session) {
		UserVO loginUser = userBiz.doLogin(userVO);
		if(loginUser != null){
			session.setAttribute("_USER_", loginUser);
		}
		
		return loginUser != null;
	}



}

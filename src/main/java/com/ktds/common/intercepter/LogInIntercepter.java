package com.ktds.common.intercepter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ktds.user.vo.UserVO;

public class LogInIntercepter extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");
		if(user == null) {
			PrintWriter writer = response.getWriter();
			writer.append("<script>");
			writer.append("alert('로그인이 필요합니다!');");
			writer.append("location.href='/SimpleBoard/login';");
			writer.append("</script>");
			writer.flush();
			writer.close();
		}
		return true;
	}
}

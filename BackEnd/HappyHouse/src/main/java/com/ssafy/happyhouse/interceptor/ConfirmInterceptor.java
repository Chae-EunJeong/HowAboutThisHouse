package com.ssafy.happyhouse.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ssafy.happyhouse.repository.dto.UserDto;


@Component
@SuppressWarnings("deprecation")
public class ConfirmInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		UserDto user = (UserDto) session.getAttribute("user");
		if(user == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		return true;
	}
	
}

package com.radiantk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class AuthCheckInterceptor implements HandlerInterceptor {
	
	// 컨트롤러(핸들러) 객체를 실행하기 전에 필요한 기능을 구현할 때 사용
	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		if(session != null) {
			Object authInfo = session.getAttribute("authInfo");
			if(authInfo != null) {
				return true;
			}
		}
		// getContextPath() : 현재 컨텍스트 경로를 리턴 ex) /spring10-mvc03
		response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}
}

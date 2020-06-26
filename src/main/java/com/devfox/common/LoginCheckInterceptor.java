package com.devfox.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.devfox.domain.MemberVO;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter
{
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{
        HttpSession session = request.getSession();
        MemberVO memberVO = (MemberVO)session.getAttribute("login");

        if(memberVO == null)
        {
            response.sendRedirect("/board/list");
            return false;
        }
        
        return true;	
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception 
    {
        // TODO Auto-generated method stub
        super.postHandle(request, response, handler, modelAndView);
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception 
    {
        // TODO Auto-generated method stub
        super.afterCompletion(request, response, handler, ex);
    }
	 
}

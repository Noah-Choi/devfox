package com.devfox.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devfox.domain.MemberVO;
import com.devfox.service.MemberService;

@Controller
public class MemberController 
{
	 @Inject
	 MemberService service;
	 
    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String loginForm()
    {
        return "login/loginForm";
    }
     
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String login(HttpSession session, MemberVO member, RedirectAttributes rttr)
    {
        if (session.getAttribute("login") != null)
            session.removeAttribute("login"); // 既存値打ち除去
         
        MemberVO vo = service.login(member);
        if (vo != null)
        { 
            session.setAttribute("login", vo);
            System.out.println(vo.getId() + " 로그인 성공");
            
            return "redirect:/board/list";
        }
        
        System.out.println(member.getId() + " 로그인 실패");
        rttr.addFlashAttribute("msg", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
        return "redirect:/login";
    }
 
    @RequestMapping(value="/logout")
    public String logout(HttpSession session) 
    {
        MemberVO vo = (MemberVO)session.getAttribute("login");
    	if (vo != null)
    		System.out.println(vo.getId() + " 로그아웃");
    	
        session.invalidate(); // session初期化
        return "redirect:/board/list";
    }
}

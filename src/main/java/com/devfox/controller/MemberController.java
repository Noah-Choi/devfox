package com.devfox.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devfox.domain.MemberVO;
import com.devfox.service.MemberService;

@Controller
public class MemberController 
{
	@Autowired
	MemberService service;
	 
	@Autowired
	BCryptPasswordEncoder Encoder;
	 
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
         
        MemberVO vo = service.login(member.getId());
        if (vo != null)
        { 
        	if(Encoder.matches(member.getPw(), vo.getPw()))
        	{
                session.setAttribute("login", vo);
                System.out.println(vo.getId() + " 로그인 성공");
                
                return "redirect:/board/list";        		
        	}
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
    
    @RequestMapping(value="/login/signupForm",method=RequestMethod.GET)
    public void signupForm()
    {
    	
    }
    
    @ResponseBody
    @RequestMapping(value="/login/idChk", method = RequestMethod.GET, produces = "application/json")
    public int idChk(String id) throws Exception 
    {
    	return service.selectExistId(id);
    }
    
    @RequestMapping(value="/login/signup",method=RequestMethod.POST)
    public String signup(MemberVO member, RedirectAttributes rttr)
    {
    	if(member.getId() == null || member.getId().replaceAll(" ", "").equals("") || 
    	   member.getPw() == null || member.getPw().replaceAll(" ", "").equals("") ||
    	   member.getName() == null || member.getName().replaceAll(" ", "").equals(""))
    	{
    		rttr.addFlashAttribute("msg", "회원가입 실패");	
    	}
    	else
    	{
    		member.setPw(Encoder.encode(member.getPw()));
    		service.create(member);
    		rttr.addFlashAttribute("msg", "회원가입 성공");
    	}
    		
    	return "redirect:/board/list";
    }
}

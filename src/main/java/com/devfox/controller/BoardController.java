package com.devfox.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devfox.domain.BoardVO;
import com.devfox.domain.MemberVO;
import com.devfox.service.BoardService;
 
@Controller
@RequestMapping("/board/") //url要請が/borad/で始めるのはここで処理する. ex) board/abc , board/123 board/create
public class BoardController 
{
    @Inject
    private BoardService service;
    
    @RequestMapping(value="/create",method=RequestMethod.GET)
    public void createGET(BoardVO board, Model model) throws Exception
    {
        System.out.println(" /board/create. GET方式");
    }
    
    @RequestMapping(value = "/create",method=RequestMethod.POST )
    public String createPOST(BoardVO board, HttpServletRequest request, RedirectAttributes rttr) throws Exception
    {
        System.out.println("/board/create POST方式");
        
        HttpSession session = request.getSession();
        MemberVO memberVO = (MemberVO)session.getAttribute("login");
        board.setM_id(memberVO.getId());
        
        service.create(board);
        rttr.addFlashAttribute("msg", "글을 등록하였습니다.");
        
        return "redirect:/board/list";
    }
    
    @RequestMapping(value = "/list", method=RequestMethod.GET)
    public void list(Model model) throws Exception
    {
        System.out.println("전체목록 페이지");
        
        model.addAttribute("boardList", service.list());    		
    }
    
    @RequestMapping(value = "/detail", method=RequestMethod.GET)
    public void detail(@RequestParam("num") int num, Model model) throws Exception
    {
        System.out.println("글 번호 " + num + "번의 상세내용 페이지");
        
        model.addAttribute(service.read(num));
        //service.updateViewCnt(num);
    }
    
    @RequestMapping(value = "/updateForm", method=RequestMethod.GET)
    public void updateForm(@RequestParam("num") int num, HttpServletRequest request, Model model) throws Exception
    {
        System.out.println("글 번호 " + num + "수정폼");
        
        HttpSession session = request.getSession();
        MemberVO memberVO = (MemberVO)session.getAttribute("login");
        BoardVO boardVO = service.read(num);
        if(memberVO.getId().equals(boardVO.getM_id()))
        	model.addAttribute(boardVO);
        else
        {
        	System.out.println("비정상적인 접근!!");
        	model.addAttribute("msg", "잘못된 접근 방식입니다.");
        }
        	
    }
    
    @RequestMapping(value = "/update", method=RequestMethod.POST)
    public String update(BoardVO board, HttpServletRequest request, RedirectAttributes rttr) throws Exception
    {
        System.out.println("글 번호 " + board.getNum() + "수정");
        
        HttpSession session = request.getSession();
        MemberVO memberVO = (MemberVO)session.getAttribute("login");
        if(memberVO.getId().equals(board.getM_id()))
        {
        	service.update(board);
        	rttr.addFlashAttribute("msg", "수정하였습니다.");
        }
        else
        {
        	System.out.println("비정상적인 접근!!");
        	rttr.addFlashAttribute("msg", "잘못된 접근 방식입니다.");
        }
        
        return "redirect:/board/detail?num=" + board.getNum();
    }
    
    @RequestMapping(value = "/delete", method=RequestMethod.GET)
    public String delete(@RequestParam("num") int num, HttpServletRequest request, RedirectAttributes rttr) throws Exception
    {
        System.out.println("글 번호 " + num + "삭제");
        
        HttpSession session = request.getSession();
        MemberVO memberVO = (MemberVO)session.getAttribute("login");
        BoardVO boardVO = service.read(num);
        if(memberVO.getId().equals(boardVO.getM_id()))
        {
        	service.delete(num);
        	rttr.addFlashAttribute("msg", "삭제하였습니다.");
        	return "redirect:/board/list";
        }
        else
        {
        	System.out.println("비정상적인 접근!!");
        	rttr.addFlashAttribute("msg", "잘못된 접근 방식입니다.");
        	return "redirect:/board/detail?num=" + boardVO.getNum();
        }
        
    }
}
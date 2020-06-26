package com.devfox.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devfox.domain.BoardVO;
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
    public String createPOST(BoardVO board, RedirectAttributes rttr) throws Exception
    {
        System.out.println("/board/create POST方式");
        
        service.create(board);
        rttr.addFlashAttribute("type", "등록");
        rttr.addFlashAttribute("msg", 1);
        
        return "redirect:/board/list";
    }
    
    @RequestMapping(value = "/list", method=RequestMethod.GET)
    public void list(Model model) throws Exception
    {
        System.out.println("전체목록 페이지");
        
        model.addAttribute("boardList", service.list());    		
    }
}
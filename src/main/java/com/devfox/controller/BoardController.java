package com.devfox.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devfox.domain.BoardSearchVO;
import com.devfox.domain.BoardVO;
import com.devfox.domain.CommentPagingVO;
import com.devfox.domain.CommentVO;
import com.devfox.domain.MemberVO;
import com.devfox.domain.PagingVO;
import com.devfox.service.BoardService;
import com.devfox.service.CommentService;
import com.mysql.cj.util.StringUtils;
 
@Controller
@RequestMapping("/board/") //url要請が/borad/で始めるのはここで処理する. ex) board/abc , board/123 board/create
public class BoardController 
{
    private BoardService  service;
    private CommentService commentService;
    
    @Autowired
    @Qualifier("boardPaging")
    private PagingVO boardPaging;
    
    @Autowired
    @Qualifier("commentPaging")
    private CommentPagingVO commentPaging;
    
    @Autowired
    public BoardController(BoardService service, CommentService commentService)
    {
    	this.service = service;
    	this.commentService = commentService;
    }
    
    @RequestMapping(value="/create",method=RequestMethod.GET)
    public void createGET(BoardVO board, Model model) throws Exception
    {
        System.out.println(" /board/create. GET方式");
    }
    
    @RequestMapping(value = "/create",method=RequestMethod.POST )
    public String createPOST(BoardVO board, HttpServletRequest request, RedirectAttributes rttr) throws Exception
    {
        System.out.println("/board/create POST方式");
        
        
        //題目とか内容がなければ
        if(board.getTitle() == null || board.getTitle().replaceAll(" ", "").equals("") || 
           board.getContent() == null || board.getContent().replaceAll(" ", "").equals(""))
        {
        	rttr.addFlashAttribute("msg", "게시글을 입력해주세요.");
        	return "redirect:/board/create";
        }
        
        HttpSession session = request.getSession();
        MemberVO memberVO = (MemberVO)session.getAttribute("login");
        board.setM_id(memberVO.getId());
        
        service.create(board);
        rttr.addFlashAttribute("msg", "게시글을 등록하였습니다.");
        
        return "redirect:/board/list";
    }
    
    @RequestMapping(value = "/list", method=RequestMethod.GET)
    public void list(@RequestParam(value="page", defaultValue="1") int page, BoardSearchVO search, Model model) throws Exception
    {
    	System.out.println("게시판 리스트 page : " + page);
    	
    	boardPaging.setTotalCnt(service.selectCount(search));
    	boardPaging.setCurPage(page);
    	
    	int startPoint = (page - 1) * boardPaging.getPageSize();
    	boardPaging.setStartPoint(startPoint);
    	
    	int endPage = boardPaging.getTotalCnt() / boardPaging.getPageSize();
    	int nmg = boardPaging.getTotalCnt() % boardPaging.getPageSize();
    	if(0 < nmg) 
    		endPage++;
    	
    	boardPaging.setEndPage(endPage);
    	search.setPaging(boardPaging);
    	
    	model.addAttribute("boardList", service.list(search));
    	model.addAttribute("paging", search);
    }
    
    @RequestMapping(value = "/detail", method=RequestMethod.GET)
    public ModelAndView detail(@RequestParam("num") int num, @RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        System.out.println("글 번호 " + num + "번의 상세내용 페이지");
                
        // 保存されたCookie読み込み
        Map mapCookie = new HashMap();
        if(request.getCookies() != null)
        {
        	Cookie cookies[] = request.getCookies();
        	for(int i = 0; i < cookies.length; i++) 
        	{
        		Cookie obj = cookies[i]; 
        		mapCookie.put(obj.getName(), obj.getValue()); 
        	}
        }
        
        String cookieReadView = (String) mapCookie.get("readView"); 
        
        // 新しいCookie生成 
        String newCookieReadView = "|" + num + "|";
        
        //保存されたCookieの中で新しいCookie値と同じ値があるか検索
        if(StringUtils.indexOfIgnoreCase(cookieReadView, newCookieReadView) == -1) 
        {
        	//なければCookie生成
        	Cookie cookie = new Cookie("readView", cookieReadView + newCookieReadView);
        	response.addCookie(cookie);
        	
        	System.out.println("글 번호 " + num + "조회수 증가");
        	
        	//照会数増加
        	service.updateViewCnt(num);
        }

        commentPaging.setTotalCnt(commentService.selectCount(num));
        commentPaging.setCurPage(page);
    	
    	int startPoint = (page - 1) * commentPaging.getPageSize();
    	commentPaging.setStartPoint(startPoint);
    	
    	int endPage = commentPaging.getTotalCnt() / commentPaging.getPageSize();
    	int nmg = commentPaging.getTotalCnt() % commentPaging.getPageSize();
    	if(0 < nmg) 
    		endPage++;
    	
    	commentPaging.setEndPage(endPage);
    	commentPaging.setNum(num);
    	
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/board/detail"); 
        mv.addObject("boardVO", service.read(num));
        mv.addObject("commentList", commentService.list(commentPaging));
        mv.addObject("paging", commentPaging);

        return mv;
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
        
        //題目とか内容がなければ
        if(board.getTitle() == null || board.getTitle().replaceAll(" ", "").equals("") || 
           board.getContent() == null || board.getContent().replaceAll(" ", "").equals(""))
        {
        	rttr.addFlashAttribute("msg", "게시글을 입력해주세요.");
        	return "redirect:/board/updateForm?num=" + board.getNum();
        }
        
        HttpSession session = request.getSession();
        MemberVO memberVO = (MemberVO)session.getAttribute("login");
        BoardVO boardVO = service.read(board.getNum());
        if(memberVO.getId().equals(boardVO.getM_id()))
        {
        	service.update(board);
        	rttr.addFlashAttribute("msg", "게시물을 수정하였습니다.");
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
        	rttr.addFlashAttribute("msg", "게시물을 삭제하였습니다.");
        	return "redirect:/board/list";
        }
        else
        {
        	System.out.println("비정상적인 접근!!");
        	rttr.addFlashAttribute("msg", "잘못된 접근 방식입니다.");
        	return "redirect:/board/detail?num=" + boardVO.getNum();
        }
    }
    
    @RequestMapping(value = "/createComment",method=RequestMethod.POST )
    public String createComment(CommentVO comment, HttpServletRequest request, RedirectAttributes rttr) throws Exception
    {
        System.out.println("/board/createComment");
        
        //コメントがなければ
        if(comment.getContent() == null || comment.getContent().replaceAll(" ", "").equals(""))
        {
        	rttr.addFlashAttribute("msg", "댓글을 입력해 주세요.");
        	return "redirect:/board/detail?num=" + comment.getB_no();
        }
        
        HttpSession session = request.getSession();
        MemberVO memberVO = (MemberVO)session.getAttribute("login");
        comment.setM_id(memberVO.getId());
        
        commentService.create(comment);
        rttr.addFlashAttribute("msg", "댓글을 등록하였습니다.");
        
        return "redirect:/board/detail?num=" + comment.getB_no();
    }
    
    @RequestMapping(value = "/updateComment", method=RequestMethod.POST)
    public String updateComment(CommentVO comment, HttpServletRequest request, RedirectAttributes rttr) throws Exception
    {
        System.out.println("댓글 번호 " + comment.getNum() + "수정");

        //コメントがなければ
        CommentVO commentVO = commentService.read(comment.getNum());
        if(comment.getContent() == null || comment.getContent().replaceAll(" ", "").equals(""))
        {
        	rttr.addFlashAttribute("msg", "댓글을 입력해 주세요.");
        	return "redirect:/board/detail?num=" + commentVO.getB_no();
        }
        
        HttpSession session = request.getSession();
        MemberVO memberVO = (MemberVO)session.getAttribute("login");
        if(memberVO.getId().equals(commentVO.getM_id()))
        {
        	commentService.update(comment);
        	rttr.addFlashAttribute("msg", "댓글을 수정하였습니다.");
        }
        else
        {
        	System.out.println("비정상적인 접근!!");
        	rttr.addFlashAttribute("msg", "잘못된 접근 방식입니다.");
        }
        
        return "redirect:/board/detail?num=" + commentVO.getB_no();
    }
    
    @RequestMapping(value = "/deleteComment", method=RequestMethod.GET)
    public String deleteComment(@RequestParam("num") int num, HttpServletRequest request, RedirectAttributes rttr) throws Exception
    {
        System.out.println("댓글 번호 " + num + "삭제");
        
        HttpSession session = request.getSession();
        MemberVO memberVO = (MemberVO)session.getAttribute("login");
        CommentVO commentVO = commentService.read(num);
        if(memberVO.getId().equals(commentVO.getM_id()))
        {
        	commentService.delete(num);
        	rttr.addFlashAttribute("msg", "게시물을 삭제하였습니다.");
        }
        else
        {
        	System.out.println("비정상적인 접근!!");
        	rttr.addFlashAttribute("msg", "잘못된 접근 방식입니다.");
        }
        
        return "redirect:/board/detail?num=" + commentVO.getB_no();
    }
}
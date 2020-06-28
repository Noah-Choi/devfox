package com.devfox.service;

import java.util.List;

import com.devfox.domain.CommentPagingVO;
import com.devfox.domain.CommentVO;

public interface CommentService 
{
	public void create(CommentVO vo) throws Exception;

	public List<CommentVO> list(CommentPagingVO vo) throws Exception;
	    
	public CommentVO read(Integer num) throws Exception;
	
	public void delete(Integer num) throws Exception;
	    
	public void update(CommentVO vo) throws Exception;
	    
	public int selectCount(Integer b_no) throws Exception;
}

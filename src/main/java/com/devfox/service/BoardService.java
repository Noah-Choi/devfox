package com.devfox.service;

import java.util.List;

import com.devfox.domain.BoardSearchVO;
import com.devfox.domain.BoardVO;

public interface BoardService 
{
	
	public void create(BoardVO vo) throws Exception;

	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listSearch(BoardSearchVO vo) throws Exception;
	    
	public BoardVO read(Integer num) throws Exception;
	    
	public void delete(Integer num) throws Exception;
	    
	public void update(BoardVO vo) throws Exception;
	    
	public void updateViewCnt(Integer num) throws Exception;

}

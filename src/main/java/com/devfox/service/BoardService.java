package com.devfox.service;

import java.util.List;

import com.devfox.domain.BoardSearchVO;
import com.devfox.domain.BoardVO;
import com.devfox.domain.PagingVO;

public interface BoardService 
{
	
	public void create(BoardVO vo) throws Exception;

	public List<BoardVO> list(BoardSearchVO vo) throws Exception;
	    
	public BoardVO read(Integer num) throws Exception;
	    
	public void delete(Integer num) throws Exception;
	    
	public void update(BoardVO vo) throws Exception;
	    
	public void updateViewCnt(Integer num) throws Exception;

	public int selectCount(BoardSearchVO vo) throws Exception;
}

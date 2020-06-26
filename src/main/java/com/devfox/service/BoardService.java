package com.devfox.service;

import java.util.List;

import com.devfox.domain.BoardVO;

public interface BoardService {

	   public void create(BoardVO vo) throws Exception;
	    
	    public List<BoardVO> list() throws Exception;
	    
	    public BoardVO read(Integer b_no) throws Exception;
	    
	    public void delete(Integer b_no) throws Exception;
	    
	    public void update(BoardVO vo) throws Exception;
}

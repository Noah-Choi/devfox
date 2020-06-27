package com.devfox.dao;

import java.util.List;

import com.devfox.domain.BoardVO;

public interface BoardDAO {

	public void create(BoardVO vo) throws Exception;

	public List<BoardVO> list() throws Exception;
	
	public BoardVO read(Integer num) throws Exception;
	
	public void delete(Integer num) throws Exception;
	
	public void update(BoardVO vo) throws Exception;
	
	public void updateViewCnt(Integer num) throws Exception;
}

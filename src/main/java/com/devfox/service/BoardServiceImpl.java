package com.devfox.service;
 
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.devfox.dao.BoardDAO;
import com.devfox.domain.BoardSearchVO;
import com.devfox.domain.BoardVO;
import com.devfox.domain.PagingVO;
 
@Service
public class BoardServiceImpl implements BoardService 
{
    @Inject
    private BoardDAO dao;
    
    @Override
    public void create(BoardVO vo) throws Exception 
    {
        dao.create(vo);
    }
 
    @Override
    public List<BoardVO> list(BoardSearchVO vo) throws Exception
    {
    	return dao.list(vo);
    }
    
    @Override
    public BoardVO read(Integer num) throws Exception 
    {
        return dao.read(num);
    }
 
    @Override
    public void delete(Integer num) throws Exception 
    {
        dao.delete(num);
    }
 
    @Override
    public void update(BoardVO vo) throws Exception 
    {
        dao.update(vo);
    }

	@Override
	public void updateViewCnt(Integer num) throws Exception 
	{
		dao.updateViewCnt(num);
	}
	
	@Override
	public int selectCount(BoardSearchVO vo) throws Exception
	{
		return dao.selectCount(vo);
	}
 
}
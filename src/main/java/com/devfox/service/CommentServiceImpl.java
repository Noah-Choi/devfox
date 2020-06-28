package com.devfox.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.devfox.dao.CommentDAO;
import com.devfox.domain.CommentPagingVO;
import com.devfox.domain.CommentVO;

@Service
public class CommentServiceImpl implements CommentService
{

	@Inject
    private CommentDAO dao;
	
	@Override
	public void create(CommentVO vo) throws Exception 
	{
		dao.create(vo);
	}

	@Override
	public List<CommentVO> list(CommentPagingVO vo) throws Exception 
	{
		return dao.list(vo);
	}

	@Override
	public CommentVO read(Integer num) throws Exception 
	{
		return dao.read(num);
	}
	
	@Override
	public void delete(Integer num) throws Exception 
	{
		dao.delete(num);
	}

	@Override
	public void update(CommentVO vo) throws Exception 
	{
		dao.update(vo);
	}

	@Override
	public int selectCount(Integer b_no) throws Exception 
	{
		return dao.selectCount(b_no);
	}

}

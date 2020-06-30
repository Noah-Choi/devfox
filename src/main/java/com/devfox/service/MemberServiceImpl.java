package com.devfox.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.devfox.dao.MemberDAO;
import com.devfox.domain.MemberVO;

@Service
public class MemberServiceImpl implements MemberService
{

	@Inject
    private MemberDAO dao;
	
	@Override
	public MemberVO login(String id) 
	{
		return dao.login(id);
	}

	@Override
	public void create(MemberVO vo)
	{
		dao.create(vo);
	}

	@Override
	public int selectExistId(String id) 
	{
		return dao.selectExistId(id);
	}

}

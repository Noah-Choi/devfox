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
	public MemberVO login(MemberVO vo) 
	{
		return dao.login(vo);
	}

}

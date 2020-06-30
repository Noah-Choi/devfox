package com.devfox.service;

import com.devfox.domain.MemberVO;

public interface MemberService 
{
	public MemberVO login(String id);
	
	public void create(MemberVO vo);
	
	public int selectExistId(String id);
}

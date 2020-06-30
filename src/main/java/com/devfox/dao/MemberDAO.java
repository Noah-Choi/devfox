package com.devfox.dao;

import com.devfox.domain.MemberVO;

public interface MemberDAO 
{
	public MemberVO login(String id);
	
	public void create(MemberVO vo);
	
	public int selectExistId(String id);
}

package com.devfox.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.devfox.domain.MemberVO;

@Repository
public class MemberImpl implements MemberDAO
{
	@Inject
    private SqlSession sqlSession;
    private static String namespace = "com.devfox.mapper.MemberMapper";

	@Override
	public MemberVO login(MemberVO vo) 
	{
		return sqlSession.selectOne(namespace + ".login", vo);
	}

}

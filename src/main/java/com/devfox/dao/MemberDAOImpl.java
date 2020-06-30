package com.devfox.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.devfox.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO
{
	@Inject
    private SqlSession sqlSession;
    private static String namespace = "com.devfox.mapper.MemberMapper";

	@Override
	public MemberVO login(String id) 
	{
		return sqlSession.selectOne(namespace + ".login", id);
	}

	@Override
	public void create(MemberVO vo) 
	{
		sqlSession.insert(namespace + ".insertMember", vo);
	}

	@Override
	public int selectExistId(String id) 
	{
		return sqlSession.selectOne(namespace + ".selectExistId", id);
	}
}

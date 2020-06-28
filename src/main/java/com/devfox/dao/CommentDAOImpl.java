package com.devfox.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.devfox.domain.CommentPagingVO;
import com.devfox.domain.CommentVO;

@Repository
public class CommentDAOImpl implements CommentDAO 
{

	@Inject
    private SqlSession sqlSession;
    private static String namespace = "com.devfox.mapper.CommentMapper";
	
	@Override
	public void create(CommentVO vo) throws Exception 
	{
		sqlSession.insert(namespace + ".insertComment", vo);
	}

	@Override
	public List<CommentVO> list(CommentPagingVO vo) throws Exception 
	{
		return sqlSession.selectList(namespace + ".listComment", vo);
	}

	@Override
	public CommentVO read(Integer num) throws Exception 
	{
		return sqlSession.selectOne(namespace + ".detailComment", num);
	}
	
	@Override
	public void delete(Integer num) throws Exception 
	{
		sqlSession.delete(namespace + ".deleteComment", num);
	}

	@Override
	public void update(CommentVO vo) throws Exception 
	{
		sqlSession.update(namespace + ".updateComment", vo);
	}

	@Override
	public int selectCount(Integer b_no) throws Exception 
	{
		return sqlSession.selectOne(namespace + ".selectCommentCnt", b_no);
	}
}

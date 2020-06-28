package com.devfox.dao;
 
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.devfox.domain.BoardSearchVO;
import com.devfox.domain.BoardVO;
import com.devfox.domain.PagingVO;
 
@Repository
public class BoardDAOImpl implements BoardDAO 
{
    
    @Inject
    private SqlSession sqlSession;
    private static String namespace = "com.devfox.mapper.BoardMapper";
 
    @Override
    public void create(BoardVO vo) throws Exception 
    {
        sqlSession.insert(namespace + ".insertBoard", vo);
    }
    
    @Override
    public List<BoardVO> list(BoardSearchVO vo) throws Exception
    {
    	return sqlSession.selectList(namespace + ".listBoard", vo); 
    }
    
    @Override
    public BoardVO read(Integer num) throws Exception 
    {
    	return sqlSession.selectOne(namespace + ".detailBoard", num);
    }
 
    @Override
    public void delete(Integer num) throws Exception 
    {
        sqlSession.delete(namespace + ".deleteBoard", num);
    }
 
    @Override
    public void update(BoardVO vo) throws Exception 
    {
        sqlSession.update(namespace + ".updateBoard", vo);
    }

	@Override
	public void updateViewCnt(Integer num) throws Exception 
	{
		sqlSession.update(namespace + ".updateBoardViewCnt", num);
	}
	
	@Override
	public int selectCount(BoardSearchVO vo) throws Exception
	{
		return sqlSession.selectOne(namespace + ".selectViewCnt", vo);
	}
}
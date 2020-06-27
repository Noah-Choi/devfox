package com.devfox.dao;
 
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.devfox.domain.BoardSearchVO;
import com.devfox.domain.BoardVO;
 
@Repository
public class BoardDAOImp implements BoardDAO 
{
    
    @Inject
    private SqlSession sqlSession;
    private static String namespace = "com.devfox.mapper.BoardMapper";
 
    //掲示板追加 
    @Override
    public void create(BoardVO vo) throws Exception 
    {
        sqlSession.insert(namespace+".insertBoard", vo);
    }
    
    //掲示板リスト表示
    @Override
    public List<BoardVO> listAll() throws Exception 
    {
    	return sqlSession.selectList(namespace+".listAllBoard");
    }
    
    //掲示板検索リスト表示 
    @Override
    public List<BoardVO> listSearch(BoardSearchVO vo) throws Exception
    {
    	return sqlSession.selectList(namespace+".listSearchBoard", vo); 
    }
    
    //掲示板細かい表示 
    @Override
    public BoardVO read(Integer num) throws Exception 
    {
    	return sqlSession.selectOne(namespace+".detailBoard", num);
    }
 
    //掲示板削除 
    @Override
    public void delete(Integer num) throws Exception 
    {
        sqlSession.delete(namespace+".deleteBoard", num);
    }
 
    //掲示板修正
    @Override
    public void update(BoardVO vo) throws Exception 
    {
        sqlSession.update(namespace+".updateBoard", vo);
    }

	@Override
	public void updateViewCnt(Integer num) throws Exception 
	{
		sqlSession.update(namespace+".updateBoardViewCnt", num);
	}
}
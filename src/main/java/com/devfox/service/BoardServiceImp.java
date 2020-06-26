package com.devfox.service;
 
import java.util.List;
 
import javax.inject.Inject;
 
import com.devfox.dao.BoardDAO;
import com.devfox.domain.BoardVO;
import org.springframework.stereotype.Service;
 
@Service
public class BoardServiceImp implements BoardService 
{
    @Inject
    private BoardDAO dao;
    
    @Override
    public void create(BoardVO vo) throws Exception 
    {
        dao.create(vo);
    }
 
    @Override
    public List<BoardVO> list() throws Exception 
    {
        return dao.list();
    }
 
    @Override
    public BoardVO read(Integer num) throws Exception 
    {
        return dao.read(num);
    }
 
    @Override
    public void delete(Integer num) throws Exception 
    {
        dao.delete(num);
    }
 
    @Override
    public void update(BoardVO vo) throws Exception 
    {
        dao.update(vo);
    }
 
}
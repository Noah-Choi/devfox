package com.devfox.domain;

public class CommentPagingVO extends PagingVO 
{
	private int num;

	public CommentPagingVO() 
	{
		super();
	}
	public int getNum() 
	{
		return num;
	}
	public void setNum(int num) 
	{
		this.num = num;
	}
	
	public void setPaging(PagingVO paging) 
	{
		this.setTotalCnt(paging.getTotalCnt());
		this.setStartPage(paging.getStartPage());
		this.setEndPage(paging.getEndPage());
		this.setCurPage(paging.getCurPage());
		this.setPageSize(paging.getPageSize());
		this.setBlockSize(paging.getBlockSize());
		this.setStartPoint(paging.getStartPoint());
	}
}

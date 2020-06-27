package com.devfox.domain;

public class BoardSearchVO extends PagingVO
{
	private String type;
	private String keyword;
	
	public String getType() 
	{
		return type;
	}
	public void setType(String type) 
	{
		this.type = type;
	}
	public String getKeyword() 
	{
		return keyword;
	}
	public void setKeyword(String keyword) 
	{
		this.keyword = keyword;
	}
	
	public void setPaging(PagingVO paging) 
	{
		this.setTotalPostCnt(paging.getTotalPostCnt());
		this.setStartPage(paging.getStartPage());
		this.setEndPage(paging.getEndPage());
		this.setCurPage(paging.getCurPage());
		this.setPageSize(paging.getPageSize());
		this.setBlockSize(paging.getBlockSize());
		this.setStartPoint(paging.getStartPoint());
	}
}

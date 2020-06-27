package com.devfox.domain;

public class PagingVO 
{
	private int totalPostCnt;	//総掲示物数
	private int startPage;		//最初ページ
	private int endPage;		//最後ページ
	private int curPage;		//現在ページ
	private int pageSize;		//ページごとに表示する掲示物数
	private int blockSize;		//表示するページ数
	private int startPoint;		//掲示物の始め
	
	public int getTotalPostCnt() 
	{
		return totalPostCnt;
	}
	public void setTotalPostCnt(int totalPostCnt) 
	{
		this.totalPostCnt = totalPostCnt;
	}
	public int getStartPage() 
	{
		return startPage;
	}
	public void setStartPage(int startPage) 
	{
		this.startPage = startPage;
	}
	public int getEndPage() 
	{
		return endPage;
	}
	public void setEndPage(int endPage) 
	{
		this.endPage = endPage;
	}
	public int getCurPage() 
	{
		return curPage;
	}
	public void setCurPage(int curPage) 
	{
		this.curPage = curPage;
	}
	public int getPageSize() 
	{
		return pageSize;
	}
	public void setPageSize(int pageSize) 
	{
		this.pageSize = pageSize;
	}
	public int getBlockSize() 
	{
		return blockSize;
	}
	public void setBlockSize(int blockSize) 
	{
		this.blockSize = blockSize;
	}
	public int getStartPoint() 
	{
		return startPoint;
	}
	public void setStartPoint(int startPoint) 
	{
		this.startPoint = startPoint;
	}
	
}

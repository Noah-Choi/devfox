package com.devfox.domain;

import java.util.Date;

public class BoardVO {

	private int num;
	private String title;
	private String content;
	private String m_id;
	private Date date;
	private int count;
	
	public int getNum() 
	{
		return num;
	}
	public void setNum(int num) 
	{
		this.num = num;
	}
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	public String getContent() 
	{
		return content;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}
	public String getM_id() 
	{
		return m_id;
	}
	public void setM_id(String m_id) 
	{
		this.m_id = m_id;
	}
	public Date getDate() 
	{
		return date;
	}
	public void setDate(Date date) 
	{
		this.date = date;
	}
	public int getCount() 
	{
		return count;
	}
	public void setCount(int count) 
	{
		this.count = count;
	}
}

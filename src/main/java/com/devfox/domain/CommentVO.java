package com.devfox.domain;

import java.util.Date;

public class CommentVO 
{
	private int num;
	private int b_no;		//掲示物番号
	private String m_id;	//メンバーID
	private String content;	//内容
	private Date date;		//入力日
	
	public int getNum() 
	{
		return num;
	}
	public void setNum(int num) 
	{
		this.num = num;
	}
	public int getB_no() 
	{
		return b_no;
	}
	public void setB_no(int b_no) 
	{
		this.b_no = b_no;
	}
	public String getM_id() 
	{
		return m_id;
	}
	public void setM_id(String m_id) 
	{
		this.m_id = m_id;
	}
	public String getContent() 
	{
		return content;
	}
	public void setContent(String comment) 
	{
		this.content = comment;
	}
	public Date getDate() 
	{
		return date;
	}
	public void setDate(Date date) 
	{
		this.date = date;
	}
	
}

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>게시판</title>
	<script type="text/javascript">
		var msg ='${msg}';
	    if(msg != "")
	    {
			alert(msg);
	    }
	    
	</script>
 
</head>
<body>
 
	
    <table class="table table-board" border="1px" width="80%" align="center">
    <tr>
   		<td colspan="5" align="right">
   			<form action="/board/search" method="GET">
	    		<select name="search_type">
	    			<option value="title">제목</option>
	    			<option value="content">내용</option>
	    			<option value="id">작성자</option> 
	    		</select>
	    		
	    		<input type="text" name="search_keyword">
	    		<input type="submit" value="검색">
	    		
	    		<input type="button" value="글 작성" onclick="location.href='create';">
    		</form>
   		</td>
    </tr>
	<tr>
	    <th style="width:10%" >글 번호</th>
	    <th style="width:30%">제목</th>
	    <th style="width:20%">작성자</th>
	    <th style="width:20%">날짜</th>
	    <th style="width:20%">조회수</th>
	</tr>
 
 
    <c:forEach items="${boardList}" var="boardVO">
        <tr>
            <td>${boardVO.num}</td>
            <td><a href="/board/detail?num=${boardVO.num}">${boardVO.title}</a></td>
            <td>${boardVO.m_id}</td>
            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.date}" /> </td>
            <td><span> ${boardVO.count}</span></td>
        </tr>
    </c:forEach>
    </table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	
	<div align="center" style="margin-bottom:5px;">
   	<c:choose>
	    <c:when test="${empty login}">
	        <input type="button" value="로그인" onclick="location.href='../login';">
	        <input type="button" value="회원가입" onclick="location.href='../login/signupForm';">
	    </c:when>	 
	    <c:otherwise>
	        <input type="button" value="로그아웃" onclick="location.href='../logout';">
	    </c:otherwise>
	</c:choose>
   			
	</div>
	
    <table class="table table-board" border="1px" width="80%" align="center">
    <tr>
   		<td colspan="5" align="right">
   			<form action="/board/list" method="GET">
	    		<select name="type">
	    			<option value="title">제목</option>
	    			<option value="content">내용</option>
	    			<option value="m_id">작성자</option> 
	    		</select>
	    		
	    		<input type="text" name="keyword">
	    		<input type="submit" value="검색">
	    		
	    		<c:if test="${not empty login}">
	    			<input type="button" value="글 작성" onclick="location.href='create';">
	    		</c:if>	
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
    
    <c:set var="start" />
    <c:set var="count" value="${paging.blockSize}" />
    <c:choose>
	    <c:when test="${paging.curPage - paging.blockSize / 2 <= paging.startPage}">
	    	<c:set var="start" value="${paging.startPage}" />
	    	<c:if test="${paging.endPage < paging.blockSize}">
	    		<c:set var="count" value="${paging.endPage}" />
	    	</c:if>
	    </c:when>
	    <c:when test="${paging.endPage <= paging.curPage + (paging.blockSize - 1) / 2}">
			<c:set var="start" value="${paging.endPage - paging.blockSize + 1}" />
			<c:if test="${paging.endPage < paging.blockSize}">
				<c:set var="start" value="${paging.startPage}" />
				<c:set var="count" value="${paging.endPage}" />
			</c:if>    	
	    </c:when>
	    <c:otherwise>
	    	<c:set var="start" value="${paging.curPage - (paging.blockSize / 2 - paging.blockSize / 2 % 1)}" />
	    </c:otherwise>
	</c:choose>
	
	<div align="center" style="margin-top:10px">
	
		<!-- <表示可否 -->
		<c:if test="${paging.blockSize < paging.endPage && paging.startPage < paging.curPage - paging.blockSize / 2}">
			<a href="/board/list?page=${paging.startPage}&type=${paging.type}&keyword=${paging.keyword}" style="text-decoration:none; color:gray">&lt;</a>&nbsp;
		</c:if>
	
		<c:forEach var="i" begin="${start}" end="${start + count - 1}">
			<c:choose>
				<c:when test="${i == paging.curPage}">
					<a href="" style="text-decoration:none; color:black">${i}</a>&nbsp;
				</c:when>
				<c:otherwise>
					<a href="/board/list?page=${i}&type=${paging.type}&keyword=${paging.keyword}" style="text-decoration:none; color:gray">${i}</a>&nbsp;
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<!-- >表示可否 -->
		<c:if test="${paging.blockSize < paging.endPage && paging.curPage + (paging.blockSize - 1) / 2 < paging.endPage}">
			<a href="/board/list?page=${paging.endPage}&type=${paging.type}&keyword=${paging.keyword}" style="text-decoration:none; color:gray">&gt;</a>&nbsp;
		</c:if>
	</div>
	
</body>
</html>
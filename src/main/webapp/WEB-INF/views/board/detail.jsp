<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>게시판</title>

	<style>
		.commentWriteForm
		{
			overflow: hidden;
			height: 0px;
		}
		
		.commentView
		{
			
		}
		
	</style>

    <script type="text/javascript">
    
	    var msg ='${msg}';
	    if(msg != "")
	    {
			alert(msg);
	    }
	    
		function del(num) 
		{
			var chk = confirm("정말 삭제하시겠습니까?");
			if(chk) 
			{
				location.href='delete?num='+num;
			}
		}	
		
		function updateComment(num)
		{
			var write = document.getElementById("WriteForm" + num);
			write.style.height = "131px";
			write.style.overflow = "visible"
			
			var view = document.getElementById("view" + num);
			view.style.height = "0px";
			view.style.overflow = "hidden";
		}
		
		function cancel(num)
		{
			var write = document.getElementById("WriteForm" + num);
			write.style.height = "0px";
			write.style.overflow = "hidden"
			
			var view = document.getElementById("view" + num);
			view.style.height = "108px";
			view.style.overflow = "visible";
		}
		
		function delComment(num) 
		{
			var chk = confirm("정말 삭제하시겠습니까?");
			if(chk) 
			{
				location.href='deleteComment?num='+num;
			}
		}	
		
		function validateWrite() 
		{
			if(commentWrite.content.value.trim() == "")
			{
				alert("댓글을 입력해 주세요.");
				commentWrite.content.focus();
				return false;
			}
		}
	</script>

</head>
<body>
	<h1>게시판 상세</h1>
	
	<div style="width:800px">
		<div>
			<label >제목</label>
			<input type="text" name="title" style="width:80%; height:40px; font-size:16" value='${boardVO.title}' disabled>
		</div>
		<div style="margin-top:7px">
			<label >내용</label>
			<textarea rows="25" cols="100" name="content" style="font-size:14" disabled>${boardVO.content}</textarea>
		</div>
	      
		<div style="text-align: center; margin:10px;">
			<c:if test="${boardVO.m_id eq login.id}">
				<input type="button" value="수정" onclick="location.href='updateForm?num=${boardVO.num}'">
				<input type="button" value="삭제" onclick="del(${boardVO.num})">
			</c:if>
			<input type="button" value="글 목록" onclick="location.href='list';">
		</div>
		
		<!-- コメント総数表示 -->
		<div style="width:92%; height:40px; line-height:40px; padding:5px; margin-left:37px; margin-bottom:5px; border:1px solid gray;">
			댓글 <b>${paging.totalCnt}</b>개
		</div>
		
		<!-- コメント表示 -->
		<div style="width:89%; margin-left:37px;">
			<c:forEach items="${commentList}" var="commentVO">
				<c:if test="${commentVO.m_id eq login.id}">
					<div id="WriteForm${commentVO.num}" class="commentWriteForm">
						<form action="/board/updateComment" method="POST">
							<input type="hidden" name="num" value="${commentVO.num}">
							<div style="text-align:left; font-size:18; font-weight:bold; margin:7px; margin-left:0px">${commentVO.m_id}</div>
							<div style="height:80px">
								<textarea rows="5" cols="80" name="content" id="content">${commentVO.content}</textarea>
								<input type="button" value="취소" style="width:35pt; height:30pt; position:relative; top:-33px" onclick="cancel(${commentVO.num})">
								<button type="submit" style="width:35pt; height:30pt; position:relative; top:-33px">수정</button>
							</div>
						</form>
						<hr>
					</div>
				</c:if>
				
				<div id="view${commentVO.num}" class="commentView">
					<div style="font-size:18; font-weight:bold; margin-bottom:5px;">${commentVO.m_id}</div>
			        <div style="margin-bottom:5px;">${commentVO.content}</div>
			        <div style="font-size:14; color:gray;"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${commentVO.date}" /></div>
			        
			        <c:if test="${commentVO.m_id eq login.id}">
			        	<div style="text-align:right;">
				        	<input type="button" value="수정" onclick="updateComment(${commentVO.num})">
							<input type="button" value="삭제" onclick="delComment(${commentVO.num})">
			        	</div>
			        </c:if>
			        
		        	<hr>
				</div>
	    	</c:forEach>
		</div>
		
		<!-- paging表示-->
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
		
		<div align="center" style="margin:10px">
			<!-- <表示可否 -->
			<c:if test="${paging.blockSize < paging.endPage && paging.startPage < paging.curPage - paging.blockSize / 2}">
				<a href="/board/detail?num=${boardVO.num}&page=${paging.startPage}" style="text-decoration:none; color:gray">&lt;</a>&nbsp;
			</c:if>
		
			<c:forEach var="i" begin="${start}" end="${start + count - 1}">
				<c:choose>
					<c:when test="${i == paging.curPage}">
						<a href="" style="text-decoration:none; color:black">${i}</a>&nbsp;
					</c:when>
					<c:otherwise>
						<a href="/board/detail?num=${boardVO.num}&page=${i}" style="text-decoration:none; color:gray">${i}</a>&nbsp;
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- >表示可否 -->
			<c:if test="${paging.blockSize < paging.endPage && paging.curPage + (paging.blockSize - 1) / 2 < paging.endPage}">
				<a href="/board/detail?num=${boardVO.num}&page=${paging.endPage}" style="text-decoration:none; color:gray">&gt;</a>&nbsp;
			</c:if>
		</div>
		
		<!-- コメント入力空間-->
		<c:if test="${!empty login}">
			<div style="text-align:center; width:89%; margin-left:37px; border:1px solid gray">
				<form action="/board/createComment" method="POST" id="commentWrite" onsubmit="return validateWrite();">
					<input type="hidden" name="b_no" value="${boardVO.num}">
					<div style="text-align:left; margin:7px; margin-left:25px">댓글 쓰기</div>
					<div style="height:80px">
						<textarea rows="5" cols="80" name="content" id="content"></textarea>
						<button type="submit" style="width:50pt; height:50pt; position:relative; left:5px; top:-33px">등록</button>
					</div>
				</form>
			</div>
		</c:if>
		
	</div>
</body>
</html>
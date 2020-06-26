<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>게시판</title>

    <script type="text/javascript">
    
	    var msg ='${msg}';
	    if(msg != "")
	    {
			alert(msg);
	    }
	    
		function del(num) 
		{
			var chk = confirm("정말 삭제하시겠습니까?");
			if (chk) 
			{
				location.href='delete?num='+num;
			}
		}	
		
	</script>

</head>
<body>
	<h1>게시판 상세</h1>
	
	<div>
		<label >제목</label>
		<input type="text" name="title" style="width:350px" value='${boardVO.title}' disabled>
	</div>
	<div style="margin-top:7px">
		<label >내용</label>
		<textarea rows="12" cols="60" name="content" disabled>${boardVO.content}</textarea>
	</div>
      
	<div style="width:450px; text-align: center">
		<c:if test="${boardVO.m_id eq login.id}">
			<input type="button" value="수정" onclick="location.href='updateForm?num=${boardVO.num}'">
			<input type="button" value="삭제" onclick="del(${boardVO.num})">
		</c:if>
		<input type="button" value="글 목록" onclick="location.href='list';">
	</div>
        
</body>
</html>
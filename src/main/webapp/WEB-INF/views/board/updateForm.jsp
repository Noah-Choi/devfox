<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
	<title>게시판</title>
	<script type="text/javascript">
	  
		var msg ='${msg}';
		if(msg != "")
		{
			alert(msg);
			history.back();
		}
	
	</script>

</head>
<body>
	<h1>게시판 수정</h1>

	<form action="/board/update" method="POST">
		<input type="hidden" name="num" value='${boardVO.num}'>
		<div>
            <label>제목</label>
            <input type="text" name="title" style="width:350px" placeholder = "게시판 제목" value='${boardVO.title}'>
        </div>
        <div style="margin-top:7px">
            <label >내용</label>
            <textarea rows="12" cols="60" name="content" placeholder="게시판 내용">${boardVO.content}</textarea>
        </div>
        
        <div style="width:450px; text-align: center">
			<button type="submit">수정하기</button>
			<input type="button" value="글 목록" onclick="location.href='list';">
        </div>
	</form>

</body>
</html>
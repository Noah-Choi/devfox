<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>게시판</title>
    <script type="text/javascript">
		function validate() 
		{
			var title = document.getElementById("title");
			var content = document.getElementById("content");
	
			if(title.value.trim() == "")
			{
				alert("제목을 입력해 주세요.");
				title.focus();
				return false;
			}
			else if(content.value.trim() == "")
			{
				alert("내용을 입력해 주새요");
				content.focus();
				return false;
			}
				
		}
	</script>
</head>
<body>
	<h1>게시판 등록하기 </h1>
    
    <fieldset style="border:0">
	    <form action="/board/create" method="POST" onsubmit="return validate();">
    		<div>
	            <label for="title">제목</label>
	            <input type="text" name="title" id="title" style="width:350px" placeholder = "게시판 제목">
	        </div>
	        <div style="margin-top:7px">
	            <label for="content">내용</label>
	            <textarea rows="12" cols="60" name="content" id="content" placeholder="게시판 내용"></textarea>
	        </div>
	        
	        <div style="width:450px; text-align: center">
				<button type="submit">작성하기</button>
				<input type="button" value="글 목록" onclick="location.href='list';">
	        </div>
	    </form>
    </fieldset>
    
</body>
</html>
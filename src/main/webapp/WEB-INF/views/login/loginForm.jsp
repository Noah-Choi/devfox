<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>로그인</title>
	<script type="text/javascript">
	    var msg ='${msg}';
	    if(msg != "")
	    {
			alert(msg);
	    }
	    
	</script>
</head>
<body>
	<form action="/login" method="POST">
		<label class="legend">아이디</label>
		<input name="id" type="text">
		
		<label class="legend">패스워드</label> 
		<input name="pw" type="password">
		 
		<button type="submit">로그인</button>
	</form>
</body>
</html>
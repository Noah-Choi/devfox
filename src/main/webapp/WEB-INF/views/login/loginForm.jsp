<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>�α���</title>
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
		<label class="legend">���̵�</label>
		<input name="id" type="text">
		
		<label class="legend">�н�����</label> 
		<input name="pw" type="password">
		 
		<button type="submit">�α���</button>
	</form>
</body>
</html>
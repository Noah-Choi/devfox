<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>�α���</title>
	
	<style>
		input[type="text"], input[type="password"]
		{
			width:98%;
			height:40px;
			font-size: 18px;
			margin-top: 4px;
			margin-bottom: 10px;
			
		}
		
		button[type="submit"]
		{
			width:100%; 
			height:38pt;
			 
			background-color:skyblue; 
			border:0;
			 
			font-size:24px; 
			font-weight:bold; 
			color:gray;
		}
		
	</style>
	
	<script type="text/javascript">
	    var msg ='${msg}';
	    if(msg != "")
	    {
			alert(msg);
	    }
	    
	    function validate() 
		{
			if(memberId.value.trim() == "")
			{
				alert("���̵� �Է��� �ּ���.");
				memberId.focus();
				return false;
			}
			else if(password.value.trim() == "")
			{
				alert("��й�ȣ�� �Է��� �ּ���.");
				password.focus();
				return false;
			}
		}
	    
	</script>
</head>
<body>
	<div style="width: 400px;">
		<form action="/login" method="POST" onsubmit="return validate();">
			<input name="id" id="memberId" type="text" placeholder = "���̵�">
			<br>
			
			<input name="pw" id="password" type="password" placeholder = "��й�ȣ">
			<button type="submit">�α���</button>
		</form>
	
		<br>	
		<input type="button" value="�� ���" onclick="location.href='/board/list';">
	</div>
	
</body>
</html>
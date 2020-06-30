<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
	<title>회원가입</title>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<style>
	
		label
		{
			font-weight:bold;
		}
	
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
		
		button[type="button"]
		{
			width:20%; 
			height:35pt;
		}
		
		
	</style>
	<script type="text/javascript">
		function validate() 
		{
			if(memberId.value.trim() == "")
			{
				alert("아이디를 입력해 주세요.");
				memberId.focus();
				return false;
			}
			else if(password.value.trim() == "")
			{
				alert("비밀번호를 입력해 주세요.");
				password.focus();
				return false;
			}
			else if(password2.value.trim() == "")
			{
				alert("비밀번호 확인을 입력해 주세요.");
				password2.focus();
				return false;
			}
			else if(memberName.value.trim() == "")
			{
				alert("이름을 입력해 주세요.");
				memberName.focus();
				return false;
			}
			else if(password.value != password2.value)
			{
				alert("비밀번호가 일치하지 않습니다.");
				password.focus();
				return false;
			}
			else if(idChk.value == "N")
			{
				alert("중복확인 버튼을 눌러주세요.");
				memberId.focus();
				return false;
			}
		}
		
		function fn_idChk()
		{ 			
			$.ajax({
				url : '/login/idChk?id=' + memberId.value,
				type : 'get',
				success : function(data)
				{
					if(data == 0)
					{
						alert("사용가능한 아이디입니다.");
						idChk.value = "Y";
					}
					else
					{
						alert("중복된 아이디입니다.");
						memberId.focus();
						idChk.value = "N";	
					}
				}
			})
		}
	</script>
	
</head>
<body>
    <div style="width:400px; border:1px solid gray; padding-top: 5px;">
	    <fieldset style="border:0">
		    <form action="/login/signup" method="POST" onsubmit="return validate();">
	    		<div>
		            <label for="memberId">아이디</label>
		            <br>
		            <input type="text" name="id" id="memberId" style="width:75%;">
		            <button type="button" id="idChk" onclick="fn_idChk();" value="N">중복확인</button>
					<br>
		            
		            <label for="password">비밀번호</label>
		            <br>
		            <input type="password" name="pw" id="password">
		            <br>
		            
		            <label for="password">비밀번호 확인</label>
		            <br>
		            <input type="password" id="password2">
		            <br>
		            
		            <label for="memberName">이름</label>
		            <br>
		            <input type="text" name="name" id="memberName">
		            <br>
		      	
		      		<button type="submit">가입하기</button>
		      		
		        </div>
		    </form>
	    </fieldset>
    </div>
</body>
</html>
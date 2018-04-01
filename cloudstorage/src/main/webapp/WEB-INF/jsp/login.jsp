<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<title>Login Page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@include file="/WEB-INF/jsp/include/header.jsp"%>

</head>

<body>

	<form id="loginForm" action="user/login" method="post">
		Email: <input type="text" id="email" name="email" value=""> <br />
		Password: <input type="password" id="password" name="password" value=""> <br /> 
		<input type="submit" id="login" value="login" onclick="checkLogin()"> <br />
	</form>
	<a href="registerPage">register</a>

</body>

<script type="text/javascript">
	//ajax check login to refresh login form
	function checkLogin() {	
		$.ajax({
			data : $('#loginForm').serialize(),
			type : "POST",
			url : "user/checkLogin",
			dataType : "text",
			async : false,
			success : function(res) {
				console.log(res);
				if (res == "false") {
					alert("User does not exist! Please try again.");
				}
			}
		});
	}
</script>
</html>
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

	<form id="registerForm" action="user/register" method="post">
		Email: <input type="text" id="email" name="email" value=""> <br />
		Username: <input type="text" id="username" name="username" value=""> <br />
		Password: <input type="password" id="password" name="password" value=""> <br />
		Confirm Password: <input type="password" id="confirmPassword" name="confirmPassword" value=""> <br />
		<input type="button" id="register" onclick="checkRegister()" value="register">
	</form>
	<a href="loginPage">login</a>

</body>

<script type="text/javascript">
	function checkRegister() {
		
		var emailRegExp = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
		var usernameAndPasswordRegExp = /^[\w]{6,12}$/;
		//6-12digit, containing only letter number and _
		
		if (!emailRegExp.exec($("#email").val())) {
			alert("Please input a valid email address! Please try again.");
			return false;
		}
		if (!usernameAndPasswordRegExp.exec($("#username").val())) {
			alert("Username should be 6-12 digit containing only letter, number and underline! Please try again.");
			return false;
		}
		if (!usernameAndPasswordRegExp.exec($("#password").val())) {
			alert("Password should be 6-12 digit containing only letter, number and underline! Please try again.");
			return false;
		}	
		if ($("#password").val() != $("#confirmPassword").val()) {
			alert("Confirmed password does not match with password! Please try again.");
			return false;
		}
		
		$.ajax({
			data : $('#registerForm').serialize(),
			type : "POST",
			url : "user/checkRegister",
			dataType : "text",
			async : false,
			success : function(res) {
				console.log(res);
				if (res == "false") {
					alert("Email has already been used! Please try again.");
					return false;
				}
			}
		});
		
		$('#registerForm').submit();
		return true;
	}
</script>
</html>
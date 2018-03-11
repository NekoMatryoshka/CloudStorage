<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="/WEB-INF/jsp/include/header.jsp"%>

<form action="login" method="post">
	Email: <input type="text" name="email" value=""> <br />
	Password: <input type="text" name="password" value=""> <br />
	<input type="submit" value="login">
</form>
<a href="registerPage">register</a>



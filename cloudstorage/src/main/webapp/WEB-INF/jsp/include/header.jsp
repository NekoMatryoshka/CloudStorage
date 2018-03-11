<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<script src="js/jquery/2.0.0/jquery.min.js"></script>
<link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
<link href="css/site/style.css" rel="stylesheet">
<link href="css/fore/style.css" rel="stylesheet">

<script>
	function checkEmpty(id, name) {
		var value = $("#" + id).val();
		if (value.length == 0) {

			$("#" + id)[0].focus();
			return false;
		}
		return true;
	}
</script>
</head>
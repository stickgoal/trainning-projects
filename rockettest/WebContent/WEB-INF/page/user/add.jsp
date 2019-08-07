<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=request.getContextPath()%>/"/>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="user/add" method="post">
	<div><input type="text" name="username" placeholder="请输入用户名" /></div>
	<div><input type="password" name="password" placeholder="请输入密码" /></div>
	<div><input type="submit"/></div>
</form>


</body>
</html>
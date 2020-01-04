<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <% String basePath=request.getScheme()+"://"+request.getServerName()+":"+
    request.getServerPort()+request.getContextPath()+"/";
    %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>欢迎使用转账系统</h3>
	<form action="taccount.do" method="post">
		请输入转出账号：<input type="text" name="zcAccount"><br/><br/>
		请输入转入账号：<input type="text" name="zrAccount"><br/><br/>
		请输入转出金额：<input type="text" name="zzBalance"><br/><br/>
			<input type="submit" value="转账" >
	</form>

</body>
</html>
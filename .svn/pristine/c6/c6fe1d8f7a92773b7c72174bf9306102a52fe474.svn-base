<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>403 - 用户权限不足</title>
<script type="text/javascript" src="${ctp }/static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#toIndex").click(function(){
		window.top.location.href = this.href;
		return false;
	});
})
</script>
</head>

<body>
	<h2>403 - 用户权限不足.</h2>
	<p><a id="toIndex" href="<c:url value="/"/>">返回首页</a></p>
</body>
</html>

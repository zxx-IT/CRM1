<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>    
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>   
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
	<link rel="stylesheet" type="text/css" href="styles.css" >
	<script type="text/javascript">
		$(function(){
			//登出的 JS 代码
			$("a").click(function(){
				window.top.location.href = this.href;
				return false;
			});
		})
	</script>
  </head>
  <body style="border-bottom:solid 1px #666;">
	<TABLE style="width:100%;">
	<TR >
		<td ><img src="${ctp}/static/images/logo.jpg"></td>
		<td style="font-family:黑体;font-size:33px;font-weight:bold;"> 客户关系管理系统</td>	
		<td width="25%" align="right" style="font-size:12px;" valign="bottom">
			<b>当前登录用户:</b> <shiro:principal property="name"/>(${sessionScope.user.role.name }) [<a href="${ctp}/logout" id="logout">注销</a>]&nbsp;&nbsp;<br />
		 </td>
	</tr>
	</table>
  </body>
</html>

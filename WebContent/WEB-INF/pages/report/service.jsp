<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="atguigu" tagdir="/WEB-INF/tags" %>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户服务分析</title>
  <body>
	<div class="page_title">客户服务分析</div>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[0].submit();">查询</button>  
	</div>
  	<form action="${ctp }/report/service">
		<div id="listView" style="display:block;">
			<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
				<tr>
					<th>
						日期
					</th>
					<td>
						<input type="text" name="search_GTD_minOrderDate" size="10" />
						-
						<input type="text" name="search_LTD_maxOrderDate" size="10" />
					</td>
					<th>&nbsp;</th>
					<td>&nbsp;</td>
				</tr>
			</table>
			<!-- 列表数据 -->
			<br />
			
			<c:if test="${page != null && page.totalElements > 0 }">
				<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
				<tr>
					<th>序号</th>
					<th>条目</th>
					<th>客户数量</th>
				</tr>
					<c:forEach var="customer" items="${page.content }" varStatus="status">
						<tr>
							<td class="list_data_number">${status.index + 1}</td>
							<td class="list_data_ltext">${customer.licenceno}</td>
							<td class="list_data_number">${customer.count}</td>
						</tr>			
					</c:forEach>
				</table>
				<br /><br />
			<h3 align="center"><a href="${ctp }/chart/service">生成统计图</a></h3>
			<atguigu:page page="${page }" queryString="${queryString }"></atguigu:page>
			</c:if>
			<c:if test="${page == null || page.totalElements == 0 }">
				没有任何数据
			</c:if>
		</div>
	</form>	
  </body>
</html>
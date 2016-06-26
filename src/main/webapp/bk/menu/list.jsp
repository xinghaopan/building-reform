<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/bk/top.jsp" %>
<div class="mainFrame-center-navigation">
	<p>${navigation}</p>
	<span></span>
</div>
<div class="mainFrame-center-list">
	<div class="mainFrame-center-search">
		<em>
			<a href="/bk/menu/edit/${mid}?fatherId=${fatherId}&id=0">新建</a>
		</em>
	</div>
	<table border=0 cellSpacing=1 summary="" cellPadding=3 width=732 bgColor="#cccccc" align="center" style="font-size:12px;">
		<tr>
			<td height=30 align="center" bgcolor="#45c8dc" ><p class="grzx_27">前台名称</p></td>
			<td width="20%" align="center" bgcolor="#45c8dc"><p class="grzx_27">前台地址</p></td>
			<td width="20%" align="center" bgcolor="#45c8dc"><p class="grzx_27">后台名称</p></td>
			<td width="20%" align="center" bgcolor="#45c8dc"><p class="grzx_27">后台地址</p></td>
			<td width="10%" align="center" bgcolor="#45c8dc"><p class="grzx_27">排序</p></td>
			<td width="10%" align="center" bgcolor="#45c8dc"><p class="grzx_27">操作</p></td>
		</tr>
		<c:forEach items="${list}" var="smenu">
			<tr>
				<td height="30" align="center" bgColor="#ffffff">
					<p><a class="mainFrame-first-a" href="/bk/menu/list/${mid}?fatherId=${smenu.id}">${smenu.frontName}</a></p>
				</td>
				<td bgColor="#ffffff" align="center"><p>${smenu.frontLink}</p></td>
				<td bgColor="#ffffff" align="center"><p>${smenu.backName}</p></td>
				<td bgColor="#ffffff" align="center"><p>${smenu.backLink}</p></td>
				<td bgColor="#ffffff" align="center"><p>${smenu.order}</p></td>
				<td bgColor="#ffffff" align="center">
					<p>
						<a class="centerFrame-list-edit" href="/bk/menu/edit/${mid}?fatherId=${smenu.fatherId}&id=${smenu.id}">编辑</a> 
						<a class="centerFrame-list-del" href="javascript:void(0)" url="/bk/menu/del/${mid}?id=${smenu.id}" bname="${smenu.backName}">删除</a> 
					</p>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>

<script type="text/javascript">
jQuery(document).ready(function($) {
	$('.centerFrame-list-del').click(function(){
		if( confirm('您确定要删除【' + $(this).attr("bname") + '】吗？') ){
			var url = $(this).attr("url");
			$.ajax({
				type : "get",
				url : url,
				data : "radom=" + Math.random(),
				dataType : "text",
				success : function(msg) {
					if (msg == "-999") {
		        		outLogin();
		        	}
	            	else if (msg == 1) {
	            		location.reload();
					}
					else {
						alert('【' + $(this).attr("bname") + '】删除失败！！！');
					}
				},
				error : function(XMLHttpRequest, error, errorThrown) {
					alert("请求超时！！！");
				}
			});
		}
	});
	$('#btn_New').click(function(){
		changeCenterOnce($(this).attr("url"));
	});
});
</script>
<%@ include file="/bk/bottom.jsp" %>
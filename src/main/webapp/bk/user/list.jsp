<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/bk/top.jsp" %>
<div class="mainFrame-center-navigation">
	<p></p>
	<span></span>
</div>
<div class="mainFrame-center-list">
	<div class="mainFrame-center-search">
		<p></p><span></span>
		<em>
			<a href="/bk/user/edit/${mid}/0">新建</a>
		</em>
	</div>
	
	<table border=0 cellSpacing=1 summary="" cellPadding=3 width=732 bgColor="#cccccc" align="center" style="font-size:12px;">
		<tr>
			<td height=30 align="center" bgcolor="#45c8dc" ><p class="grzx_27">帐号</p></td>
			<td width="20%" align="center" bgcolor="#45c8dc"><p class="grzx_27">真实姓名</p></td>
			<td width="15%" align="center" bgcolor="#45c8dc"><p class="grzx_27">角色名称</p></td>
			<td width="20%" align="center" bgcolor="#45c8dc"><p class="grzx_27">操作</p></td>
		</tr>
		<c:forEach items="${list}" var="suser">
			<tr>
				<td height="30" align="center" bgColor="#ffffff">
					<p>${suser.name}</p>
				</td>
				<td bgColor="#ffffff" align="center">
					<p>${suser.trueName}</p>
				</td>
				<td bgColor="#ffffff" align="center">
					<p>
						${suser.role.name}
					</p>
				</td>
				<td bgColor="#ffffff" align="center">
					<p>
						<a class="centerFrame-list-initPs" href="javascript:void(0)" url="/bk/user/initPassword/${mid}" para="id=${suser.id}" bname="${suser.name}">重置密码</a>
						<a class="centerFrame-list-edit" href="/bk/user/edit/${mid}/${suser.id}">编辑</a> 
						<a class="centerFrame-list-del" href="javascript:void(0)" url="/bk/user/del/${mid}" para="id=${suser.id}" bname="${suser.name}">删除</a> 
					</p>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>

<script type="text/javascript">
jQuery(document).ready(function($) {
	$('.centerFrame-list-initPs').click(function(){
		if( confirm('您确定要重置【' + $(this).attr("bname") + '】密码吗？') ){
			var url = $(this).attr("url");
			var para = $(this).attr("para");
			$.ajax({
				type : "get",
				url : url,
				data : "radom=" + Math.random() + "&" + para,
				dataType : "text",
				success : function(msg) {
					if (msg == "-999") {
		        		outLogin();
		        	}
	            	else if (msg == 1) {
	            		alert('【' + $(this).attr("bname") + '】密码初始化成功！！！\n密码为：${password}');
					}
	            	else if (msg == -1) {
	            		alert('【' + $(this).attr("bname") + '】密码初始化失败！！！\n用户信息错误！！！');
					}
					else {
						alert('【' + $(this).attr("bname") + '】初始化密码失败！！！');
					}
				},
				error : function(XMLHttpRequest, error, errorThrown) {
					alert("请求超时！！！");
				}
			});
		}
	});
	
	$('.centerFrame-list-del').click(function(){
		if( confirm('您确定要删除【' + $(this).attr("bname") + '】吗？') ){
			var url = $(this).attr("url");
			var para = $(this).attr("para");
			$.ajax({
				type : "get",
				url : url,
				data : "radom=" + Math.random() + "&" + para,
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
	
});
</script>
<%@ include file="/bk/bottom.jsp" %>
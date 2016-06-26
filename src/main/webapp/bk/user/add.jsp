<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/bk/top.jsp" %>
<form id="userForm" method="post" name="userForm" action="/bk/user/save/${mid}">
<div class="mainFrame-center-navigation">
	<p>${navigation}</p>
</div>
<div class="mainFrame-center-form">
	<ul>
		<li style="display:none"><p>用户ID：</p><span><input id="id" name="id" class="web_01" type="text" value="${user.id}"  /></span></li>
		<li><p>帐号：</p><span><input id="name" name="name" class="web_01" type="text" value="${user.name}"  /></span></li>
		<li><p>真实姓名：</p><span><input id="trueName" name="trueName" class="web_01" type="text" value="${user.trueName}"  /></span></li>
		<li style="display:none"><p>密码：</p><span><input id="password" name="password" class="web_01" type="text" value="${user.password}"  /></span></li>
		<li>
			<p>角色：</p>
			<span>
				<select id="role.id" name="role.id" class="web_01 role">
					<option value="0" selected>请选择</option>
					<c:forEach items="${roleList}" var="srole">
						<option value="${srole.id}" <c:if test="${srole.id == user.role.id}">selected</c:if> >${srole.name}</option>
					</c:forEach>
				</select>	
			</span>
		</li>
		<li><p>排序：</p><span><input id="order" name="order" class="web_01" type="text" value="${user.order}"  /></span></li>
		<li>
			<p>是否统计：</p>
			<span>
				<input type="radio" id="state" name="state" value="0" checked="checked" />否
				<input type="radio" id="state" name="state" value="1" <c:if test="${user.state==1}"> checked="checked" </c:if> />是
			</span>
		</li>
	</ul>
</div>
</form>

<div class="mainFrame-center-form-editor">
    <div class="mainFrame-center-form-editor-title"></div> 
    <div class="mainFrame-center-form-editor-content">
        <img src="/images/back_13.jpg" class="web_02" id="btn_Submit" name="btn_Submit" />
        <img src="/images/back_44.jpg" class="web_02" id="btn_Back" name="btn_Back" />
    </div>
</div>

<script type="text/javascript">
$(document).ready(function () {
	// ajaxSubmit
	var id = '${user.id}';
	if (id == null || id == '') {
		id = 0;
	}
	$("#btn_Submit").click(function () {
		if (isNull($('#name').val())) {
			alert("帐号不能为空！！！");
			$('#name').focus();
			return;
		}
		
		if (isNull($('#trueName').val())) {
			alert("真实姓名不能为空！！！");
			$('#trueName').focus();
			return;
		}
		
		if ($('.role').val() == 0) {
			alert("请为用户选择角色！！！");
			return;
		}
		
		if (!isUnsignedInteger($('#order').val())) {
			alert("排序只能为数字！！！");
			$('#order').focus();
			return;
		}
		
		var options = { 
	            success : function(msg) {
	            	if (msg == "-999") {
		        		alert("999");
		        		//outLogin();
		        	}
	            	else if (msg == -1) {
	            		alert("帐号：[" + $('#name').val() + "]已存在，无法保存！！！");
		        	}
	            	else if (msg == 1) {
	            		if (id == 0) {
	            			alert("用户信息保存成功！！！\n默认密码为：${password}");
	            		}
	            		else {
	            			alert("用户信息保存成功！！！");
	            		}
	            		window.open("/bk/user/list/${mid}", "_self");
	            	}
	            	else {
	            		alert("用户信息保存失败！！！");
	            	}
	            } 
        }; 
        $("#userForm").ajaxSubmit(options); 
	});
	
	$('#btn_Back').click(function(){
		history.back();
	});
});
</script> 
<%@ include file="/bk/bottom.jsp" %>
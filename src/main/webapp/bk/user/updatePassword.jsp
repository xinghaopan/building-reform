<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/bk/top.jsp" %>
<form id="personForm" method="post" name="personForm" action="/bk/user/savePassword/${mid}">
<div class="mainFrame-center-navigation">
	<p>${navigation}</p>
</div>
<div class="mainFrame-center-form">
	<ul>
		<li style="display:none"><p>用户ID：</p><span><input id="id" name="id" class="web_01" type="text" value="${user.id}"  /></span></li>
		<li><p>帐号：</p><span><input id="name" name="name" class="web_01" type="text" value="${user.name}" readOnly /></span></li>
		<li><p>真实姓名：</p><span><input id="trueName" name="trueName" class="web_01" type="text" value="${user.trueName}" readOnly /></span></li>
		<li><p>原密码：</p><span><input id="opassword" name="opassword" class="web_01" type="password" value=""  /></span></li>
		<li><p>新密码：</p><span><input id="npassword" name="npassword" class="web_01" type="password" value=""  /></span></li>
		<li><p>确认密码：</p><span><input id="cpassword" name="cpassword" class="web_01" type="password" value=""  /></span></li>
	</ul>
</div>
</form>

<div class="mainFrame-center-form-editor">
    <div class="mainFrame-center-form-editor-title"></div> 
    <div class="mainFrame-center-form-editor-content">
        <img src="/images/back_13.jpg" class="web_02" id="btn_Submit" name="btn_Submit" />
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
		if (isNull($('#opassword').val())) {
			alert("原密码不能为空！！！");
			$('#opassword').focus();
			return;
		}
		
		if (isNull($('#npassword').val())) {
			alert("新密码不能为空！！！");
			$('#npassword').focus();
			return;
		}
		
		if ($('#npassword').val() != $('#cpassword').val()) {
			alert("新密码与确认密码不相同！！！");
			$('#cpassword').focus();
			return;
		}
		
		var options = { 
	            success : function(msg) {
	            	if (msg == "-999") {
		        		alert("999");
		        	}
	            	else if (msg == -1) {
	            		alert("原密码错误！！！");
		        	}
	            	else if (msg == 1) {
            			alert("用户密码修改成功！！！");
	            	}
	            	else {
	            		alert("用户密码修改失败！！！");
	            	}
	            } 
        }; 
        $("#personForm").ajaxSubmit(options); 
	});
	
	$('#btn_Back').click(function(){
		history.back();
	});
});
</script> 
<%@ include file="/bk/bottom.jsp" %>
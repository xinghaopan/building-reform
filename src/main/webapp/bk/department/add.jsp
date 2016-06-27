<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/bk/top.jsp" %>
<form id="departmentForm" method="post" name="departmentForm" action="/bk/department/save/${mid}" >
<div class="mainFrame-center-navigation">
	<p>${navigation}</p>
</div>
<div class="mainFrame-center-form">
	<ul>
		<li style="display:none"><p>栏目ID：</p><span><input id="id" name="id" class="web_01" type="text" value="${department.id}"  /></span></li>
		<li><p>父类名称：</p><span><input id="fatherName" name="fatherName" class="web_01" type="text" value="${fatherName}" ReadOnly="true" /></span></li>
		<li style="display:none"><p>父类id：</p><span><input id="fatherId" name="fatherId" class="web_01" type="text" value="${department.fatherId}" /></span></li>
		<li><p>单位名称：</p><span><input id="name" name="name" class="web_01" type="text" value="${department.name}"  /></span></li>
		<li>
			<p>是否执行：</p>
			<span>
				
				<input type="radio" id="isWork" name="isWork" value="1" checked="checked" />是
				<input type="radio" id="isWork" name="isWork" value="0" <c:if test="${department.isWork == 1}"> checked="checked" </c:if> />否
			</span>
		</li>
		<li><p>排序：</p><span><input id="order" name="order" class="web_01" type="text" value="${department.order}" /></span></li>
		<li>
			<p></p>
			<span>
				<img src="/images/back_13.jpg" class="web_02" id="btn_Submit" name="btn_Submit" />
				
				<img src="/images/back_44.jpg" class="web_02" id="btn_Back" name="btn_Back" onclick="javascript:history.go(-1);"/>
			</span>
		</li>
	</ul>
</div>
</form>
<div class="upload"></div>

<script type="text/javascript">
$(document).ready(function () {
	$('.mainFrame-first-a').click(function(){
		changeCenter($(this).attr("url"));
	});
	
	// ajaxSubmit
	$("#btn_Submit").click(function () {
		if (isNull($('#name').val())) {
			alert("单位名称不能为空！！！");
			$('#name').focus();
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
	            	else if (msg == 1) {
	            		alert("单位信息保存成功！！！");
	            		window.open("/bk/department/list/${mid}?fatherId=${department.fatherId}", "_self");
	            	}
	            	else {
	            		alert("单位信息保存失败！！！");
	            	}
	            } 
        }; 
        $("#departmentForm").ajaxSubmit(options); 
	});
	
});
</script> 
<%@ include file="/bk/bottom.jsp" %>
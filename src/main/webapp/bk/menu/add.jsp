<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/bk/top.jsp" %>
<form id="menuForm" method="post" name="menuForm" action="/bk/menu/save/${mid}" >
<div class="mainFrame-center-navigation">
	<p>${navigation}</p>
</div>
<div class="mainFrame-center-form">
	<ul>
		<li style="display:none"><p>栏目ID：</p><span><input id="id" name="id" class="web_01" type="text" value="${menu.id}"  /></span></li>
		<li><p>父类名称：</p><span><input id="fatherName" name="fatherName" class="web_01" type="text" value="${fatherName}" ReadOnly="true" /></span></li>
		<li style="display:none"><p>父类id：</p><span><input id="fatherId" name="fatherId" class="web_01" type="text" value="${menu.fatherId}" /></span></li>
		<li><p>前台名称：</p><span><input id="frontName" name="frontName" class="web_01" type="text" value="${menu.frontName}"  /></span></li>
		<li><p>特殊名称：</p><span><input id="frontSubName" name="frontSubName" class="web_01" type="text" value="${menu.frontSubName}"  /></span></li>
		<li><p>前台地址：</p><span><input id="frontLink" name="frontLink" class="web_01" type="text" value="${menu.frontLink}"  /></span></li>
		<li><p>后台名称：</p><span><input id="backName" name="backName" class="web_01" type="text" value="${menu.backName}"  /></span></li>
		<li><p>后台地址：</p><span><input id="backLink" name="backLink" class="web_01" type="text" value="${menu.backLink}" /></span></li>
		<li style="display:none"><p>导航图片大图：</p><span><input id="bigImage" name="bigImage" class="web_01" type="text" value="${menu.bigImage}" /></span></li>
		<li style="display:none"><p>导航图片中图：</p><span><input id="middleImage" name="middleImage" class="web_01" type="text" value="${menu.middleImage}" /></span></li>
		<li style="display:none"><p>导航图片小图：</p><span><input id="smallImage" name="smallImage" class="web_01" type="text" value="${menu.smallImage}" /></span></li>
		<li>
			<p>略缩图：</p>
			<span>
				<c:choose>
					<c:when test="${menu.smallImage != null && menu.smallImage != ''}">
						<img alt="" src="${menu.smallImage}" class="addFile" style="width: 120px; height: 80px;" />
					</c:when>
					<c:otherwise>
						<img alt="" src="/images/addImage.png" class="addFile" style="width: 120px; height: 80px;" />
					</c:otherwise>
				</c:choose> 
				 大小：120*80
			</span>
		</li>
		<li><p>排序：</p><span><input id="order" name="order" class="web_01" type="text" value="${menu.order}" /></span></li>
		<li>
			<p>显示类型：</p>
			<span>
				<input type="radio" id="type" name="type" value="0" checked="checked" />前台显示
				<input type="radio" id="type" name="type" value="2" <c:if test="${menu.type==2}"> checked="checked" </c:if> />后台显示
				<input type="radio" id="type" name="type" value="1" <c:if test="${menu.type==1}"> checked="checked" </c:if> />全部显示
			</span>
		</li>
		<li>
			<p>是否审核：</p>
			<span>
				<input type="radio" id="isaudit" name="isaudit" value="0" checked="checked" />是
				<input type="radio" id="isaudit" name="isaudit" value="1" <c:if test="${menu.isaudit==1}"> checked="checked" </c:if> />否
			</span>
		</li>
		<li>
			<p>专题：</p>
			<span>
				<input type="radio" id="isSubject" name="isSubject" value="0" checked="checked" />否
				<input type="radio" id="isSubject" name="isSubject" value="1" <c:if test="${menu.isSubject==1}"> checked="checked" </c:if> />是
			</span>
		</li>
		<li>
			<p>导航显示：</p>
			<span>
				<input type="radio" id="isNavigation" name="isNavigation" value="0" checked="checked" />不显示
				<input type="radio" id="isNavigation" name="isNavigation" value="1" <c:if test="${menu.isNavigation==1}"> checked="checked" </c:if> />显示
			</span>
		</li>
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
		if (isNull($('#frontName').val())) {
			alert("前台名称不能为空！！！");
			$('#frontName').focus();
			return;
		}
		
		if (isNull($('#backName').val())) {
			alert("后台名称不能为空！！！");
			$('#backName').focus();
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
	            		alert("功能信息保存成功！！！");
	            		window.open("/bk/menu/list/${mid}?fatherId=${menu.fatherId}", "_self");
	            	}
	            	else {
	            		alert("功能信息保存失败！！！");
	            	}
	            } 
        }; 
        $("#menuForm").ajaxSubmit(options); 
	});
	
	$('.addFile').click(function(){
		var a = new $.HUploader({ place: $(".upload"), files: $("#smallImage"), image: $('.addFile'), accept: "image/*" });
		a.open();
	});
	
});
</script> 
<%@ include file="/bk/bottom.jsp" %>
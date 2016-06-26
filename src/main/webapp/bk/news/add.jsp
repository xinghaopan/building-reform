<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/bk/top.jsp" %>
<form id="newsForm" method="post" name="newsForm" action="/bk/news/save/${mid}" enctype="multipart/form-data">
<div class="mainFrame-center-navigation">
	<p>${navigation}</p>
</div>
<div class="mainFrame-center-form">
	<ul>
		<li style="display:none"><p>文章ID：</p><span><input id="id" name="id" class="web_01" type="text" value="${news.id}"  /></span></li>
		<li style="display:none"><p>栏目ID：</p><span><input id="menuId" name="menuId" class="web_01" type="text" value="${news.menuId}"  /></span></li>
		<li><p>文章标题：</p><span><input id="title" name="title" class="web_01" type="text" value="${news.title}"  /></span></li>
		<li><p>作者：</p><span><input id="author" name="author" class="web_01" type="text" value="${news.author}"  /></span></li>
		<li style="display:none"><p>来源：</p><span><input id="source" name="source" class="web_01" type="text" value="${news.source}"  /></span></li>
		<li style="display:none"><p>大图片：</p><span><input id="bigImage" name="bigImage" class="web_01" type="text" value="${news.bigImage}"  /></span></li>
		<li style="display:none"><p>中图片：</p><span><input id="middleImage" name="middleImage" class="web_01" type="text" value="${news.middleImage}"  /></span></li>
		<li style="display:none"><p>略缩图：</p><span><input id="smallImage" name="smallImage" class="web_01" type="text" value="${news.smallImage}"  /></span></li>
		<li style="display:none"><p>略缩图：</p><span><input id="bigImg" name="bigImg" class="web_01" type="file" accept=".jpg"/></span></li>
		<li style="display:none"><p>略缩图：</p><span><input id="middleImg" name="middleImg" class="web_01" type="file" accept=".jpg"/></span></li>
		<li>
			<p>略缩图：</p>
			<span>
				<c:choose>
					<c:when test="${news.smallImage != null && news.smallImage != ''}">
						<img alt="" src="${news.smallImage}" class="addFile" style="width: 120px; height: 80px;" />
					</c:when>
					<c:otherwise>
						<img alt="" src="/images/addImage.png" class="addFile" style="width: 120px; height: 80px;" />
					</c:otherwise>
				</c:choose> 
				 大小：120*80
			</span>
		</li>
		<li style="display:none"><p>视频：</p><span><input id="video" name="video" class="web_01" type="text" value="${news.video}"  /></span></li>
		<li style="display:none"><p>链接地址：</p><span><input id="link" name="link" class="web_01" type="text" value="${news.link}"  /></span></li>
		<li style="display:none"><p>文件：</p><span><input id="file" name="file" class="web_01" type="text" value="${news.file}"  /></span></li>
		<li><p>排序：</p><span><input id="order" name="order" class="web_01" type="text" value="${news.order}"  /></span></li>
		<li>
			<p>置顶：</p>
			<span>
				<input id="istop" name="istop" type="radio" value="1" checked="checked" />置顶
				<input id="istop" name="istop" type="radio" value="0" <c:if test="${news.istop == 0}">checked="checked"</c:if>  />取消置顶
			</span>
		</li>
		<li>
			<p>图片新闻：</p>
			<span>
				
				<input id="flag" name="flag" type="radio" value="0" checked="checked" />不显示
				<input id="flag" name="flag" type="radio" value="1" <c:if test="${news.flag == 1}">checked="checked"</c:if> />显示
			</span>
		</li>
		<li>
			<p>限制IP：</p>
			<span>
				<input id="ip" name="ip" type="radio" value="1" checked="checked" />限制
				<input id="ip" name="ip" type="radio" value="0" <c:if test="${news.ip == 0}">checked="checked"</c:if>  />不限制
			</span>
		</li>
		<li style="display:none"><p>最新：</p><span><input id="isnew" name="isnew" class="web_01" type="text" value="${news.isnew}"  /></span></li>
		<li style="display:none"><p>审核：</p><span><input id="audit" name="audit" class="web_01" type="text" value="${news.audit}"  /></span></li>
		<li style="display:none"><p>点击：</p><span><input id="hits" name="hits" class="web_01" type="text" value="${news.hits}"  /></span></li>
		<li style="display:none"><p>关键字：</p><span><input id="key" name="key" class="web_01" type="text" value="${news.key}"  /></span></li>
		<li style="display:none"><p>描述：</p><span><input id="des" name="des" class="web_01" type="text" value="${news.des}"  /></span></li>
		<li><p>发布日期：</p><span><input id="date" name="date" class="web_01 Wdate" type="text" value="<fmt:formatDate value='${news.date}' pattern='yyyy-MM-dd' type='date' dateStyle='long' />" onClick="WdatePicker()"/></span></li>
		<li><p>简介：</p><span><textarea id="subTitle" name="subTitle" class="web_01" rows="3" cols="10" style="width:500px; height:200px;">${news.subTitle}</textarea></span></li>
		<li style="display:none"><p>简介：</p><span><input id="content" name="content" class="web_01" type="text" value="${news.content}" /></span></li>

	</ul>
</div>
</form>
<div class="upload"></div>
<div class="mainFrame-center-form-editor">
    <div class="mainFrame-center-form-editor-title">内容:</div> 
    <div class="mainFrame-center-form-editor-content"><textarea id="inputContent" name="inputContent">${news.content}</textarea></div>
</div>
<div class="mainFrame-center-form-editor">
    <div class="mainFrame-center-form-editor-title"></div> 
    <div class="mainFrame-center-form-editor-content">
        <img src="/images/back_13.jpg" class="web_02" id="btn_Submit" name="btn_Submit" />
        <img src="/images/back_44.jpg" class="web_02" id="btn_Back" name="btn_Back" />
    </div>
</div>

<script type="text/javascript">
$(document).ready(function () {
	if (editor != null) {
        editor = null;
    }
    editor = new baidu.editor.ui.Editor();
    editor.render('inputContent');
    
	// ajaxSubmit
	$("#btn_Submit").click(function () {
		if (isNull($('#title').val())) {
			alert("文章标题不能为空！！！");
			$('#title').focus();
			return;
		}
		
		if (!isUnsignedInteger($('#order').val())) {
			alert("排序只能为数字！！！");
			$('#order').focus();
			return;
		}
		
		if (isNull($('#date').val())) {
			alert("文章发布日期不能为空！！！");
			$('#date').focus();
			return;
		}
		
		$('#content').val(editor.getContent());
		
		var options = { 
	            success : function(msg) {
	            	if (msg == "-999") {
		        		alert("999");
		        		//outLogin();
		        	}
	            	else if (msg == 1) {
	            		alert("文章信息保存成功！！！");
	            		window.open("/bk/news/list/${mid}", "_self");
	            	}
	            	else {
	            		alert("文章信息保存失败！！！");
	            	}
	            } 
        }; 
        $("#newsForm").ajaxSubmit(options); 
	});
	
	$('.addFile').click(function(){
		var a = new $.HUploader({ place: $(".upload"), files: $("#smallImage"), image: $('.addFile'), accept: "image/*" });
		a.open();
	});
	
	$('#btn_Back').click(function(){
		history.back();
	});
});
</script> 
<%@ include file="/bk/bottom.jsp" %>
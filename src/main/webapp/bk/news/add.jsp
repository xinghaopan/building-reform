<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/bk/top.jsp" %>

<!-- Content -->
<div id="content">
	<div class="breadcrumb">
		<img src="/images/photo_02.jpg"  alt=""/>&nbsp;您当前的位置：
		<b class="center-navigation" style="font-weight:normal"></b>
	</div>
	
	<div class="row-fluid row-merge"></div>

	<div class="innerLR">
		
		<div class="separator bottom"></div>
		
<form id="newsForm" method="post" name="newsForm" action="/bk/news/save/${mid}" enctype="multipart/form-data">
<div class="widget" data-toggle="collapse-widget">
			<div class="widget-head">
				<p class="heading glyphicons folder_open"><i></i>新闻信息</p>
			</div>
			
			<div class="widget-body">

	<div class="row-fluid" style="display:none;">
		<div>
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="id">id：</label>
			<div class="controls"><input class="span9" id="id" name="id" type="text" value="${news.id}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="menuId">父类id：</label>
			<div class="controls"><input class="span9" id="menuId" name="menuId" type="text" value="${news.menuId}" /></div>
		</div>
		<!-- // Group END -->
		</div>
	</div>
	
	<div class="row-fluid">
		<div>
		<!-- Group -->
		<div class="control-group span5">
			<label class="control-label span4" for="title">文章标题：</label>
			<div class="controls"><input class="span8" id="title" name="title" type="text" value="${news.title}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span5">
			<label class="control-label span4" for="author">作者：</label>
			<div class="controls"><input class="span8" id="author" name="author" type="text" value="${news.author}" /></div>
		</div>
		<!-- // Group END -->
		</div>
	</div>

	<div class="row-fluid" style="display:none;">
		<div>
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="source">来源：</label>
			<div class="controls"><input class="span9" id="source" name="source" type="text" value="${news.source}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="subTitle">简介：</label>
			<div class="controls"><input class="span9" id="subTitle" name="subTitle" type="text" value="${news.subTitle}" /></div>
		</div>
		<!-- // Group END -->
		</div>
	</div>
	
	<div class="row-fluid" style="display:none;">
		<div>
		<!-- Group -->
		<div class="control-group span4">
			<label class="control-label span3" for="bigImage">大图片：</label>
			<div class="controls"><input class="span9" id="bigImage" name="bigImage" type="text" value="${news.bigImage}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span4">
			<label class="control-label span3" for="middleImage">中图片：</label>
			<div class="controls"><input class="span9" id="middleImage" name="middleImage" type="text" value="${news.middleImage}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span4">
			<label class="control-label span3" for="smallImage">略缩图：</label>
			<div class="controls"><input class="span9" id="smallImage" name="smallImage" type="text" value="${news.smallImage}" /></div>
		</div>
		<!-- // Group END -->
		</div>
	</div>
	
	<div class="row-fluid" style="display:none;">
		<div>
		<!-- Group -->
		<div class="control-group span4">
			<label class="control-label span3" for="video">视频：</label>
			<div class="controls"><input class="span9" id="video" name="video" type="text" value="${news.video}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span4">
			<label class="control-label span3" for="link">链接：</label>
			<div class="controls"><input class="span9" id="link" name="link" type="text" value="${news.link}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span4">
			<label class="control-label span3" for="file">文件：</label>
			<div class="controls"><input class="span9" id="file" name="file" type="text" value="${news.file}" /></div>
		</div>
		<!-- // Group END -->
		</div>
	</div>
		
	<div class="row-fluid" style="display:none;">
		<div>
		<!-- Group -->
		<div class="control-group span4">
			<label class="control-label span3" for="isnew">最新：</label>
			<div class="controls"><input class="span9" id="isnew" name="isnew" type="text" value="${news.isnew}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span4">
			<label class="control-label span3" for="audit">审核：</label>
			<div class="controls"><input class="span9" id="audit" name="audit" type="text" value="1" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span4">
			<label class="control-label span3" for="hits">点击：</label>
			<div class="controls"><input class="span9" id="hits" name="hits" type="text" value="${news.hits}" /></div>
		</div>
		<!-- // Group END -->
		</div>
	</div>
		
	<div class="row-fluid" style="display:none;">
		<div>
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="key">关键字：</label>
			<div class="controls"><input class="span9" id="key" name="key" type="text" value="${news.key}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="des">描述：</label>
			<div class="controls"><input class="span9" id="des" name="des" type="text" value="${news.des}" /></div>
		</div>
		<!-- // Group END -->
		</div>
	</div>
		
	<div class="row-fluid">
		<div>
		<!-- Group -->
		<div class="control-group span5">
			<label class="control-label span4" for="smallImage">置顶：</label>
			<div class="controls">
				<select id="istop" name="istop" class="span8">
					<option value="1" selected >置顶</option>
					<option value="0" <c:if test="${news.istop == null || news.istop == 0}">selected</c:if> >不置顶</option>
				</select>
			</div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span5">
			<label class="control-label span4" for="order">排序：</label>
			<div class="controls"><input class="span8" id="order" name="order" type="text" value="${news.order}" /></div>
		</div>
		<!-- // Group END -->
		</div>
	</div>
	
	<div class="row-fluid">
		<div>
		<!-- Group -->
		<div class="control-group span5">
			<label class="control-label span4" for="date">日期：</label>
			<div class="controls"><input class="span8" id="date" name="date" type="text" value="<fmt:formatDate value='${news.date}' pattern='yyyy-MM-dd' type='date' dateStyle='long' />"  onClick="WdatePicker()" /></div>
		</div>
		<!-- // Group END -->
		</div>
	</div>
	
	<div class="row-fluid" >
		<div>
		<!-- Group -->
		<div class="control-group span10">
			<label class="control-label span2" for="content">内容：</label>
			<div class="controls"><textarea class="span10" id="inputContent" name="inputContent" style="height:300px;">${news.content}</textarea></div>
		</div>
		<!-- // Group END -->
		</div>
	</div>
	</div>
		</div>
		
		<div class="separator bottom"></div>
		
		<!-- Modal footer -->
		<div class="modal-footer">
			<a href="javascript:void(0);" id="btn_Submit" name="btn_Submit" class="btn btn-news" >提交</a>
			
			<a href="javascript:void(0);" id="btn_Back" name="btn_Back" class="btn btn-default" data-dismiss="modal">返回</a> 
		</div>
		<!-- // Modal footer END -->
		
		</form>
	</div>	
		
</div>


<script type="text/javascript">
	if (editor != null) {
        editor = null;
    }
    editor = new baidu.editor.ui.Editor();
    editor.render('inputContent');

jQuery(document).ready(function($) {
	$("#btn_Back").live("click", function() {
		window.open("/bk/news/list/${mid}", "_self");
	});
	
	$("#btn_Submit").live("click", function() { 
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
		
		$('#subTitle').val(editor.getContent());
		
		
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
});
</script> 
<%@ include file="/bk/bottom.jsp" %>
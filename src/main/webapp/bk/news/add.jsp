<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<form id="newsForm" method="post" name="newsForm" action="/bk/news/save/${mid}" enctype="multipart/form-data">
<!-- Modal heading -->
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h3>新闻信息</h3>
</div>
<!-- // Modal heading END -->

<!-- Modal body -->
<div class="modal-body">

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
		<div class="control-group span6">
			<label class="control-label span3" for="title">文章标题：</label>
			<div class="controls"><input class="span9" id="title" name="title" type="text" value="${news.title}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="author">作者：</label>
			<div class="controls"><input class="span9" id="author" name="author" type="text" value="${news.author}" /></div>
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
		<div class="control-group span4">
			<label class="control-label span3" for="smallImage">置顶：</label>
			<div class="controls">
				<select id="istop" name="istop" class="span9">
					<option value="1" selected >置顶</option>
					<option value="0" <c:if test="${news.istop == null || news.istop == 0}">selected</c:if> >不置顶</option>
				</select>
			</div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span4">
			<label class="control-label span3" for="order">排序：</label>
			<div class="controls"><input class="span9" id="order" name="order" type="text" value="${news.order}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span4">
			<label class="control-label span3" for="date">日期：</label>
			<div class="controls"><input class="span9" id="date" name="date" type="text" value="<fmt:formatDate value='${news.date}' pattern='yyyy-MM-dd' type='date' dateStyle='long' />"  onClick="WdatePicker()" /></div>
		</div>
		<!-- // Group END -->
		</div>
	</div>
	
	<div class="row-fluid" >
		<div>
		<!-- Group -->
		<div class="control-group span12">
			<label class="control-label span1" for="content">内容：</label>
			<div class="controls"><textarea class="span11" id="inputContent" name="inputContent">${news.content}</textarea></div>
		</div>
		<!-- // Group END -->
		</div>
	</div>
</div>
<!-- // Modal body END -->

<!-- Modal footer -->
<div class="modal-footer">
	<a href="javascript:void(0);" class="btn btn-default" data-dismiss="modal">关闭</a> 
	<a href="javascript:void(0);" id="btn_Submit" name="btn_Submit" class="btn btn-info" >提交</a>
</div>
<!-- // Modal footer END -->
</form>

<script type="text/javascript">
	if (editor != null) {
        editor = null;
    }
    editor = new baidu.editor.ui.Editor();
    editor.render('inputContent');
</script> 

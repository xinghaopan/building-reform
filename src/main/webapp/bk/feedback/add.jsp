<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="feedbackForm" method="post" name="feedbackForm" action="/bk/feedback/saveAdd/${mid}" >
<!-- Modal heading -->
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h3>反馈信息</h3>
</div>
<!-- // Modal heading END -->

<!-- Modal body -->
<div class="modal-body">

	<div class="row-fluid" style="display:none;">
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="id">id：</label>
			<div class="controls"><input class="span9" id="id" name="id" type="text" value="${feedback.id}" /></div>
		</div>
		<!-- // Group END -->
	</div>
	
	<div class="row-fluid">
		<div class="control-group span10">
			<label class="control-label span2" for="title">标题：</label>
			<div class="controls"><input class="span10" id="title" name="title" type="text" value="${feedback.title}" /></div>
		</div>
	</div>
	
	<div class="control-group row-fluid">
		<label class="control-label">反馈内容：</label>
		<div class="controls">
			<textarea class="wysihtml5 span12" id="askContent" name="askContent" rows="5">${feedback.askContent}</textarea>
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
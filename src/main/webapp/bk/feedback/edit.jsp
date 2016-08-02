<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<form id="feedbackForm" method="post" name="feedbackForm" action="/bk/feedback/saveEdit/${mid}" >
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
			<label class="control-label span3" for="title">标题：</label>
			<div class="controls"><input class="span9" id="title" name="title" type="text" value="${feedback.title}" /></div>
		</div>

	</div>
	
	<div class="control-group row-fluid">
		<label class="control-label">反馈内容：</label>
		<div class="controls">
			<textarea id="mustHaveId" class="wysihtml5 span12" id="askContent" name="askContent" rows="5">${feedback.askContent}</textarea>
		</div>
	</div>
	
	<div class="row-fluid">
		<div class="control-group span6">
			<label class="control-label span3" for="firstname">反馈人：</label>
			<div class="controls"><input class="span9" id="firstname" name="firstname" type="text" value="${feedback.userName}"/></div>
		</div>
		
		<div class="control-group span6">
			<label class="control-label span3" for="lastname">反馈时间：</label>
			<div class="controls"><input class="span9" id="lastname" name="lastname" type="text" value='<fmt:formatDate value="${feedback.date}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />'/></div>
		</div>
	</div>
	<div class="control-group row-fluid">
		<label class="control-label">回复内容：</label>
		<div class="controls">
			<textarea id="mustHaveId" class="wysihtml5 span12" id="replyContent" name="replyContent" rows="5">${feedback.replyContent}</textarea>
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
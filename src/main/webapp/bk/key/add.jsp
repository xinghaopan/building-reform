<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="keyForm" method="post" name="keyForm" action="/bk/key/save/${mid}" >
<!-- Modal heading -->
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h3>健值信息</h3>
</div>
<!-- // Modal heading END -->

<!-- Modal body -->
<div class="modal-body">

	<div class="row-fluid" style="display:none;">
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="id">id：</label>
			<div class="controls"><input class="span9" id="id" name="id" type="text" value="${key.id}" /></div>
		</div>
		<!-- // Group END -->
	</div>
	
	<div class="row-fluid">
		<div>
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="name">健值名称：</label>
			<div class="controls"><input class="span9" id="name" name="name" type="text" value="${key.name}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="value">健值：</label>
			<div class="controls"><input class="span9" id="value" name="value" type="text" value="${key.value}" /></div>
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
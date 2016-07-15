<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="quotaForm" method="post" name="quotaForm" action="/bk/quota/save/${mid}">
<!-- Modal heading -->
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h3>用户信息</h3>
</div>
<!-- // Modal heading END -->

<!-- Modal body -->
<div class="modal-body">

	<div class="row-fluid" style="display:none;">
		<div>
		<!-- Group -->
		<div class="control-group span4">
			<label class="control-label span3" for="id">id：</label>
			<div class="controls"><input class="span9" id="id" name="id" type="text" value="${quota.id}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span4">
			<label class="control-label span3" for="departmentId">机构id：</label>
			<div class="controls"><input class="span9" id="departmentId" name="departmentId" type="text" value="${quota.departmentId}" /></div>
		</div>
		<!-- // Group END -->
		</div>
		
		<!-- Group -->
		<div class="control-group span4">
			<label class="control-label span3" for="restNum">剩余数量：</label>
			<div class="controls"><input class="span9" id="restNum" name="restNum" type="text" value="${quota.restNum}" /></div>
		</div>
		<!-- // Group END -->
		</div>
	</div>
		
	<div class="row-fluid">
		<div>
		<!-- Group -->
		<div class="control-group span2">
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span8">
			<label class="control-label span3" for="departmentName">机构：</label>
			<div class="controls"><input class="span9" id="departmentName" name="departmentName" type="text" value="${quota.departmentName}" readOnly="readonly" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span2">
		</div>
		<!-- // Group END -->
		</div>
	</div>
	
	<div class="row-fluid">
		<div>
		<!-- Group -->
		<div class="control-group span2">
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span8">
			<label class="control-label span3" for="year">年度：</label>
			<div class="controls"><input class="span9" id="year" name="year" type="text" value="${quota.year}" readOnly="readonly" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span2">
		</div>
		<!-- // Group END -->
		</div>
	</div>
	
	<div class="row-fluid">
		<div>
		<!-- Group -->
		<div class="control-group span2">
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span8">
			<label class="control-label span3" for="num">指标数量：</label>
			<div class="controls"><input class="span9" id="num" name="num" type="text" value="${quota.num}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span2">
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

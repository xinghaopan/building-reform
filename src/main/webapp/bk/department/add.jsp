<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="departmentForm" method="post" name="departmentForm" action="/bk/department/save/${mid}" >
<!-- Modal heading -->
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h3>组织结构信息</h3>
</div>
<!-- // Modal heading END -->

<!-- Modal body -->
<div class="modal-body">

	<div class="row-fluid" style="display:none;">
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="fatherId">父类id：</label>
			<div class="controls"><input class="span9" id="fatherId" name="fatherId" type="text" value="${department.fatherId}" /></div>
		</div>
		<!-- // Group END -->
	</div>
	
	<div class="row-fluid">
		<div class="control-group span6">
			<label class="control-label span3">父类名称：</label>
			<div class="controls"><input class="span9" id="fatherName" name="fatherName" type="text" value="${fatherName}" /></div>
		</div>
		
		<div class="control-group span6">
			<label class="control-label span3" for="order">指标管理机构：</label>
			<div class="controls"><input class="span9" id="quotaManageId" name="quotaManageId" type="text" value="${department.quotaManageId}" /></div>
		</div>
		
		<div class="control-group span6" style="display:none;">
			<label class="control-label span3" for="order">排序：</label>
			<div class="controls"><input class="span9" id="order" name="order" type="text" value="${department.order}" /></div>
		</div>
	</div>
	
	<div class="row-fluid">
		<div>
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="id">机构编码：</label>
			<div class="controls"><input class="span9" id="id" name="id" type="text" value="${department.id}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="name">机构名称：</label>
			<div class="controls"><input class="span9" id="name" name="name" type="text" value="${department.name}" /></div>
		</div>
		<!-- // Group END -->
		
		</div>
	</div>
	
	<div class="row-fluid">
		<div>
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="isWork">是否执行审核：</label>
			<div class="controls">
				<select id="isWork" name="isWork" class="span9">
					<option value="1" selected>是</option>
					<option value="0" <c:if test="${department.isWork == null || department.isWork == 0}">selected</c:if> >否</option>
				</select>
			</div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="smallImage">是否统计：</label>
			<div class="controls">
				<select id="isStatistics" name="isStatistics" class="span9">
					<option value="1" selected>是</option>
					<option value="0" <c:if test="${department.isStatistics == null || department.isStatistics == 0}">selected</c:if> >否</option>
				</select>
			</div>
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
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="userForm" method="post" name="userForm" action="/bk/user/save/${mid}">
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
		<div class="control-group span6">
			<label class="control-label span3" for="id">id：</label>
			<div class="controls"><input class="span9" id="id" name="id" type="text" value="${user.id}" /></div>
		</div>
		
		<div class="control-group span6">
			<label class="control-label span3" for="password">密码：</label>
			<div class="controls"><input class="span9" id="password" name="password" type="text" value="${user.password}" /></div>
		</div>
		</div>
	</div>
		
	<div class="row-fluid">
		<div>
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="name">帐号：</label>
			<div class="controls"><input class="span9" id="name" name="name" type="text" value="${user.name}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="trueName">真实姓名：</label>
			<div class="controls"><input class="span9" id="trueName" name="trueName" type="text" value="${user.trueName}" /></div>
		</div>
		<!-- // Group END -->
		</div>
	</div>
	
	<div class="row-fluid">
		<div>
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="smallImage">角色：</label>
			<div class="controls">
				<select id="role.id" name="role.id" class="span9 role">
					<option value="0" selected>请选择</option>
					<c:forEach items="${roleList}" var="srole">
						<option value="${srole.id}" <c:if test="${srole.id == user.role.id}">selected</c:if> >${srole.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="order">机构编码：</label>
			<div class="controls"><input class="span9" id="departmentId" name="departmentId" type="text" value="${user.departmentId}" /></div>
		</div>
		<!-- // Group END -->
		
		<div class="control-group span6" style="display:none;">
			<label class="control-label span3" for="key">key：</label>
			<div class="controls"><input class="span9" id="key" name="key" type="text" value="${user.key}" /></div>
		</div>
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
	var id = '${user.id}';
	if (id == null || id == '') {
		id = 0;
	}
</script> 

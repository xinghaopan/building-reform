<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="menuForm" method="post" name="menuForm" action="/bk/menu/save/${mid}" >
<!-- Modal heading -->
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h3>功能信息</h3>
</div>
<!-- // Modal heading END -->

<!-- Modal body -->
<div class="modal-body">

	<div class="row-fluid" style="display:none;">
		<div>
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="id">id：</label>
			<div class="controls"><input class="span9" id="id" name="id" type="text" value="${menu.id}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="fatherId">父类id：</label>
			<div class="controls"><input class="span9" id="fatherId" name="fatherId" type="text" value="${menu.fatherId}" /></div>
		</div>
		<!-- // Group END -->
		</div>
	</div>
	
	<div class="row-fluid">
		<div class="control-group span6">
		<label class="control-label span3">父类名称：</label>
		<div class="controls"><input class="span9" id="fatherName" name="fatherName" type="text" value="${fatherName}" /></div>
		</div>
	</div>
	
	<div class="row-fluid">
		<div>
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="frontName">前台名称：</label>
			<div class="controls"><input class="span9" id="frontName" name="frontName" type="text" value="${menu.frontName}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="frontLink">前台地址：</label>
			<div class="controls"><input class="span9" id="frontLink" name="frontLink" type="text" value="${menu.frontLink}" /></div>
		</div>
		<!-- // Group END -->
		</div>
	</div>
	
	<div class="row-fluid">
		<div>
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="backName">后台名称：</label>
			<div class="controls"><input class="span9" id="backName" name="backName" type="text" value="${menu.backName}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="backLink">后台地址：</label>
			<div class="controls"><input class="span9" id="backLink" name="backLink" type="text" value="${menu.backLink}" /></div>
		</div>
		<!-- // Group END -->
		</div>
	</div>
	
	<div class="row-fluid">
		<div>
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="smallImage">图标：</label>
			<div class="controls"><input class="span9" id="smallImage" name="smallImage" type="text" value="${menu.smallImage}" /></div>
		</div>
		<!-- // Group END -->
		
		<!-- Group -->
		<div class="control-group span6">
			<label class="control-label span3" for="order">排序：</label>
			<div class="controls"><input class="span9" id="order" name="order" type="text" value="${menu.order}" /></div>
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
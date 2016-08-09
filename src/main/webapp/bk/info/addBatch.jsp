<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- Modal heading -->
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h3>批量审核</h3>
</div>
<!-- // Modal heading END -->

<!-- Modal body -->
<div class="modal-body">
	<form id="infoForm" method="post" name="infoForm" action="">
	<div class="row-fluid">
		<table class="table table-bordered table-condensed table-striped table-primary table-vertical-center checkboxs" style="border-top:#dddddd 1px solid">
			<tbody>
				<tr class="selectable">
					<td class="center" width="130">审核意见：</td>
					<td class="left">
						 <textarea id="auditInfo" name="auditInfo" rows="6" style="width:98%"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	</form>
</div>
<!-- // Modal body END -->

<!-- Modal footer -->
<div class="modal-footer">
	<a href="javascript:void(0);" class="btn btn-default" data-dismiss="modal">关闭</a> 
	<a href="javascript:void(0);" url="/bk/info/batchSubmit/${mid}" class="btn btn-info action-batch-pass" >审核通过</a>
	<a href="javascript:void(0);" url="/bk/info/batchBack/${mid}" class="btn btn-info action-batch-back" >审核退回</a>
</div>
<!-- // Modal footer END -->

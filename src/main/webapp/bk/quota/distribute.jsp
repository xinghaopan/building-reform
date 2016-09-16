<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/bk/top.jsp" %>
<script>
	var departmentList = new Array();
	
	function pushDepartment(id, fatherId, quotaManageId, name, isWork) {
		var current = new Object();
	    current.id = id;
	    current.fatherId = fatherId;
	    current.quotaManageId = quotaManageId;
	    current.name = name;
	    current.isWork = isWork;
	    departmentList.push(current);
	}
</script>

<c:forEach items="${departmentList}" var="sdepartment">
	<script>pushDepartment('${sdepartment.id}', '${sdepartment.fatherId}', '${sdepartment.quotaManageId}', '${sdepartment.name}', '${sdepartment.isWork}');</script>
</c:forEach>

<!-- Content -->
<div id="content">
	<div class="breadcrumb">
		<img src="/images/photo_02.jpg"  alt=""/>&nbsp;您当前的位置：
		<b class="center-navigation" style="font-weight:normal"></b>
	</div>
	
	<div class="row-fluid row-merge"></div>

	<div class="innerLR">

		<div class="separator bottom"></div>
	
	 	<form id="quotaForm" method="post" name="quotaForm" action="/bk/quota/distributeSave/${mid}">
	 	<input id="id" name="id" value="${quota.id}" type="hidden" />
	 	<!-- Website Traffic Chart -->
		<div class="widget" data-toggle="collapse-widget">
			<div class="widget-head">
				<p class="heading glyphicons folder_open"><i></i>指标发放</p>
			</div>
			
			<div class="widget-body">
	           <div class="row-fluid">
					
					<!-- Column -->
					<div>
						
						<!-- Group -->
						<div class="control-group span8">
							<label class="control-label span4" for="department">下发机构：</label>
							<div class="controls">
								<c:if test="${fn:length(user.departmentId) == 2}">
									<select id="fatherDepartmentId" name="fatherDepartmentId" class="span4"></select>
								</c:if>
								<select id="departmentId" name="departmentId" class="span4"><option value='-1'>请选择</option></select>
								
								<input id="departmentName" name="departmentName" type="hidden" />
							</div>
						</div>
						<!-- // Group END -->
						
					</div>
                    
                    <div>
                    	<!-- Group -->
						<div class="control-group span8">
							<label class="control-label span4" for="year">年度：</label>
							<div class="controls">
								<select id="year" name="year" class="span8">
									<option value="-1" selected>请选择</option>
									<c:forEach items="${dicList}" var="sdic">
										<c:if test="${sdic.keyValue == 'planYear'}">
											<option value="${sdic.value}" >${sdic.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						<!-- // Group END -->
                    </div>
                    
                    <div>
						
						
						<!-- Group -->
						<div class="control-group span8">
							<label class="control-label span4" for="num">发放数量：</label>
							<div class="controls"><input class="span8" id="num" name="num" type="text" /></div>
						</div>
						<!-- // Group END -->
						
					</div>
				</div>
			</div>
		</div>
		<!-- // Website Traffic Chart END -->
		
		<div class="separator bottom"></div>
		
		<!-- Modal footer -->
		<div class="modal-footer">
			<a href="javascript:void(0);" id="btn_Submit" name="btn_Submit" class="btn btn-quota" >提交</a>
		</div>
		<!-- // Modal footer END -->
		
		</form>
	</div>	
		
</div>
<script type="text/javascript">
jQuery(document).ready(function($) {
	var departmentId = '${user.departmentId}';
	if (departmentId.length == 2) {
		$("#fatherDepartmentId").empty();
		$("#fatherDepartmentId").append("<option value='-1'>请选择</option>");
		
		for (var i = 0; i < departmentList.length; i ++) {
			if (departmentList[i].fatherId == departmentId) {
				 $("#fatherDepartmentId").append("<option value='" + departmentList[i].id + "'>" + departmentList[i].name + "</option>");  
			}
		}
		
		$("#fatherDepartmentId").change(function() {
			setDepartmentId($(this).val(), $(this).find("option:selected").text(), 1);
		});
	} else {
		setDepartmentId(departmentId, '', 0);
	}
	
	function setDepartmentId(fatherId, text, type) {
		$("#departmentId").empty();
		
		if (type == 0) {
			for (var i = 0; i < departmentList.length; i ++) {
				// 找出指定机构id的直系子机构
				if (departmentList[i].fatherId == fatherId) {
					$("#departmentId").append("<option value='" + departmentList[i].id + "' text='" + departmentList[i].name + "'>" + departmentList[i].name + "</option>");
				}
			}
		}
		else {
			// 循环机构
			for (var i = 0; i < departmentList.length; i ++) {
				// 找出指定机构id的直系子机构
				if (departmentList[i].fatherId == fatherId) {
					// 虽然是直系子机构，但指标管理机构不是本机构，显示出来
					if (departmentList[i].fatherId != departmentList[i].quotaManageId) {
						$("#departmentId").append("<option value='" + departmentList[i].id + "' text='" + departmentList[i].name + "'>" + departmentList[i].name + "</option>");	
					}
				}
			}
			
			$("#departmentId").prepend("<option value='" + fatherId + "' text='" + text + "'>市本级</option>");
		}
		
		$("#departmentId").prepend("<option value='-1'>请选择</option>");
	}
	
	$("#btn_Submit").live("click", function() { 
		if ($('#departmentId').val() == -1) {
			alert("请选择下发机构！！！");
			$('#departmentId').focus();
			return;
		}
		$("#departmentName").val($("#departmentId").find("option:selected").attr("text"));
		
		if ($('#year').val() == -1) {
			alert("请选择年度！！！");
			$('#year').focus();
			return;
		}
		
		if (!isUnsignedInteger($('#num').val())) {
			alert("发放数量只能为数字！！！");
			$('#num').focus();
			return;
		}
		
		var options = { 
	            success : function(msg) {
	            	if (msg == "-999") {
		        		alert("999");
		        	}
	            	else if (msg == -1) {
	            		alert("本单位剩余指标不够此次发放！！！");
		        	}
	            	else if (msg == -2) {
	            		alert("下发单位的指标已经存在！！！");
		        	}
	            	else if (msg == 1) {
	            		alert("指标发放成功！！！");
	            		window.open("/bk/quota/list/${mid}", "_self");
	            	}
	            	else {
	            		alert("指标发放保存失败！！！");
	            	}
	            } 
        }; 
        $("#quotaForm").ajaxSubmit(options); 
	});
	
});
</script>
<%@ include file="/bk/bottom.jsp" %>
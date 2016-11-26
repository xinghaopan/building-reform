<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/bk/top.jsp" %>
<!-- Content -->
<div id="content">
	<div class="breadcrumb">
		<img src="/images/photo_02.jpg"  alt=""/>&nbsp;您当前的位置：
		<b class="center-navigation" style="font-weight:normal"></b>
	</div>
	
	<div class="row-fluid row-merge"></div>

	<div class="innerLR">

	    <!-- Modal -->
		<div class="modal hide fade" id="modal-simple"></div>
		<!-- // Modal END -->	
	
		<div class="separator bottom"></div>
	
	 
	 	<div class="widget" data-toggle="collapse-widget">
			<div class="widget-head">
				<p class="heading glyphicons edit "></p>
			</div>
	         
			<div class="widget-body">
	           
				<div class="row-fluid">
					<div class="widget-titel">
						<div class="span7">
							<div class="input-append">
								<input type="text" id="personName" name="personName" value="${personName}" placeholder="农户姓名" >&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" id="personId" name="personId" value="${personId}" placeholder="身份证号" >&nbsp;&nbsp;&nbsp;&nbsp;
								<select id="year" name="year">
									<c:forEach items="${dicList}" var="sdic">
										<c:if test="${sdic.keyValue == 'planYear'}">
											<option value="${sdic.value}" <c:if test="${sdic.value == year}">selected</c:if> >${sdic.name}</option>
										</c:if>
									</c:forEach>
								</select>
								<button class="btn btn_Search" type="button" currentPage="0">搜索</button>
							</div>
           				</div>
						
			           	<div class=" pull-right">
			           	</div>
					</div>
	           </div>
	         
				<div class="row-fluid">
					<table class="table table-bordered table-condensed table-striped table-primary table-vertical-center checkboxs">
					<thead>
						<tr>
							<th style="width: 1%;" class="uniformjs"><input type="checkbox" /></th>
							<th class="center">户主姓名</th>
							<th class="center">身份证号</th>
							<th class="center">民族</th>
							<th class="center">联系电话</th>
							<th class="center">机构</th>
							<th class="center">当前状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="sinfo">
							<!-- Item -->
							<tr class="selectable">
								<td class="center uniformjs"><input type="checkbox" /></td>
								<td class="center">${sinfo.personName}</td>
								<td class="center">${sinfo.personId}</td>
								<td class="center">${sinfo.personNation}</td>
								<td class="center">${sinfo.personTel}</td>
								<td class="center">${sinfo.departmentName}</td>
								<td class="center">
									<c:choose>
										<c:when test="${sinfo.state == 0}">编辑中</c:when>
										<c:when test="${sinfo.state == 80}">等待镇审核</c:when>
										<c:when test="${sinfo.state == 60}">等待县区审核</c:when>
										<c:when test="${sinfo.state == 40}">等待市审核</c:when>
										<c:when test="${sinfo.state == 20}">等待省厅审核</c:when>
										<c:when test="${sinfo.state == 10}">审核结束</c:when>
										<c:when test="${sinfo.state == 5}">归档</c:when>
										<c:when test="${sinfo.state == -1}">退回</c:when>
										<c:when test="${sinfo.state == -2}">撤回</c:when>
										
										<c:otherwise>未知</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<!-- // Item END -->
						</c:forEach>
					</tbody>
					</table>
				</div>
			</div>
		</div>
	    
	 	<div class="separator bottom"></div> 
	    
	    <div class="pagination pagination-centered margin-none">
		
			<ul>
				<li>&nbsp;&nbsp;&nbsp;&nbsp;每页条数：<input id="count" name="count" type="text" value="${count}" class="page_count" style="width:25px;"/></li>
				${pages}
			</ul>
		</div>
		
		<div class="separator bottom"></div> 
	    
		<div class="widget widget-tabs">		
			<div class="widget-body">
	
			</div>
		</div>
		<!-- // Google Vizualization DataTable Widget END -->
		
	</div>	
		
</div>
<script type="text/javascript">
jQuery(document).ready(function($) {
	$('.action-del').click(function(){
		if( confirm('您确定要删除【' + $(this).attr("bname") + '】吗？') ){
			var url = $(this).attr("url");
			$.ajax({
				type : "get",
				url : url,
				data : "radom=" + Math.random(),
				dataType : "text",
				success : function(msg) {
					if (msg == "-999") {
		        		outLogin();
		        	}
	            	else if (msg == 1) {
	            		window.location.reload();
					}
					else {
						alert('【' + $(this).attr("bname") + '】删除失败！！！');
					}
				},
				error : function(XMLHttpRequest, error, errorThrown) {
					//alert("请求超时！！！");
				}
			});
		}
	});
	
	$('.action-edit').click(function(){
		var url = $(this).attr("url");
		
		$.ajax({
			type : "get",
			url : url,
			data : "radom=" + Math.random(),
			dataType : "text",
			success : function(text) {
				$("#modal-simple").html(text);
			},
			error : function(XMLHttpRequest, error, errorThrown) {
				//alert("请求超时！！！");
			}
		});
	});
	
	$("#btn_Submit").live("click", function() { 
		if (!isUnsignedInteger($('#num').val())) {
			alert("指标数量只能为数字！！！");
			$('#num').focus();
			return;
		}
		
		var options = { 
	            success : function(msg) {
	            	if (msg == "-999") {
		        		alert("999");
		        	}
	            	else if (msg == -1) {
	            		alert("修改后的年度指标不符合规则！！！");
		        	}
	            	else if (msg == -2) {
	            		alert("此机构的年度指标已经存在！！！");
		        	}
	            	else if (msg == 1) {
	            		alert("年度指标信息保存成功！！！");
	            		window.location.reload();
	            	}
	            	else {
	            		alert("年度指标信息保存失败！！！");
	            	}
	            } 
        }; 
        $("#infoForm").ajaxSubmit(options); 
	});
	
	$('.btn_Search').click(function(){
		var para = "?currentPage=" + $(this).attr("currentPage") + "&count=" + $('#count').val() + "&year=" + $('#year').val();
		if ($("#personName").val() != '') {
			para += "&personName=" + $("#personName").val();
		}
		if ($("#personId").val() != '') {
			para += "&personId=" + $("#personId").val();
		}
		window.open("/bk/info/auditInfo/${mid}" + para, "_self");
	});
	
});
</script>
<%@ include file="/bk/bottom.jsp" %>
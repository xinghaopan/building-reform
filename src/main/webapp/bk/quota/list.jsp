<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/bk/top.jsp" %>
<!-- Content -->
<div id="content">
	<ul class="breadcrumb">
		<li><img src="/images/photo_02.jpg"  alt=""/>&nbsp;您当前的位置：</li>
	    <li class="center-navigation">
	    </li>
	</ul>
	
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
			           		<c:if test="${fn:length(user.departmentId) == 2}">
				           		<a href="#modal-simple" data-toggle="modal" url="/bk/quota/edit/${mid}?id=0" class="btn btn-icon btn-info glyphicons circle_ok action-edit"><i></i>新&nbsp;&nbsp;&nbsp;&nbsp;建</a>
				           	</c:if>
				           	<c:if test="${userDepartment.isWork == 1 && fn:length(user.departmentId) != 10}">
				           		<a href="/bk/quota/distribute/${mid}" class="btn btn-icon btn-info glyphicons circle_ok"><i></i>指标发放</a>
				           	</c:if>
			           	</div>
					</div>
	           </div>
	         
				<div class="row-fluid">
					<table class="table table-bordered table-condensed table-striped table-primary table-vertical-center checkboxs">
					<thead>
						<tr>
							<th style="width: 1%;" class="uniformjs"><input type="checkbox" /></th>
							<th class="center">机构</th>
							<th class="center">年度</th>
							<th class="center">指标数量</th>
							<th class="center">剩余数量</th>
							<th class="center">发放日期</th>
							<th class="center" style="width: 120px;">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="squota">
							<!-- Item -->
							<tr class="selectable">
								<td class="center uniformjs"><input type="checkbox" /></td>
								<td class="center">${squota.departmentName}</td>
								<td class="center">${squota.year}</td>
								<td class="center">${squota.num}</td>
								<td class="center">${squota.restNum}</td>
								<td class="center"><fmt:formatDate value="${squota.date}" pattern="yyyy-MM-dd" type="date" dateStyle="long" /></td>
								<td class="center">
									<c:if test="${squota.distributeDepartmentId == user.departmentId}">
										<a href="#modal-simple" data-toggle="modal" url="/bk/quota/edit/${mid}?id=${squota.id}" class="btn-action glyphicons pencil btn-success action-edit"><i></i></a>
									
										<a href="javascript:void(0);" url="/bk/quota/del/${mid}?id=${squota.id}" bname="${squota.departmentName}" class="btn-action glyphicons remove_2 btn-danger action-del"><i></i></a>
									</c:if>
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
					else if (msg == -1) {
	            		alert("指标已分配无法删除");
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
	            	else if (msg == -3) {
	            		alert("本机构的年度指标不存在，无法发放！！！");
		        	}
	            	else if (msg == -4) {
	            		alert("修改后本机构指标不够发放下级机构！！！");
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
        $("#quotaForm").ajaxSubmit(options); 
	});
	
	$('.btn_Search').click(function(){
		var para = "?year=" + $('#year').val();
		window.open("/bk/quota/list/${mid}" + para, "_self");
	});
});
</script>
<%@ include file="/bk/bottom.jsp" %>
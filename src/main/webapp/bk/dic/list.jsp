<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
						</div>
						
			           	<div class=" pull-right">
				           	<a href="#modal-simple" data-toggle="modal" url="/bk/dic/edit/${mid}" class="btn btn-icon btn-info glyphicons circle_ok action-edit"><i></i>新&nbsp;&nbsp;&nbsp;&nbsp;建</a>
			           	</div>
					</div>
	           </div>
	         
				<div class="row-fluid">
					<table class="table table-bordered table-condensed table-striped table-primary table-vertical-center checkboxs">
					<thead>
						<tr>
							<th style="width: 1%;" class="uniformjs"><input type="checkbox" /></th>
							<th class="center">健值名称</th>
							<th class="center">字典名称</th>
							<th class="center">字典值</th>
							<th class="center" style="width: 120px;">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="sdic">
							<!-- Item -->
							<tr class="selectable">
								<td class="center uniformjs"><input type="checkbox" /></td>
								<td class="center">${sdic.keyName}</td>
								<td class="center">${sdic.name}</td>
								<td class="center">${sdic.value}</td>
								<td class="center">
									<a href="#modal-simple" data-toggle="modal" url="/bk/dic/edit/${mid}?id=${sdic.id}" class="btn-action glyphicons pencil btn-success action-edit"><i></i></a>
									<a href="javascript:void(0);" url="/bk/dic/del/${mid}?id=${sdic.id}" bname="${sdic.name}" class="btn-action glyphicons remove_2 btn-danger action-del"><i></i></a>
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
		if ($('#keyId').val() == 0) {
			alert("请选择健值！！！");
			$('#keyId').focus();
			return;
		}
		$("#keyName").val($("#keyId").find("option:selected").text());
		$("#keyValue").val($("#keyId").find("option:selected").attr("v"));
		
		if (isNull($('#name').val())) {
			alert("字典名称不能为空！！！");
			$('#name').focus();
			return;
		}
		
		if (!isUnsignedInteger($('#value').val())) {
			alert("字典值只能为数字！！！");
			$('#value').focus();
			
			return;
		}
		
		var options = { 
	            success : function(msg) {
	            	if (msg == "-999") {
		        		alert("999");
		        	}
	            	else if (msg == 1) {
	            		alert("字典信息保存成功！！！");
	            		window.location.reload();
	            	}
	            	else {
	            		alert("字典信息保存失败！！！");
	            	}
	            } 
        }; 
        $("#dicForm").ajaxSubmit(options); 
	});
});
</script>
<%@ include file="/bk/bottom.jsp" %>
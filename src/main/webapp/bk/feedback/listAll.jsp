<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
			           	</div>
					</div>
	           </div>
	         
				<div class="row-fluid">
					<table class="table table-bordered table-condensed table-striped table-primary table-vertical-center checkboxs">
					<thead>
						<tr>
							<th style="width: 1%;" class="uniformjs"><input type="checkbox" /></th>
							<th class="center">标题</th>
							<th class="center">内容</th>
							<th class="center">反馈用户</th>
							<th class="center">状态</th>
							<th class="center">反馈日期</th>
							<th class="center" style="width: 120px;">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="sfeedback">
							<!-- Item -->
							<tr class="selectable">
								<td class="center uniformjs"><input type="checkbox" /></td>
								<td class="center">${sfeedback.title}</td>
								<td class="center">${sfeedback.askContent}</td>
								<td class="center">${sfeedback.userName}</td>
								<td class="center">
									<c:choose>
										<c:when test="${sfeedback.state == 1}">已回复</c:when>
										<c:when test="${sfeedback.state == 0}">未回复</c:when>
									</c:choose>
								</td>
								<td class="center"><fmt:formatDate value="${sfeedback.date}" pattern="yyyy-MM-dd" type="date" dateStyle="long" /></td>
								<td class="center">
									<c:if test="${sfeedback.state == 0}">
										<a href="#modal-simple" data-toggle="modal" url="/bk/feedback/edit/${mid}?id=${sfeedback.id}" class="btn-action glyphicons pencil btn-success action-edit"><i></i></a>
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
		if (isNull($('#replyContent').val())) {
			alert("回复内容不能为空！！！");
			$('#replyContent').focus();
			return;
		}
		
		var options = { 
	            success : function(msg) {
	            	if (msg == "-999") {
		        		alert("999");
		        	}
	            	else if (msg == 1) {
	            		alert("反馈信息回复成功！！！");
	            		window.location.reload();
	            	}
	            	else {
	            		alert("反馈信息回复失败！！！");
	            	}
	            } 
        }; 
        $("#feedbackForm").ajaxSubmit(options); 
	});
});
</script>
<%@ include file="/bk/bottom.jsp" %>
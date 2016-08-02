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
				           	<a href="#modal-simple" data-toggle="modal" url="/bk/feedback/add/${mid}" class="btn btn-icon btn-info glyphicons circle_ok action-edit"><i></i>新&nbsp;&nbsp;&nbsp;&nbsp;建</a>
			           	</div>
					</div>
	           </div>
			</div>
		</div>
	    
		<div class="separator bottom"></div> 
		
		<c:forEach items="${list}" var="sfeedback">
			<div class="widget" data-toggle="collapse-widget">
				<div class="widget-head">
					<p class="heading">
						${sfeedback.title}&nbsp;&nbsp;
						<fmt:formatDate value="${sfeedback.date}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />&nbsp;&nbsp;-&nbsp;&nbsp;
						${sfeedback.userName}
					</p>
				</div>
		        
				<div class="widget-body">
		            <div class="row-fluid">内容：${sfeedback.askContent}</div>
		            
		            <div class="row-fluid separator">
						回复内容：${sfeedback.replyContent}
		            </div>
		             
		            <div class="row-fluid separator">
		             	回复人：${sfeedback.replyUserName}&nbsp;&nbsp;
		             	日期：<fmt:formatDate value="${sfeedback.replyDate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />
		            </div>
				</div>
			</div>
	    
	 		<div class="separator bottom"></div> 
 		</c:forEach>
 		
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
		if (isNull($('#title').val())) {
			alert("标题不能为空！！！");
			$('#title').focus();
			return;
		}
		
		if (isNull($('#askContent').val())) {
			alert("内容不能为空！！！");
			$('#askContent').focus();
			return;
		}
		
		var options = { 
	            success : function(msg) {
	            	if (msg == "-999") {
		        		alert("999");
		        	}
	            	else if (msg == 1) {
	            		alert("反馈信息保存成功！！！");
	            		window.location.reload();
	            	}
	            	else {
	            		alert("反馈信息保存失败！！！");
	            	}
	            } 
        }; 
        $("#feedbackForm").ajaxSubmit(options); 
	});
});
</script>
<%@ include file="/bk/bottom.jsp" %>
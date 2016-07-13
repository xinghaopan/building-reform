<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
						</div>
						
			           	<div class=" pull-right">
				           	<a href="#modal-simple" data-toggle="modal" url="/bk/user/edit/${mid}?id=0" class="btn btn-icon btn-info glyphicons circle_ok action-edit"><i></i>新&nbsp;&nbsp;&nbsp;&nbsp;建</a>
			           	</div>
					</div>
	           </div>
	         
				<div class="row-fluid">
					<table class="table table-bordered table-condensed table-striped table-primary table-vertical-center checkboxs">
					<thead>
						<tr>
							<th style="width: 1%;" class="uniformjs"><input type="checkbox" /></th>
							<th class="center">帐号</th>
							<th class="center">真实姓名</th>
							<th class="center">角色名称</th>
							<th class="center" style="width: 120px;">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="suser">
							<!-- Item -->
							<tr class="selectable">
								<td class="center uniformjs"><input type="checkbox" /></td>
								<td class="center">${suser.name}</td>
								<td class="center">${suser.trueName}</td>
								<td class="center">${suser.role.name}</td>
								<td class="center">
									<a href="javascript:void(0)" url="/bk/user/initPassword/${mid}?id=${suser.id}" bname="${suser.name}" class="btn-action glyphicons pencil btn-success action-initPassword" ><i></i></a>
									<a href="#modal-simple" data-toggle="modal" url="/bk/user/edit/${mid}?id=${suser.id}" class="btn-action glyphicons pencil btn-success action-edit"><i></i></a>
									<a href="javascript:void(0);" url="/bk/user/del/${mid}?id=${suser.id}" bname="${suser.name}" class="btn-action glyphicons remove_2 btn-danger action-del"><i></i></a>
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
	$('.action-initPassword').click(function(){
		if( confirm('您确定要重置【' + $(this).attr("bname") + '】密码吗？') ){
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
	            		alert('【' + $(this).attr("bname") + '】密码初始化成功！！！\n密码为：${password}');
					}
	            	else if (msg == -1) {
	            		alert('【' + $(this).attr("bname") + '】密码初始化失败！！！\n用户信息错误！！！');
					}
					else {
						alert('【' + $(this).attr("bname") + '】初始化密码失败！！！');
					}
				},
				error : function(XMLHttpRequest, error, errorThrown) {
					//alert("请求超时！！！");
				}
			});
		}
	});
	
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
		if (isNull($('#name').val())) {
			alert("帐号不能为空！！！");
			$('#name').focus();
			return;
		}
		
		if (isNull($('#trueName').val())) {
			alert("真实姓名不能为空！！！");
			$('#trueName').focus();
			return;
		}
		
		if ($('.role').val() == 0) {
			alert("请为用户选择角色！！！");
			return;
		}
		
		if (!isUnsignedInteger($('#order').val())) {
			alert("排序只能为数字！！！");
			$('#order').focus();
			return;
		}
		
		var options = { 
	            success : function(msg) {
	            	if (msg == "-999") {
		        		alert("999");
		        	}
	            	else if (msg == -1) {
	            		alert("帐号：[" + $('#name').val() + "]已存在，无法保存！！！");
		        	}
	            	else if (msg == 1) {
	            		if (id == 0) {
	            			alert("用户信息保存成功！！！\n默认密码为：${password}");
	            		}
	            		else {
	            			alert("用户信息保存成功！！！");
	            		}
	            		window.location.reload();
	            	}
	            	else {
	            		alert("用户信息保存失败！！！");
	            	}
	            } 
        }; 
        $("#userForm").ajaxSubmit(options); 
	});
});
</script>
<%@ include file="/bk/bottom.jsp" %>
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
			           	</div>
					</div>
	           </div>
	         
				<div class="row-fluid">
					<table class="table table-bordered table-condensed table-striped table-primary table-vertical-center checkboxs">
					<thead>
						<tr>
							<th style="width: 1%;" class="uniformjs"><input type="checkbox" /></th>
							<th class="center">计划年度</th>
							<th class="center">户主姓名</th>
							<th class="center">身份证号</th>
							<th class="center">民族</th>
							<th class="center">联系电话</th>
							<th class="center">机构</th>
							<th class="center" style="width: 120px;">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="sinfo">
							<!-- Item -->
							<tr class="selectable">
								<td class="center uniformjs"><input type="checkbox" /></td>
								<td class="center">${sinfo.planYear}</td>
								<td class="center">${sinfo.personName}</td>
								<td class="center">${sinfo.personId}</td>
								<td class="center">${sinfo.personNation}</td>
								<td class="center">${sinfo.personTel}</td>
								<td class="center">${sinfo.departmentName}</td>
								<td class="center">
									<a href="#modal-simple" data-toggle="modal" url="/bk/info/editAudit/${mid}?id=${sinfo.id}" bname="${sinfo.personName}" class="btn-action glyphicons eye_open btn-success action-edit"><i></i></a>
									
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
	$('.action-audit-pass').live("click", function() {
		if( confirm('您确定审核通过此上报信息吗？') ) {
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
	            		alert("上报信息错误！！！");
					}
					else if (msg == -2) {
	            		alert("上报信息状态错误！！！");
					}
					else if (msg == -3) {
	            		alert("上级机构不存在！！！");
					}
					else if (msg == -4) {
	            		alert("没有权限进行审核操作！！！");
					}
					else if (msg == 1) {
						alert("上报信息审核通过成功！！！");
	            		window.location.reload();
					}
					else {
						alert('审核失败！！！');
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
	
	$('.btn_Search').click(function(){
		var para = "?year=" + $('#year').val() + "&currentPage=" + $(this).attr("currentPage") + "&count=" + $('#count').val();
		window.open("/bk/info/waitAudit/${mid}" + para, "_self");
	});
});
</script>
<%@ include file="/bk/bottom.jsp" %>
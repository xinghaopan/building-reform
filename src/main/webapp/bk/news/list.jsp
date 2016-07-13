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
							<div class="input-append">
								<input type="text" id="search_title" name="search_title" value="${title}" placeholder="标题" >&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" id="search_content" name="search_content" value="${content}" placeholder="内容" >
								<button class="btn btn_Search" type="button" currentPage="0">搜索</button>
							</div>
           				</div>
						
			           	<div class=" pull-right">
				           	<a href="#modal-simple" data-toggle="modal" url="/bk/news/edit/${mid}?id=0" class="btn btn-icon btn-info glyphicons circle_ok action-edit"><i></i>新&nbsp;&nbsp;&nbsp;&nbsp;建</a>
			           	</div>
					</div>
	           </div>
	         
				<div class="row-fluid">
					<table class="table table-bordered table-condensed table-striped table-primary table-vertical-center checkboxs">
					<thead>
						<tr>
							<th style="width: 1%;" class="uniformjs"><input type="checkbox" /></th>
							<th class="center">文章标题</th>
							<th class="center">置顶</th>
							<th class="center">排序</th>
							<th class="center">发布日期</th>
							<th class="center" style="width: 120px;">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="snews">
							<!-- Item -->
							<tr class="selectable">
								<td class="center uniformjs"><input type="checkbox" /></td>
								<td class="center">${snews.title}</td>
								<td class="center"><c:if test="${snews.istop == 1}">置顶</c:if></td>
								<td class="center">${snews.order}</td>
								<td class="center"><fmt:formatDate value="${snews.date}" pattern="yyyy-MM-dd" type="date" dateStyle="long" /></td>
								<td class="center">
									<a href="#modal-simple" data-toggle="modal" url="/bk/news/edit/${mid}?id=${snews.id}" class="btn-action glyphicons pencil btn-success action-edit"><i></i></a>
									<a href="javascript:void(0);" url="/bk/news/del/${mid}?id=${snews.id}" bname="${snews.title}" class="btn-action glyphicons remove_2 btn-danger action-del"><i></i></a>
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
		if (isNull($('#title').val())) {
			alert("文章标题不能为空！！！");
			$('#title').focus();
			return;
		}
		
		if (!isUnsignedInteger($('#order').val())) {
			alert("排序只能为数字！！！");
			$('#order').focus();
			return;
		}
		
		if (isNull($('#date').val())) {
			alert("文章发布日期不能为空！！！");
			$('#date').focus();
			return;
		}
		
		$('#subTitle').val(editor.getContent());
		
		
		var options = { 
	            success : function(msg) {
	            	if (msg == "-999") {
		        		alert("999");
		        		//outLogin();
		        	}
	            	else if (msg == 1) {
	            		alert("文章信息保存成功！！！");
	            		window.location.reload();
	            	}
	            	else {
	            		alert("文章信息保存失败！！！");
	            	}
	            } 
        }; 
        $("#newsForm").ajaxSubmit(options);  
	});
	
	$('.btn_Search').click(function(){
		var para = "?currentPage=" + $(this).attr("currentPage") + "&count=" + $('#count').val();
		var title = $('#search_title').val();
		if (title != null && title != "") {
			para += "&search_title=" + title;
		}
		var content = $('#search_content').val();
		if (content != null && content != "") {
			para += "&search_content=" + content;
		}
		window.open("/bk/news/list/${mid}" + para, "_self");
	});
});
</script>

<%@ include file="/bk/bottom.jsp" %>
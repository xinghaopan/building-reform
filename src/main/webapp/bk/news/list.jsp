<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/bk/top.jsp" %>
<div class="mainFrame-center-navigation">
	<p></p>
	<span></span>
</div>
<div class="mainFrame-center-list">
	<div class="mainFrame-center-search">
		<p>标题:</p><span><input id="title" name="title" type="text" value="${title}" class="search_Input" /></span>
		<p>内容:</p><span><input id="content" name="content" type="text" value="${content}" class="search_Input" /></span>
		<em>
			<a class="btn_Search" href="javascript:void(0);" currentPage="0">查询</a>
			<a href="/bk/news/edit/${mid}?id=0">新建</a>
		</em>
	</div>
	
	<table border=0 cellSpacing=1 summary="" cellPadding=3 width=732 bgColor="#cccccc" align="center" style="font-size:12px;">
		<tr>
			<td height=30 align="center" bgcolor="#45c8dc" ><p class="grzx_27">文章标题</p></td>
			<td width="7%" align="center" bgcolor="#45c8dc"><p class="grzx_27">置顶</p></td>
			<td width="7%" align="center" bgcolor="#45c8dc"><p class="grzx_27">排序</p></td>
			<td width="7%" align="center" bgcolor="#45c8dc"><p class="grzx_27">审核</p></td>
			<td width="7%" align="center" bgcolor="#45c8dc"><p class="grzx_27">ip</p></td>
			<td width="10%" align="center" bgcolor="#45c8dc"><p class="grzx_27">发布日期</p></td>
			<td width="15%" align="center" bgcolor="#45c8dc"><p class="grzx_27">操作</p></td>
		</tr>
		<c:forEach items="${list}" var="snews">
			<tr>
				<td height="30" align="center" bgColor="#ffffff">
					<p>${snews.title}</p>
				</td>
				<td bgColor="#ffffff" align="center">
					<p>
						<c:if test="${snews.istop == 1}">
							置顶
						</c:if>
					</p>
				</td>
				<td bgColor="#ffffff" align="center">
					<p>${snews.order}</p>
				</td>
				<td bgColor="#ffffff" align="center">
					<p>
						<c:if test="${snews.audit == 1}">
							通过
						</c:if>
					</p>
				</td>
				<td bgColor="#ffffff" align="center">
					<p>
						<c:if test="${snews.ip == 1}">
							限制
						</c:if>
					</p>
				</td>
				<td bgColor="#ffffff" align="center">
					<p>
						<fmt:formatDate value="${snews.date}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />
					</p>
				</td>
				<td bgColor="#ffffff" align="center">
					<p>
						<c:if test="${snews.audit == 0}">
							<c:if test="${user.role.power.indexOf('-1') >= 0}">
								<a class="centerFrame-list-audit" href="javascript:void(0)" url="/bk/news/audit/${mid}" para="id=${snews.id}" bname="${snews.title}">审核</a>
							</c:if>
						</c:if>
						<a class="centerFrame-list-edit" href="/bk/news/edit/${mid}?id=${snews.id}">编辑</a> 
						<a class="centerFrame-list-del" href="javascript:void(0)" url="/bk/news/del/${mid}" para="id=${snews.id}" bname="${snews.title}">删除</a> 
					</p>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<div class="mainFrame-center-pages">
		<p>每页条数</p>
		<span><input id="count" name="count" type="text" value="${count}" class="page_count" /></span>
		${pages}
	</div>
</div>

<script type="text/javascript">
jQuery(document).ready(function($) {
	$('.centerFrame-list-audit').click(function(){
		if( confirm('您确定要审核通过【' + $(this).attr("bname") + '】吗？') ){
			var url = $(this).attr("url");
			var para = $(this).attr("para");
			$.ajax({
				type : "get",
				url : url,
				data : "radom=" + Math.random() + "&" + para,
				dataType : "text",
				success : function(msg) {
					if (msg == "") {
						outLogin();
		        	}
					else if (msg == 1) {
	            		location.reload();
					}
					else if (msg == -1) {
	            		alert("您没有权限执行审核操作！！！");
					}
					else {
						alert('【' + $(this).attr("bname") + '】审核失败！！！');
					}
				},
				error : function(XMLHttpRequest, error, errorThrown) {
					alert("请求超时！！！");
				}
			});
		}
	});
	
	$('.centerFrame-list-del').click(function(){
		if( confirm('您确定要删除【' + $(this).attr("bname") + '】吗？') ){
			var url = $(this).attr("url");
			var para = $(this).attr("para");
			$.ajax({
				type : "get",
				url : url,
				data : "radom=" + Math.random() + "&" + para,
				dataType : "text",
				success : function(msg) {
					if (msg == "") {
						outLogin();
		        	}
	            	else if (msg == 1) {
	            		location.reload();
					}
					else {
						alert('【' + $(this).attr("bname") + '】删除失败！！！');
					}
				},
				error : function(XMLHttpRequest, error, errorThrown) {
					alert("请求超时！！！");
				}
			});
		}
	});
	
	$('.btn_Search').click(function(){
		var para = "?currentPage=" + $(this).attr("currentPage") + "&count=" + $('#count').val();
		var title = $('#title').val();
		if (title != null && title != "") {
			para += "&title=" + title;
		}
		var content = $('#content').val();
		if (content != null && content != "") {
			para += "&content=" + content;
		}
		window.open("/bk/news/list/${mid}" + para, "_self");
	});
});
</script>
<%@ include file="/bk/bottom.jsp" %>
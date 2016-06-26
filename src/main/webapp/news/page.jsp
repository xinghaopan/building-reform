<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/top.jsp" %>
<div id="content">
	<div class="container">
		<div class="container_left">
			<div class="container_left_img">
				<ul>
					<li><a href="#" target="_blank"><img src="/images/index_06.jpg" width="240" height="79"  alt=""/></a></li>
					<li><a href="#" target="_blank"><img src="/images/index_07.jpg" width="240" height="79"  alt=""/></a></li>
					<li><a href="#" target="_blank"><img src="/images/index_08.jpg" width="240" height="79"  alt=""/></a></li>
					<li><a href="#" target="_blank"><img src="/images/index_09.jpg" width="240" height="79"  alt=""/></a></li>
				</ul>
			</div>
			
			<%@ include file="/ranking.jsp" %>
		</div>
	
		<div class="container_right">
			<div class="container_rightmain">
				<div class="container_rightmanu"><p>您的位置：<a href="/index">首页</a>&nbsp;>&nbsp;<a href="${menu.frontLink}/${mid}">${menu.frontName}</a></p></div>
				
				<div class="container_right_newtitle"><h2>${menu.frontName}</h2></div>
				
				<div class="container_right_newlayout">
					<div class="container_right_pagetitle"><h2>${news.title}</h2></div>
					<div class="container_right_pagetime">信息来源：${news.author} 更新时间：<fmt:formatDate value="${news.date}" pattern="yyyy-MM-dd" type="date" dateStyle="long" /></div>
					<div class="container_right_page">${news.content}</div>
				</div>
			</div>
		</div>
		
		<div style="clear:both"></div>
	</div>
</div>
<%@ include file="/bottom.jsp" %>
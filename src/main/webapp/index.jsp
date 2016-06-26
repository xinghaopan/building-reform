<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/top.jsp" %>

<div id="content">
	<div class="loginbar">
		<div class="loginbar_name"><input type="text" name="textfield" class="biao1" /></div>
		<div class="loginbar_password"><input type="text" name="textfield" class="biao1" /></div>
		<div class="loginbar_codes"><input type="text" name="textfield" class="biao2" /></div>
		<div class="loginbar_images"><img src="images/index_04.jpg" width="71" height="28"  alt=""/></div>
		<div class="loginbar_button"><a href="#"><img src="images/index_03.jpg" width="59" height="24"  alt=""/></a></div>
	</div>


	<div class="hot">
		<ul>
			<li style="float:left;"><a href="#" target="_blank"><img src="images/index_06.jpg" width="240" height="79"  alt=""/></a></li>
			<li style="float:left; margin-left:13px;"><a href="#" target="_blank"><img src="images/index_07.jpg" width="240" height="79"  alt=""/></a></li>
			<li style="float:left; margin-left:13px;"><a href="#" target="_blank"><img src="images/index_08.jpg" width="240" height="79"  alt=""/></a></li>
			<li style="float:right;"><a href="#" target="_blank"><img src="images/index_09.jpg" width="240" height="79"  alt=""/></a></li>
		</ul>
	</div>

	
	<div class="container">
		<div class="container_left"><%@ include file="/ranking.jsp" %></div>
		
		<div class="container_right">
			<div class="container_right_one">
				<div class="container_right_title">
					<a href="/news/list/17" target="_self">
						<img src="images/index_12.jpg" width="370" height="45"  alt=""/>
					</a>
				</div>
				
				<div class="container_right_list">
					<ul>
						<c:forEach items="${gzdt}" var="snews" varStatus="status">
							<li>
								<c:set var="dates">  
								    <fmt:formatDate value="${snews.date}" pattern="yyyy-MM-dd " type="date"/>  
								</c:set> 
								<c:choose>
									<c:when test="${fn:length(snews.title) > 16}">
										<p><a href="/news/page/${snews.menuId}?id=${snews.id}" target="_blank">${fn:substring(snews.title, 0, 16)}...</a></p>
									</c:when>
									<c:otherwise>
										<p><a href="/news/page/${snews.menuId}?id=${snews.id}" target="_blank">${snews.title}</a></p>
									</c:otherwise>
								</c:choose>
								<span><fmt:formatDate value="${snews.date}" pattern="yyyy-MM-dd" type="date"  dateStyle="long"/></span>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		
			<div class="container_right_two">
				<div class="container_right_title">
					<a href="/news/list/16" target="_selft">
						<img src="images/index_13.jpg" width="370" height="45"  alt=""/>
					</a>
				</div>
				
				<div class="container_right_list">
					<ul>
						<c:forEach items="${zcwj}" var="snews" varStatus="status">
							<li>
								<c:set var="dates">  
								    <fmt:formatDate value="${snews.date}" pattern="yyyy-MM-dd " type="date"/>  
								</c:set> 
								<c:choose>
									<c:when test="${fn:length(snews.title) > 16}">
										<p><a href="/news/page/${snews.menuId}?id=${snews.id}" target="_blank">${fn:substring(snews.title, 0, 16)}...</a></p>
									</c:when>
									<c:otherwise>
										<p><a href="/news/page/${snews.menuId}?id=${snews.id}" target="_blank">${snews.title}</a></p>
									</c:otherwise>
								</c:choose>
								<span><fmt:formatDate value="${snews.date}" pattern="yyyy-MM-dd" type="date"  dateStyle="long"/></span>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		
			<div class="container_right_three">
				<div class="container_right_title">
					<a href="/news/list/15" target="_self">
						<img src="images/index_14.jpg" width="370" height="45"  alt=""/>
					</a>
				</div>
				
				<div class="container_right_list">
					<ul>
						<c:forEach items="${xtgx}" var="snews" varStatus="status">
							<li>
								<c:set var="dates">  
								    <fmt:formatDate value="${snews.date}" pattern="yyyy-MM-dd " type="date"/>  
								</c:set> 
								<c:choose>
									<c:when test="${fn:length(snews.title) > 16}">
										<p><a href="/news/page/${snews.menuId}?id=${snews.id}" target="_blank">${fn:substring(snews.title, 0, 16)}...</a></p>
									</c:when>
									<c:otherwise>
										<p><a href="/news/page/${snews.menuId}?id=${snews.id}" target="_blank">${snews.title}</a></p>
									</c:otherwise>
								</c:choose>
								<span><fmt:formatDate value="${snews.date}" pattern="yyyy-MM-dd" type="date"  dateStyle="long"/></span>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		
			<div class="container_right_four">
				<div class="container_right_title">
					<a href="/news/list/14" target="_self">
						<img src="images/index_15.jpg" width="370" height="45"  alt=""/>
					</a>
				</div>
				
				<div class="container_right_list">
					<ul>
						<c:forEach items="${xzzq}" var="snews" varStatus="status">
							<li>
								<c:set var="dates">  
								    <fmt:formatDate value="${snews.date}" pattern="yyyy-MM-dd " type="date"/>  
								</c:set> 
								<c:choose>
									<c:when test="${fn:length(snews.title) > 16}">
										<p><a href="/news/page/${snews.menuId}?id=${snews.id}" target="_blank">${fn:substring(snews.title, 0, 16)}...</a></p>
									</c:when>
									<c:otherwise>
										<p><a href="/news/page/${snews.menuId}?id=${snews.id}" target="_blank">${snews.title}</a></p>
									</c:otherwise>
								</c:choose>
								<span><fmt:formatDate value="${snews.date}" pattern="yyyy-MM-dd" type="date"  dateStyle="long"/></span>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		
		<div style="clear:both"></div>
	</div>
</div>

<%@ include file="/bottom.jsp" %>



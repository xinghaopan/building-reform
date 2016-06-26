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
				<div class="container_rightmanu">
					<p>当前位置：<a href="/index" target="_self">首页</a>&nbsp;>&nbsp;
						<a href="${menu.frontLink}/${mid}">${menu.frontName}</a>
					</p>
				</div>
				
				<div class="container_right_newtitle"><h2>${menu.frontName}</h2></div>
				<div class="container_right_newlayout">
					<div class="container_right_newlist">
						<ul>
							<c:forEach items="${list}" var="snews" varStatus="status">
								<li>
									<p><a href="/news/page/${mid}?id=${snews.id}" target="_blank">
										<c:choose>
											<c:when test="${fn:length(snews.title) > 40}">
												${fn:substring(snews.title, 0, 40)}...
											</c:when>
											<c:otherwise>
												${snews.title}
											</c:otherwise>
										</c:choose>
									</a></p>
									<span><fmt:formatDate value="${snews.date}" pattern="yyyy-MM-dd" type="date" dateStyle="long" /></span>
								</li>
							</c:forEach>
						</ul>
					</div>
				<div class="manu">
				<!--分页开始-->
			<div class="page">
				<input type="text" id="count" name="count" value="${count}" style="display:none;" />
				${pages}
			</div>
	    	<!--分页结束-->
				</div>
				</div>
			</div>
		</div>
		
		<div style="clear:both"></div>
	</div>
</div>
<%@ include file="/bottom.jsp" %>
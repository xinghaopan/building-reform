<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
							<th class="center">机构</th>
							<th class="center">年度</th>
							<th class="center">指标数量</th>
							<th class="center">开工数量</th>
							<th class="center">竣工数量</th>
							<th class="center">验收数量</th>
							<th class="center">竣工完成度</th>
							<th class="center">验收完成度</th>
							<th class="center">发放日期</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="squota">
							<!-- Item -->
							<tr class="selectable">
								<td class="center uniformjs"><input type="checkbox" /></td>
								<td class="center">
									<c:choose>
										<c:when test="${fn:length(squota.id) == 10}">
											<a href="/bk/info/sublist/${mid}?year=${year}&fatherId=${squota.id}" target="_self">${squota.name}</a>
										</c:when>
										<c:otherwise>
											<a href="/bk/info/list/${mid}?year=${year}&fatherId=${squota.id}" target="_self">${squota.name}</a>
										</c:otherwise>
									</c:choose>
								</td>
								<td class="center">${squota.year}</td>
								<td class="center">${squota.num}</td>
								<td class="center">
									<a href="/bk/info/sublist/${mid}?year=${year}&fatherId=${squota.id}&state=1" target="_self">${squota.beginNum}</a>
								</td>
								<td class="center">
									<a href="/bk/info/sublist/${mid}?year=${year}&fatherId=${squota.id}&state=2" target="_self">${squota.endNum}</a>
								</td>
								<td class="center">
									<a href="/bk/info/sublist/${mid}?year=${year}&fatherId=${squota.id}&state=3" target="_self">${squota.acceptanceNum}</a>
								</td>
								<td class="center">
									<c:if test="${squota.num != null && squota.num != 0}">
										<fmt:formatNumber value="${(squota.endNum) * 100 / squota.num}" pattern="##.##" minFractionDigits="2" />%
									</c:if>
								</td>
								<td class="center">
									<c:if test="${squota.num != null && squota.num != 0}">
										<fmt:formatNumber value="${(squota.acceptanceNum) * 100 / squota.num}" pattern="##.##" minFractionDigits="2" />%
									</c:if>
								</td>
								<td class="center"><fmt:formatDate value="${squota.date}" pattern="yyyy-MM-dd" type="date" dateStyle="long" /></td>
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
	
	$('.btn_Search').click(function(){
		var para = "?year=" + $('#year').val();
		window.open("/bk/info/list/${mid}" + para, "_self");
	});
});
</script>
<%@ include file="/bk/bottom.jsp" %>
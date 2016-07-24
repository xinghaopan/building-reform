<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<form id="dicForm" method="post" name="dicForm" action="/bk/dic/save/${mid}" >
<!-- Modal heading -->
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h3>待审核农户信息</h3>
</div>
<!-- // Modal heading END -->

<!-- Modal body -->
<div class="modal-body">

	<div class="row-fluid">
		<table class="table table-bordered table-condensed table-striped table-primary table-vertical-center checkboxs" style="border-top:#dddddd 1px solid">
			<tbody>
				<tr class="selectable">
					<td class="center" width="130">户主姓名：</td>
					<td class="left">${info.personName}</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">性别：</td>
					<td class="left">
						<c:choose>
							<c:when test="${info.personSex == 1}">男</c:when>
							<c:when test="${info.personSex == 2}">女</c:when>
						</c:choose>
					</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">身份证号：</td>
					<td class="left">${info.personId}</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">民族：</td>
					<td class="left">${info.personNation}</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">家庭人数：</td>
					<td class="left">${info.personNum}</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">联系电话：</td>
					<td class="left">${info.personTel}</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">房屋年代：</td>
					<td class="left">${info.houseAge}</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">旧住房建筑面积：</td>
					<td class="left">${info.houseOldSize1}</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">旧住房结构类型：</td>
					<td class="left">${info.houseOldTypeName}</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">改造前厕所类型：</td>
					<td class="left">${info.toiletOldTypeName}</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">改造方式：</td>
					<td class="left">${info.rebuildModeName}</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">建设方式：</td>
					<td class="left">${info.buildModeName}</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">改造后厕所类型：</td>
					<td class="left">${info.toiletNewTypeName}</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">改造后房屋面积：</td>
					<td class="left">${info.houseNewSize1}</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">列入计划年份：</td>
					<td class="left">${info.planYear}</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">改造进度：</td>
					<td class="left">${info.rebuildRateName}</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">开工日期：</td>
					<td class="left"><fmt:formatDate value='${info.rebuildBeginDate}' pattern='yyyy-MM-dd' type='date' dateStyle='long' /></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">竣工日期：</td>
					<td class="left"><fmt:formatDate value='${info.rebuildEndDate}' pattern='yyyy-MM-dd' type='date' dateStyle='long' /></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">是否验收：</td>
					<td class="left">
						<c:choose>
							<c:when test="${info.isAcceptance == 0}">否</c:when>
							<c:when test="${info.isAcceptance == 1}">是</c:when>
						</c:choose>
					</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">享受补助资金类型：</td>
					<td class="left">${info.grantTypeName}</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">总投资：</td>
					<td class="left">${info.sumFund}</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">省级补助资金：</td>
					<td class="left">${info.grantProvinceFund}</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">县市区补助资金：</td>
					<td class="left">${info.grantCountiesFund}</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">农户自筹资金：</td>
					<td class="left">${info.personSelfFund}</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="row-fluid">
		<table class="table table-bordered table-condensed table-striped table-primary table-vertical-center checkboxs" style="border-top:#dddddd 1px solid">
			<thead>
                 <tr class="selectable">
					<td class="center">户主照片</td>
					<td class="center">改造前屋内照片</td>
					<td class="center">改造前屋外照片</td>
				</tr>
			</thead>
			<tbody>																						
				<tr class="selectable">						
					<td class="center">
						<c:if test="${info.personImage != null && info.personImage != ''}">
							<img src="${info.personImage}" width="200" height="150" class="img-rounded" alt=""/>
						</c:if>
					</td>
					<td class="center">
						<c:if test="${info.houseInOldImage != null && info.houseInOldImage != ''}">
							<img src="${info.houseInOldImage}" width="200" height="150" class="img-rounded" alt=""/>
						</c:if>
					</td>
					<td class="center">
						<c:if test="${info.houseOutOldImage != null && info.houseOutOldImage != ''}">
							<img src="${info.houseOutOldImage}" width="200" height="150" class="img-rounded" alt=""/>
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="row-fluid">
		<table class="table table-bordered table-condensed table-striped table-primary table-vertical-center checkboxs" style="border-top:#dddddd 1px solid">
			<thead>
                 <tr class="selectable">
					<td class="center">改造后屋内照片</td>
					<td class="center">改造后屋外照片</td>
					<td class="center">验收照片</td>
				</tr>
			</thead>
			<tbody>																						
				<tr class="selectable">						
					<td class="center">
						<c:if test="${info.houseInNewImage != null && info.houseInNewImage != ''}">
							<img src="${info.houseInNewImage}" width="200" height="150" class="img-rounded" alt=""/>
						</c:if>
					</td>
					<td class="center">
						<c:if test="${info.houseOutNewImage != null && info.houseOutNewImage != ''}">
							<img src="${info.houseOutNewImage}" width="200" height="150" class="img-rounded" alt=""/>
						</c:if>
					</td>
					<td class="center">
						<c:if test="${info.acceptanceImage != null && info.acceptanceImage != ''}">
							<img src="${info.acceptanceImage}" width="200" height="150" class="img-rounded" alt=""/>
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<!-- // Modal body END -->

<!-- Modal footer -->
<div class="modal-footer">
	<a href="javascript:void(0);" class="btn btn-default" data-dismiss="modal">关闭</a> 
	<a href="javascript:void(0);" url="/bk/info/submit/${mid}?id=${info.id}" class="btn btn-info action-audit-pass" >审核通过</a>
	<a href="javascript:void(0);" url="/bk/info/back/${mid}?id=${info.id}" class="btn btn-info action-audit-back" >审核退回</a>
</div>
<!-- // Modal footer END -->
</form>
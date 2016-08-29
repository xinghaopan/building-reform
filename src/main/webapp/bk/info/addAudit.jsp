<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
					<td class="center" width="130">乡（镇、街道）：</td>
					<td class="left">${info.departmentName}</td>
					<td class="left" width="200"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">村：</td>
					<td class="left">${info.sonDepartmentName}</td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">村民小组：</td>
					<td class="left">${info.personGroup}</td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">户主姓名：</td>
					<td class="left">${info.personName}</td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">性别：</td>
					<td class="left">
						<c:choose>
							<c:when test="${info.personSex == 1}">男</c:when>
							<c:when test="${info.personSex == 2}">女</c:when>
						</c:choose>
					</td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">民族：</td>
					<td class="left">${info.personNation}</td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">身份证号：</td>
					<td class="left">${info.personId}</td>
					<td class="left">
						<c:if test="${info.personImage != null && info.personImage != ''}">
							<img src="${info.personImage}" width="200" height="150" class="img-rounded" alt=""/>
						</c:if>
					</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">改厕房屋地址：</td>
					<td class="left">${info.personAddr}</td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">家庭人数：</td>
					<td class="left">${info.personNum}</td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">联系电话：</td>
					<td class="left">${info.personTel}</td>
					<td class="left"></td>
				</tr>
				<tr class="selectable">
					<td class="center" width="130">房屋年代：</td>
					<td class="left">${info.houseAge}</td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">住房结构类型：</td>
					<td class="left">${info.houseOldType}</td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">改厕类型：</td>
					<td class="left">${info.toiletTypeName}</td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">改造模式：</td>
					<td class="left">${info.rebuildModeName}</td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">房屋改造方式：</td>
					<td class="left">${info.buildModeName}</td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">改造企业名称：</td>
					<td class="left">${info.buildCompany}</td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">厕所改造后面积：</td>
					<td class="left">${info.houseNewSize1}</td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">计划改厕年份：</td>
					<td class="left">${info.planYear}</td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">开工日期：</td>
					<td class="left"><fmt:formatDate value='${info.rebuildBeginDate}' pattern='yyyy-MM-dd' type='date' dateStyle='long' /></td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">竣工日期：</td>
					<td class="left"><fmt:formatDate value='${info.rebuildEndDate}' pattern='yyyy-MM-dd' type='date' dateStyle='long' /></td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">验收时间：</td>
					<td class="left"><fmt:formatDate value='${info.acceptanceDate}' pattern='yyyy-MM-dd' type='date' dateStyle='long' /></td>
					<td class="left">
						<c:if test="${info.acceptanceImage != null && info.acceptanceImage != ''}">
							<img src="${info.acceptanceImage}" width="200" height="150" class="img-rounded" alt=""/>
						</c:if>
					</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">省资金发放时间：</td>
					<td class="left"><fmt:formatDate value='${info.fundSendDate}' pattern='yyyy-MM-dd' type='date' dateStyle='long' /></td>
					<td class="left">
						<c:if test="${info.fundSendImage != null && info.fundSendImage != ''}">
							<img src="${info.fundSendImage}" width="200" height="150" class="img-rounded" alt=""/>
						</c:if>
					</td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">享受补助资金类型：</td>
					<td class="left">${info.grantTypeName}</td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">总投资：</td>
					<td class="left">${info.sumFund}</td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">省级补助资金：</td>
					<td class="left">${info.grantProvinceFund}</td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">县市区补助资金：</td>
					<td class="left">${info.grantCountiesFund}</td>
					<td class="left"></td>
				</tr>
				
				<tr class="selectable">
					<td class="center" width="130">农户自筹资金：</td>
					<td class="left">${info.personSelfFund}</td>
					<td class="left"></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="row-fluid">
		<table class="table table-bordered table-condensed table-striped table-primary table-vertical-center checkboxs" style="border-top:#dddddd 1px solid">
			<thead>
                 <tr class="selectable">
					<td class="center">位置照片（改厕前）</td>
					<td class="center">施工中照片</td>
				</tr>
			</thead>
			<tbody>																						
				<tr class="selectable">						
					<td class="center">
						<c:if test="${info.houseOldImage != null && info.houseOldImage != ''}">
							<img src="${info.houseOldImage}" width="200" height="150" class="img-rounded" alt=""/>
						</c:if>
					</td>
					<td class="center">
						<c:if test="${info.houseBuildingImage != null && info.houseBuildingImage != ''}">
							<img src="${info.houseBuildingImage}" width="200" height="150" class="img-rounded" alt=""/>
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
					<td class="center">厕所室外照片（改厕后）</td>
					<td class="center">厕所室内照片（改厕后）</td>
				</tr>
			</thead>
			<tbody>																						
				<tr class="selectable">						
					<td class="center">
						<c:if test="${info.houseOutNewImage != null && info.houseOutNewImage != ''}">
							<img src="${info.houseOutNewImage}" width="200" height="150" class="img-rounded" alt=""/>
						</c:if>
					</td>
					<td class="center">
						<c:if test="${info.houseInNewImage != null && info.houseInNewImage != ''}">
							<img src="${info.houseInNewImage}" width="200" height="150" class="img-rounded" alt=""/>
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
					<td class="center">委托书照片</td>
				</tr>
			</thead>
			<tbody>																						
				<tr class="selectable">						
					<td class="center">
						<c:if test="${info.personDelegateImage != null && info.personDelegateImage != ''}">
							<img src="${info.personDelegateImage}" width="200" height="150" class="img-rounded" alt=""/>
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<form id="infoForm" method="post" name="infoForm" action="">
	<div class="row-fluid">
		<table class="table table-bordered table-condensed table-striped table-primary table-vertical-center checkboxs" style="border-top:#dddddd 1px solid">
			<tbody>
				<tr class="selectable">
					<td class="center" width="130">审核意见：</td>
					<td class="left">
						 <textarea id="auditInfo" name="auditInfo" rows="6" style="width:98%"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	</form>
</div>
<!-- // Modal body END -->

<!-- Modal footer -->
<div class="modal-footer">
	<a href="javascript:void(0);" class="btn btn-default" data-dismiss="modal">关闭</a> 
	<a href="javascript:void(0);" url="/bk/info/submit/${mid}?id=${info.id}" class="btn btn-info action-audit-pass" >审核通过</a>
	<a href="javascript:void(0);" url="/bk/info/back/${mid}?id=${info.id}" class="btn btn-info action-audit-back" >审核退回</a>
</div>
<!-- // Modal footer END -->

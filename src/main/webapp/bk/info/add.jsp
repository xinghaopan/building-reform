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
		
		<div class="separator bottom"></div>
	
	 	<form id="infoForm" method="post" name="infoForm" action="/bk/info/save/${mid}">
	 	<input id="id" name="id" value="${info.id}" type="hidden" />

		<div class="widget" data-toggle="collapse-widget">
			<div class="widget-head">
				<p class="heading glyphicons folder_open"><i></i>农户情况</p>
			</div>
			
			<div class="widget-body">
				<div class="row-fluid">
					<div>
						<div class="control-group span5">
							<label class="control-label span4" for="fatherDepartmentName">本级单位：</label>
							<div class="controls"><input class="span8" id="departmentName" name="departmentName" value="${user.departmentName}" type="text" /></div>
						</div>
						
						<div class="control-group span5">
							<label class="control-label span4" for="departmentName">村：</label>
							<div class="controls">
								<select id="sonDepartmentId" name="sonDepartmentId" class="span8">
									<option value="-1" selected>请选择</option>
									<c:forEach items="${sonDepartmentList}" var="ssonDepartment">
										<option value="${ssonDepartment.id}" <c:if test="${ssonDepartment.id == info.sonDepartmentId}">selected</c:if> >${ssonDepartment.name}</option>
									</c:forEach>
								</select>
								<input id="sonDepartmentName" name="sonDepartmentName" value="${fatherDepartment.sonDepartmentName}" type="hidden" />
							</div>
						</div>
					</div>
					
					<div>	
						<div class="control-group span5">
							<label class="control-label span4" for="personGroup">村民小组：</label>
							<div class="controls"><input class="span8"  id="personGroup" name="personGroup" value="${info.personGroup}" type="text" /></div>
						</div>
					
						<div class="control-group span5">
							<label class="control-label span4" for="personName">姓名：</label>
							<div class="controls"><input class="span8" id="personName" name="personName" value="${info.personName}" type="text" /></div>
						</div>
					</div>
					
					<div>	
						<div class="control-group span5">
							<label class="control-label span4" for="personSex">性别：</label>
							<div class="controls">
								<select id="personSex" name="personSex" class="span8">
									<option value="-1" selected>请选择</option>
									<option value="1" <c:if test="${info.personSex == 1}">selected</c:if>>男</option>
									<option value="2" <c:if test="${info.personSex == 2}">selected</c:if>>女</option>
								</select>
							</div>
						</div>
						
						<div class="control-group span5">
							<label class="control-label span4" for="personNation">民族：</label>
							<div class="controls"><input class="span8" id="personNation" name="personNation" value="${info.personNation}" type="text" /></div>
						</div>
					</div>
                    
                    <div>
						<div class="control-group span5">
							<label class="control-label span4" for="personId">身份证号：</label>
							<div class="controls"><input class="span8"  id="personId" name="personId" value="${info.personId}" type="text" /></div>
						</div>
						
						<div class="control-group span5">
							<label class="control-label span6" for="firstname">农户本人拿身份证照片：</label>
							<div class="input-append span6">
								<input id="personImage" name="personImage" type="hidden" value="${info.personImage}" />
								<input id="personImg" name="personImg" value="" type="file" style="display:none;">
	  							<button class="btn personImg" type="button">浏览</button>
						  	</div>
						</div>
					</div>
					
					<div>	
						<div class="control-group span5">
							<div class="row-fluid">
								<label class="control-label span4" for="personAddr">改厕房屋地址：</label>
								<div class="controls"><input class="span8"  id="personAddr" name="personAddr" value="${info.personAddr}" type="text" /></div>
							</div>
							
							<div class="row-fluid separator">
								<label class="control-label span4" for="personNum">家庭人数：</label>
								<div class="controls"><input class="span8" id="personNum" name="personNum" value="${info.personNum}" type="text" /></div>
							</div>
							
							<div class="row-fluid">
								<label class="control-label span4" for="personTel">联系电话：</label>
								<div class="controls"><input class="span8"  id="personTel" name="personTel" value="${info.personTel}" type="text" /></div>
							</div>
						</div>
						
						<div class="control-group span5">
							<label class="control-label span4" for="lastname"></label>
		                    <div class="input-append span8">
		                    	<c:choose>
		                    		<c:when test="${info.personImage != null && info.personImage != ''}">
		                    			<img id="personIg" name="personIg" src="${info.personImage}" class="img-polaroid img-sfz"  alt=""/>
		                    		</c:when>
		                    		<c:otherwise>
		                    			<img id="personIg" name="personIg" src="/images/nopic.png" class="img-polaroid img-sfz"  alt=""/>
		                    		</c:otherwise>
		                    	</c:choose>
							</div>  
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="separator bottom"></div>
		
	    <div class="widget" data-toggle="collapse-widget">
			<div class="widget-head">
				<p class="heading glyphicons book_open"><i></i>房屋情况</p>
			</div>
			
			<div class="widget-body">
				<div class="row-fluid">
					<div>
						<div class="control-group span5">
							<label class="control-label span4" for="houseAge">房屋年份：</label>
							<div class="controls"><input class="span8" id="houseAge" name="houseAge" value="${info.houseAge}" type="text" /></div>
						</div>

                        <div class="control-group span5">
							<label class="control-label span4" for="houseOldType">住房结构类型：</label>
							<div class="controls"><input class="span8" id="houseOldType" name="houseOldType" value="${info.houseOldType}" type="text" /></div>
						</div>
						
						<div class="control-group span5" style="display:none;">
							<label class="control-label span4" for="houseOldSize1">旧住房建筑面积：</label>
							<div class="controls"><input class="span8" id="houseOldSize1" name="houseOldSize1" value="${info.houseOldSize1}" type="text" /></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="separator bottom"></div>   
 
 		<div class="widget" data-toggle="collapse-widget">
			<div class="widget-head">
				<p class="heading glyphicons edit"><i></i>改造情况</p>
			</div>
			
			<div class="widget-body">
				<div class="row-fluid">
					<div>
						<div class="control-group span5">
							<label class="control-label span4" for="toiletType">改厕类型：</label>
							<div class="controls">
								<select id="toiletType" name="toiletType" class="span8">
									<option value="-1" selected>请选择</option>
									<c:forEach items="${dicList}" var="sdic">
										<c:if test="${sdic.keyValue == 'toiletType'}">
											<option value="${sdic.value}" <c:if test="${sdic.value == info.toiletType}">selected</c:if> >${sdic.name}</option>
										</c:if>
									</c:forEach>
								</select>
								
								<input id="toiletTypeName" name="toiletTypeName" value="${info.toiletTypeName}" type="hidden" />
							</div>
						</div>

						<div class="control-group span5">
							<label class="control-label span4" for="rebuildMode">改造模式：</label>
							<div class="controls">
								<select id="rebuildMode" name="rebuildMode" class="span8">
									<option value="-1" selected>请选择</option>
									<c:forEach items="${dicList}" var="sdic">
										<c:if test="${sdic.keyValue == 'rebuildMode'}">
											<option value="${sdic.value}" <c:if test="${sdic.value == info.rebuildMode}">selected</c:if> >${sdic.name}</option>
										</c:if>
									</c:forEach>
								</select>
								
								<input id="rebuildModeName" name="rebuildModeName" value="${info.rebuildModeName}" type="hidden" />
							</div>
						</div>
					</div>
                    
                    <div>
						<div class="control-group span5">
							<label class="control-label span4" for="username">房屋改造方式：</label>
							<div class="controls">
								<select id="buildMode" name="buildMode" class="span8">
									<option value="-1" selected>请选择</option>
									<c:forEach items="${dicList}" var="sdic">
										<c:if test="${sdic.keyValue == 'buildMode'}">
											<option value="${sdic.value}" <c:if test="${sdic.value == info.buildMode}">selected</c:if> >${sdic.name}</option>
										</c:if>
									</c:forEach>
								</select>
								
								<input id="buildModeName" name="buildModeName" value="${info.buildModeName}" type="hidden" />
							</div>
						</div>
					
						<div class="control-group span5">
							<label class="control-label span4" for="buildCompany">改造企业名称：</label>
							<div class="controls"><input class="span8" id="buildCompany" name="buildCompany" value="${info.buildCompany}" type="text" /></div>
						</div>
					</div>
                    
                    <div>
						<div class="control-group span5">
							<label class="control-label span4" for="houseNewSize1">厕所改造后面积：</label>
							<div class="controls"><input class="span8" id="houseNewSize1" name="houseNewSize1" value="${info.houseNewSize1}" type="text" /></div>
						</div>
					</div>
				</div>
			</div>
		</div>
    
		<div class="separator bottom"></div>   
 
 		<div class="widget" data-toggle="collapse-widget">
			<div class="widget-head">
				<p class="heading glyphicons edit"><i></i>进度情况</p>
			</div>
			
			<div class="widget-body">
				<div class="row-fluid">
					<div>
						<div class="control-group span10">
							<label class="control-label span2" for="planYear">计划改厕年份：</label>
							<div class="controls">
								<select id="planYear" name="planYear" class="span4">
									<option value="-1" selected>请选择</option>
									<c:forEach items="${dicList}" var="sdic">
										<c:if test="${sdic.keyValue == 'planYear'}">
											<option value="${sdic.value}" <c:if test="${sdic.value == info.planYear}">selected</c:if> >${sdic.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
                    
                    <div>
						<div class="control-group span5">
							<label class="control-label span4" for="rebuildBeginDate">开工日期：</label>
							<div class="controls span8">
								<div class="input-append">
									<input type="text" id="rebuildBeginDate" name="rebuildBeginDate" value="<fmt:formatDate value='${info.rebuildBeginDate}' pattern='yyyy-MM-dd' type='date' dateStyle='long' />" /><span class="add-on glyphicons calendar"><i></i></span>
								</div>
							</div>
						</div>
                        
                        <div class="control-group span5">
							<label class="control-label span4" for="rebuildEndDate">竣工日期：</label>
							<div class="controls span8">
								<div class="input-append">
									<input type="text" id="rebuildEndDate" name="rebuildEndDate" value="<fmt:formatDate value='${info.rebuildEndDate}' pattern='yyyy-MM-dd' type='date' dateStyle='long' />" /><span class="add-on glyphicons calendar"><i></i></span>
								</div>
							</div>
						</div>
					</div>
                    
                    <div>
						<div class="control-group span5">
							<label class="control-label span4" for="acceptanceDate">验收时间：</label>
							<div class="controls span8">
								<div class="input-append">
									<input type="text" id="acceptanceDate" name="acceptanceDate" value="<fmt:formatDate value='${info.acceptanceDate}' pattern='yyyy-MM-dd' type='date' dateStyle='long' />" /><span class="add-on glyphicons calendar"><i></i></span>
								</div>
							</div>
						</div>
						
						<div class="control-group span5">
							<label class="control-label span4" for="fundSendDate">省资金发放时间：</label>
							<div class="controls span8">
								<div class="input-append">
									<input id="fundSendDate" name="fundSendDate" value="<fmt:formatDate value='${info.fundSendDate}' pattern='yyyy-MM-dd' type='date' dateStyle='long' />" type="text" /><span class="add-on glyphicons calendar"><i></i></span>
								</div>
							</div>
						</div>
					</div>
               <script type="text/javascript">
               $(document).ready(function() {
            	   var date = new Date(),
            	    	now = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
            	   
					if ($('#rebuildBeginDate').val() == "") {
						$('#rebuildBeginDate').val(now);
						$('#rebuildBeginDate').daterangepicker({ singleDatePicker: true });
						$('#rebuildBeginDate').val("");
					} else {
						$('#rebuildBeginDate').daterangepicker({ singleDatePicker: true });
					}
                  	
					if ($('#rebuildEndDate').val() == "") {
						$('#rebuildEndDate').val(now);
						$('#rebuildEndDate').daterangepicker({ singleDatePicker: true });
						$('#rebuildEndDate').val("");
					} else {
						$('#rebuildEndDate').daterangepicker({ singleDatePicker: true });
					}
					
					if ($('#acceptanceDate').val() == "") {
						$('#acceptanceDate').val(now);
						$('#acceptanceDate').daterangepicker({ singleDatePicker: true });
						$('#acceptanceDate').val("");
					} else {
						$('#acceptanceDate').daterangepicker({ singleDatePicker: true });
					}
					
					if ($('#fundSendDate').val() == "") {
						$('#fundSendDate').val(now);
						$('#fundSendDate').daterangepicker({ singleDatePicker: true });
						$('#fundSendDate').val("");
					} else {
						$('#fundSendDate').daterangepicker({ singleDatePicker: true });
					}
               });
               </script>
                    
                    <div>	
						<div class="control-group span5">
							<label class="control-label span6" for="firstname">验收单照片：</label>
							<div class="input-append span6">
								<input id="acceptanceImage" name="acceptanceImage" type="hidden" value="${info.acceptanceImage}" />
								<input id="acceptanceImg" name="acceptanceImg" value="" type="file" style="display:none;">
	  							<button class="btn acceptanceImg" type="button">浏览</button>
						  	</div>
						</div>
						
						<div class="control-group span5">
							<label class="control-label span6" for="fundSendImage">补助资金签收单照片：</label>
							<div class="input-append span6">
								<input id="fundSendImage" name="fundSendImage" type="hidden" value="${info.fundSendImage}" />
								<input id="fundSendImg" name="fundSendImg" value="" type="file" style="display:none;">
	  							<button class="btn fundSendImg" type="button">浏览</button>
						  	</div>
						</div>
					</div>
                    
                    <div>
                    	<div class="control-group span5">
		                    <label class="control-label span4" for="lastname"></label>
		                    <div class="input-append span8">
								<c:choose>
		                    		<c:when test="${info.acceptanceImage != null && info.acceptanceImage != ''}">
		                    			<img id="acceptanceIg" name="acceptanceIg" src="${info.acceptanceImage}" class="img-polaroid img-sfz"  alt=""/>
		                    		</c:when>
		                    		<c:otherwise>
		                    			<img id="acceptanceIg" name="acceptanceIg" src="/images/nopic.png" class="img-polaroid img-sfz"  alt=""/>
		                    		</c:otherwise>
		                    	</c:choose> 
							</div>  
	                    </div>
                    	
                    	<div class="control-group span5">
		                    <label class="control-label span4" for="lastname"></label>
		                    <div class="input-append span8">
								<c:choose>
		                    		<c:when test="${info.fundSendImage != null && info.fundSendImage != ''}">
		                    			<img id="fundSendIg" name="fundSendIg" src="${info.fundSendImage}" class="img-polaroid img-sfz"  alt=""/>
		                    		</c:when>
		                    		<c:otherwise>
		                    			<img id="fundSendIg" name="fundSendIg" src="/images/nopic.png" class="img-polaroid img-sfz"  alt=""/>
		                    		</c:otherwise>
		                    	</c:choose> 
							</div>  
	                    </div>
					</div>
                    
                    <div>
						<div class="control-group span5" style="display:none;">
							<label class="control-label span4" for="isAcceptance">是否验收：</label>
							<div class="controls">
								<input class="span8" id="isAcceptance" name="isAcceptance" value="${info.isAcceptance}" type="text" />
							</div>
						</div>
					</div>
					
				</div>
			</div>
		</div>
    
		<div class="separator bottom"></div>
		
		<div class="widget" data-toggle="collapse-widget">
			<div class="widget-head">
				<p class="heading glyphicons edit"><i></i>资金情况</p>
			</div>
			
			<div class="widget-body">
	
				<div class="row-fluid">
					<div>
						<div class="control-group span5">
							<label class="control-label span4" for="grant">享受补助资金类型：</label>
							<div class="controls">
								<c:set var="splitChar" value="," />
								<c:forEach items="${dicList}" var="sdic">
									<c:if test="${sdic.keyValue == 'grantType'}">
										<c:set var="values" value="${splitChar}${sdic.value}${splitChar}"></c:set>
										<input id="grant" name="grant" class="span1" value="${sdic.value}" grantTypeName="${sdic.name}" <c:if test="${fn:contains(info.grantType, values)}">checked</c:if>  type="checkbox" />${sdic.name}
									</c:if>
								</c:forEach>
								<input id="grantType" name="grantType" value="${info.grantType}" type="hidden" />
								<input id="grantTypeName" name="grantTypeName" value="${info.grantTypeName}" type="hidden" />
							</div>
						</div>

						<div class="control-group span5">
							<label class="control-label span4" for="sumFund">总投资：</label>
							<div class="controls">
								<input class="span7" id="sumFund" name="sumFund" value="${info.sumFund}" type="text" />&nbsp;元
							</div>
						</div>
					</div>
                    
                    <div>
                    	<div class="control-group span5">
							<label class="control-label span4" for="grantProvinceFund">省级补助资金：</label>
							<div class="controls">
								<input class="span7" id="grantProvinceFund" name="grantProvinceFund" value="${info.grantProvinceFund}" type="text" />&nbsp;元
							</div>
						</div>
						
						<div class="control-group span5">
							<label class="control-label span4" for="grantCountiesFund">县市区补助资金：</label>
							<div class="controls">
								<input class="span7" id="grantCountiesFund" name="grantCountiesFund" value="${info.grantCountiesFund}" type="text" />&nbsp;元
							</div>
						</div>
					</div>
                    
                    <div>
						<div class="control-group span5">
							<label class="control-label span4" for="personSelfFund">农户自筹资金：</label>
							<div class="controls">
								<input class="span7" id="personSelfFund" name="personSelfFund" value="${info.personSelfFund}" type="text" />&nbsp;元
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
    
		<div class="separator bottom"></div>
		
		<div class="widget" data-toggle="collapse-widget" >
			<div class="widget-head">
				<p class="heading glyphicons bank"><i></i>改造照片</p>
			</div>
			<div class="widget-body">
				<div class="row-fluid">
					<div>
						<div class="control-group span5">
							<label class="control-label span6" for="houseOldImage">位置照片（改厕前）：</label>
							<div class="input-append span6">
	  							<input id="houseOldImage" name="houseOldImage" type="hidden" value="${info.houseOldImage}" />
								<input id="houseOldImg" name="houseOldImg" value="" type="file" style="display:none;">
	  							<button class="btn houseOldImg" type="button">浏览</button>
						  	</div>
						</div>
						
						<div class="control-group span5">
							<label class="control-label span6" for="houseBuildingImage">施工中照片：</label>
							<div class="input-append span6">
								<input id="houseBuildingImage" name="houseBuildingImage" type="hidden" value="${info.houseBuildingImage}" />
								<input id="houseBuildingImg" name="houseBuildingImg" value="" type="file" style="display:none;">
	  							<button class="btn houseBuildingImg" type="button">浏览</button>
						  	</div>
						</div>
					</div>
	                
	                <div>
                    	<div class="control-group span5">
		                    <label class="control-label span4" for="lastname"></label>
		                    <div class="input-append span8">
								<c:choose>
		                    		<c:when test="${info.houseOldImage != null && info.houseOldImage != ''}">
		                    			<img id="houseOldIg" name="houseOldIg" src="${info.houseOldImage}" class="img-polaroid img-sfz"  alt=""/>
		                    		</c:when>
		                    		<c:otherwise>
		                    			<img id="houseOldIg" name="houseOldIg" src="/images/nopic.png" class="img-polaroid img-sfz"  alt=""/>
		                    		</c:otherwise>
		                    	</c:choose>  
							</div>  
	                    </div>
                    	
                    	<div class="control-group span5">
		                    <label class="control-label span4" for="lastname"></label>
		                    <div class="input-append span8">
								<c:choose>
		                    		<c:when test="${info.houseBuildingImage != null && info.houseBuildingImage != ''}">
		                    			<img id="houseBuildingIg" name="houseBuildingIg" src="${info.houseBuildingImage}" class="img-polaroid img-sfz"  alt=""/>
		                    		</c:when>
		                    		<c:otherwise>
		                    			<img id="houseBuildingIg" name="houseBuildingIg" src="/images/nopic.png" class="img-polaroid img-sfz"  alt=""/>
		                    		</c:otherwise>
		                    	</c:choose>  
							</div>  
	                    </div>
					</div>
					
					<div>
						<div class="control-group span5">
							<label class="control-label span6" for="houseOutNewImage">厕所室外照片（改厕后）：</label>
							<div class="input-append span6">
								<input id="houseOutNewImage" name="houseOutNewImage" type="hidden" value="${info.houseOutNewImage}" />
								<input id="houseOutNewImg" name="houseOutNewImg" value="" type="file" style="display:none;">
	  							<button class="btn houseOutNewImg" type="button">浏览</button>
						  	</div>
						</div>
						
						<div class="control-group span5">
							<label class="control-label span6" for="houseInNewImage">厕所室内照片（改厕后）：</label>
							<div class="input-append span6">
								<input id="houseInNewImage" name="houseInNewImage" type="hidden" value="${info.houseInNewImage}" />
								<input id="houseInNewImg" name="houseInNewImg" value="" type="file" style="display:none;">
	  							<button class="btn houseInNewImg" type="button">浏览</button>
						  	</div>
						</div>
					</div>
					
					<div>
                    	<div class="control-group span5">
		                    <label class="control-label span4" for="lastname"></label>
		                    <div class="input-append span8">
								<c:choose>
		                    		<c:when test="${info.houseOutNewImage != null && info.houseOutNewImage != ''}">
		                    			<img id="houseOutNewIg" name="houseOutNewIg" src="${info.houseOutNewImage}" class="img-polaroid img-sfz"  alt=""/>
		                    		</c:when>
		                    		<c:otherwise>
		                    			<img id="houseOutNewIg" name="houseOutNewIg" src="/images/nopic.png" class="img-polaroid img-sfz"  alt=""/>
		                    		</c:otherwise>
		                    	</c:choose>  
							</div>  
	                    </div>
                    	
                    	<div class="control-group span5">
		                    <label class="control-label span4" for="lastname"></label>
		                    <div class="input-append span8">
								<c:choose>
		                    		<c:when test="${info.houseInNewImage != null && info.houseInNewImage != ''}">
		                    			<img id="houseInNewIg" name="houseInNewIg" src="${info.houseInNewImage}" class="img-polaroid img-sfz"  alt=""/>
		                    		</c:when>
		                    		<c:otherwise>
		                    			<img id="houseInNewIg" name="houseInNewIg" src="/images/nopic.png" class="img-polaroid img-sfz"  alt=""/>
		                    		</c:otherwise>
		                    	</c:choose>  
							</div>  
	                    </div>
					</div>
					
					<div>
						<label class="control-label span8" for="lastname"><font size="10" color="red">*农户手持姓名、联系电话</font></label>
					</div>
				</div>
			</div>
	           
		</div>
        
		<div class="separator bottom"></div>
		
		<div class="widget" data-toggle="collapse-widget">
			<div class="widget-head">
				<p class="heading glyphicons edit"><i></i>填报人</p>
			</div>
			
			<div class="widget-body">
	
				<div class="row-fluid">
					<div>
						<div class="control-group span5">
							<label class="control-label span4" for="fillUserName">姓名：</label>
							<div class="controls">
								<input class="span8" id="fillUserName" name="fillUserName" value="${info.fillUserName}" type="text" />
							</div>
						</div>

						<div class="control-group span5">
							<label class="control-label span4" for="fillUserTel">电话：</label>
							<div class="controls">
								<input class="span8" id="fillUserTel" name="fillUserTel" value="${info.fillUserTel}" type="text" />
							</div>
						</div>
					</div>
					
					<div>
						<div class="control-group span5">
							<label class="control-label span4" for="fillUserUnit">单位：</label>
							<div class="controls">
								<input class="span8" id="fillUserUnit" name="fillUserUnit" value="${info.fillUserUnit}" type="text" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<c:if test="${auditList != null}">
			<div class="widget" data-toggle="collapse-widget">
				<div class="widget-head">
					<p class="heading glyphicons edit"><i></i>审核信息</p>
				</div>
				
				<div class="widget-body">
					<c:forEach items="${auditList}" var="saudit">
						<div class="row-fluid">
							<div>
								<div class="control-group span4">
									<label class="control-label span4" for="auditState">审核结果：</label>
									<div class="controls">
										<c:choose>
											<c:when test="${saudit.state == 1}">
												<input class="span8" id="auditState" name="auditState" value="审核通过" type="text" />
											</c:when>
											<c:when test="${saudit.state == -1}">
												<input class="span8" id="auditState" name="auditState" value="审核退回" type="text" />
											</c:when>
										</c:choose>
									</div>
								</div>
								<div class="control-group span4">
									<label class="control-label span4" for="auditDepartmentName">审核单位：</label>
									<div class="controls">
										<input class="span8" id="auditDepartmentName" name="auditDepartmentName" value="${saudit.auditDepartmentName}" type="text" />
									</div>
								</div>
								<div class="control-group span4">
									<label class="control-label span4" for="auditDate">审核时间：</label>
									<div class="controls">
										<input class="span8" id="auditDate" name="auditDate" value="<fmt:formatDate value='${saudit.date}' pattern='yyyy-MM-dd' type='date' dateStyle='long' />" type="text" />
									</div>
								</div>
							</div>
							
							<div>
								<div class="control-group span8">
									<label class="control-label span2" for="auditDate">审核意见：</label>
									<div class="controls">
										<input class="span10" id="auditDate" name="auditDate" value="${saudit.content}" type="text" />
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</c:if>
		<!-- Modal footer -->
		<div class="modal-footer">
			<a href="javascript:void(0);" id="btn_Submit" name="btn_Submit" class="btn btn-info" >保存</a>
		</div>
		<!-- // Modal footer END -->
		
		</form>
	</div>	
		
</div>
<script type="text/javascript">
jQuery(document).ready(function($) {
	$("#btn_Submit").click(function() { 
		$("#btn_Submit").click = function() {return false};
		
		if ($('#sonDepartmentId').val() == -1) {
			alert("请选择村！！！");
			$('#sonDepartmentId').focus();
			return;
		}
		$("#sonDepartmentName").val($("#sonDepartmentId").find("option:selected").text());
		
		// 农户情况
		if (isNull($('#personGroup').val())) {
			alert("村民小组不能为空！！！");
			$('#personGroup').focus();
			return;
		}
		
		if (isNull($('#personName').val())) {
			alert("姓名不能为空！！！");
			$('#personName').focus();
			return;
		}
		
		if ($('#personSex').val() == -1) {
			alert("请选择性别！！！");
			$('#personSex').focus();
			return;
		}
		
		if (isNull($('#personNation').val())) {
			alert("民族不能为空！！！");
			$('#personNation').focus();
			return;
		}
		
		if (isNull($('#personId').val())) {
			alert("身份证号不能为空！！！");
			$('#personId').focus();
			if ($('#personId').val().length != 15 && $('#personId').val().length != 18) {
				alert("身份证号只能为15位或18位！！！");
				$('#personId').focus();
			}
			return;
		}
		
		if ($("#personImg").val() != "" && !validate_img(document.forms["infoForm"]["personImg"].files[0], "持证照片")) {
			return;
		}
		
		if (isNull($('#personAddr').val())) {
			alert("改厕房屋地址不能为空！！！");
			$('#personAddr').focus();
			return;
		}
		
		if (!isUnsignedInteger($('#personNum').val())) {
			alert("家庭人数只能是数字！！！");
			$('#personNum').focus();
			return;
		}
		
		if (isNull($('#personTel').val())) {
			alert("联系电话不能为空！！！");
			$('#personTel').focus();
			return;
		}
	
		
		// 房屋情况
		if (isNull($('#houseAge').val())) {
			alert("房屋年份不能为空！！！");
			$('#houseAge').focus();
			return;
		}
		
		if (isNull($('#houseOldType').val())) {
			alert("住房结构类型不能为空！！！");
			$('#houseOldType').focus();
			return;
		}

		
		// 改造情况
		if ($('#toiletType').val() == -1) {
			alert("请选择改厕类型！！！");
			$('#toiletType').focus();
			return;
		}
		$("#toiletTypeName").val($("#toiletType").find("option:selected").text());
		
		if ($('#rebuildMode').val() == -1) {
			alert("请选择改造模式！！！");
			$('#rebuildMode').focus();
			return;
		}
		$("#rebuildModeName").val($("#rebuildMode").find("option:selected").text());

		if ($('#buildMode').val() == -1) {
			alert("请选择房屋改造方式！！！");
			$('#buildMode').focus();
			return;
		}
		$("#buildModeName").val($("#buildMode").find("option:selected").text());

		if (isNull($('#buildCompany').val())) {
			alert("改造企业名称不能为空！！！");
			$('#buildCompany').focus();
			return;
		}
		
		if (!isUnsignedDouble($('#houseNewSize1').val())) {
			alert("厕所改造后面积只能是数字！！！");
			$('#houseNewSize1').focus();
			return;
		} 
		
		
		// 进度情况
		if ($('#planYear').val() == -1) {
			alert("请选择计划改厕年份！！！");
			$('#planYear').focus();
			return;
		}
		
		if (!isDate($('#rebuildBeginDate').val())) {
			alert("开工日期只能是日期类型！！！");
			$('#rebuildBeginDate').focus();
			return;
		}
		
		if (!isDate($('#rebuildEndDate').val())) {
			alert("竣工日期只能是日期类型！！！");
			$('#rebuildEndDate').focus();
			return;
		}
	
		if (!isDate($('#acceptanceDate').val())) {
			alert("验收时间只能是日期类型！！！");
			$('#acceptanceDate').focus();
			return;
		}
	
		if ($("#acceptanceImg").val() != "" && !validate_img(document.forms["infoForm"]["acceptanceImg"].files[0], "验收照片")) {
			return;
		}
		
		if (!isDate($('#fundSendDate').val())) {
			alert("省资金发放时间只能是日期类型！！！");
			$('#fundSendDate').focus();
			return;
		}
	
		if ($("#fundSendImg").val() != "" && !validate_img(document.forms["infoForm"]["fundSendImg"].files[0], "补助资金签收单照片")) {
			return;
		} 

		// 资金情况
		var grantType = "";
		var grantTypeName = "";
		$("input[name='grant']").each(function() {
			if($(this).get(0).checked) {
				grantType += $(this).val() + ",";
				if(grantTypeName == "") {
					grantTypeName = $(this).attr("grantTypeName");
				} else {
					grantTypeName += "," + $(this).attr("grantTypeName");
				}
			}
		});
		
		if (grantType == "") {
			alert("请选择享受补助资金类型！！！");
			$('#grant').focus();
			return;
		}
		grantType = "," + grantType;
		$("#grantType").val(grantType);
		$("#grantTypeName").val(grantTypeName);
		
		if (!isUnsignedDouble($('#sumFund').val())) {
			alert("总投资只能是数字！！！");
			$('#sumFund').focus();
			return;
		}
		
		if (!isUnsignedDouble($('#grantProvinceFund').val())) {
			alert("省级补助资金只能是数字！！！");
			$('#grantProvinceFund').focus();
			return;
		}
		
		if (!isUnsignedDouble($('#grantCountiesFund').val())) {
			alert("县市区补助资金只能是数字！！！");
			$('#grantCountiesFund').focus();
			return;
		}
		
		if (!isUnsignedDouble($('#personSelfFund').val())) {
			alert("农户自筹资金只能是数字！！！");
			$('#personSelfFund').focus();
			return;
		} 
		
		
		// 改造照片
		if ($("#houseOldImg").val() != "" && !validate_img(document.forms["infoForm"]["houseOldImg"].files[0], "位置照片（改厕前）")) {
			return;
		}
		
		if ($("#houseBuildingImg").val() != "" && !validate_img(document.forms["infoForm"]["houseBuildingImg"].files[0], "施工中照片")) {
			return;
		}
		
		if ($("#houseOutNewImg").val() != "" && !validate_img(document.forms["infoForm"]["houseOutNewImg"].files[0], "厕所室外照片（改厕后）")) {
			return;
		}
		
		if ($("#houseInNewImg").val() != "" && !validate_img(document.forms["infoForm"]["houseInNewImg"].files[0], "厕所室内照片（改厕后）")) {
			return;
		}
		
		
		// 填报人
		if (isNull($('#fillUserName').val())) {
			alert("填报人姓名不能为空！！！");
			$('#fillUserName').focus();
			return;
		}
		
		if (isNull($('#fillUserTel').val())) {
			alert("填报人电话不能为空！！！");
			$('#fillUserTel').focus();
			return;
		}
		
		if (isNull($('#fillUserUnit').val())) {
			alert("填报人单位不能为空！！！");
			$('#fillUserUnit').focus();
			return;
		}
	
		var options = { 
	            success : function(msg) {
	            	if (msg == "-999") {
		        		alert("999");
		        	}
	            	else if (msg == -1) {
	            		alert("没有剩余的指标进行上报！！！");
		        	}
	            	else if (msg == 1) {
	            		alert("农户信息保存成功！！！");
	            		window.location.reload();
	            	}
	            	else {
	            		alert("农户信息信息保存失败！！！");
	            	}
	            } 
        }; 
        $("#infoForm").ajaxSubmit(options); 
	});
	
	$('.personImg').click(function() {
		$("#personImg").click();
	});
	
	$("#personImg").on("change", function(event) {
		$("#personImage").val($(this).val());
		var objUrl = getObjectURL(this.files[0]) ;  
		if (objUrl) {
			$("#personIg").attr("src", objUrl) ;      
		} else {
			$("#personIg").attr("src", "/images/nopic.png") ; 
		}
	});
	
	$('.acceptanceImg').click(function() {
		$("#acceptanceImg").click();
	});
	
	$("#acceptanceImg").on("change", function() {
		$("#acceptanceImage").val($(this).val());
		var objUrl = getObjectURL(this.files[0]) ;  
		if (objUrl) {
			$("#acceptanceIg").attr("src", objUrl) ;      
		} else {
			$("#acceptanceIg").attr("src", "/images/nopic.png") ; 
		}
	});
	
	$('.fundSendImg').click(function() {
		$("#fundSendImg").click();
	});
	
	$("#fundSendImg").on("change", function() {
		$("#fundSendImage").val($(this).val());
		var objUrl = getObjectURL(this.files[0]) ;  
		if (objUrl) {
			$("#fundSendIg").attr("src", objUrl) ;      
		} else {
			$("#fundSendIg").attr("src", "/images/nopic.png") ; 
		}
	});
	
	$('.houseOldImg').click(function() {
		$("#houseOldImg").click();
	});
	
	$("#houseOldImg").on("change", function() {
		$("#houseOldImage").val($(this).val());
		var objUrl = getObjectURL(this.files[0]) ;  
		if (objUrl) {
			$("#houseOldIg").attr("src", objUrl) ;      
		} else {
			$("#houseOldIg").attr("src", "/images/nopic.png") ; 
		}
	});
	
	$('.houseBuildingImg').click(function() {
		$("#houseBuildingImg").click();
	});
	
	$("#houseBuildingImg").on("change", function() {
		$("#houseBuildingImage").val($(this).val());
		var objUrl = getObjectURL(this.files[0]) ;  
		if (objUrl) {
			$("#houseBuildingIg").attr("src", objUrl) ;      
		} else {
			$("#houseBuildingIg").attr("src", "/images/nopic.png") ; 
		}
	});
	
	$('.houseOutNewImg').click(function() {
		$("#houseOutNewImg").click();
	});
	
	$("#houseOutNewImg").on("change", function() {
		$("#houseOutNewImage").val($(this).val());
		var objUrl = getObjectURL(this.files[0]) ;  
		if (objUrl) {
			$("#houseOutNewIg").attr("src", objUrl) ;      
		} else {
			$("#houseOutNewIg").attr("src", "/images/nopic.png") ; 
		}
	});
	
	$('.houseInNewImg').click(function() {
		$("#houseInNewImg").click();
	});
	
	$("#houseInNewImg").on("change", function() {
		$("#houseInNewImage").val($(this).val());
		var objUrl = getObjectURL(this.files[0]) ;  
		if (objUrl) {
			$("#houseInNewIg").attr("src", objUrl) ;      
		}else {
			$("#houseInNewIg").attr("src", "/images/nopic.png") ; 
		}
	});
	
	function getObjectURL(file) {
		var url = null ;
		
		if (window.createObjectURL != undefined) { // basic
			url = window.createObjectURL(file) ;
		} else if (window.URL != undefined) { // mozilla(firefox)
		    url = window.URL.createObjectURL(file) ;
		} else if (window.webkitURL != undefined) { // webkit or chrome
		    url = window.webkitURL.createObjectURL(file) ;
		}
		
		return url ;
	}
});
</script>
<%@ include file="/bk/bottom.jsp" %>
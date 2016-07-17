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

		<div class="separator bottom"></div>
	
	 	<form id="infoForm" method="post" name="infoForm" action="/bk/info/save/${mid}">
	 	<input id="id" name="id" value="${info.id}" type="hidden" />
	 	<!-- Website Traffic Chart -->
		<div class="widget" data-toggle="collapse-widget">
			<div class="widget-head">
				<p class="heading glyphicons folder_open"><i></i>农户情况</p>
			</div>
			
			<div class="widget-body">
	           <div class="row-fluid">
					
					<!-- Column -->
					<div>
					
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="personName">户主姓名：</label>
							<div class="controls"><input class="span8" id="personName" name="personName" value="${info.personName}" type="text" /></div>
						</div>
						<!-- // Group END -->
						
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="personSex">性别：</label>
							<div class="controls">
								<select id="personSex" name="personSex" class="span8">
									<option value="0" selected>请选择</option>
									<option value="1" <c:if test="${info.personSex == 1}">selected</c:if>>男</option>
									<option value="2" <c:if test="${info.personSex == 2}">selected</c:if>>女</option>
								</select>
							</div>
						</div>
						<!-- // Group END -->
						
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="personId">身份证号：</label>
							<div class="controls"><input class="span8"  id="personId" name="personId" value="${info.personId}" type="text" /></div>
						</div>
                        
						<!-- // Group END -->
						
					</div>
                    
                    <div>
					
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="personNation">民族：</label>
							<div class="controls"><input class="span8" id="personNation" name="personNation" value="${info.personNation}" type="text" /></div>
						</div>
						<!-- // Group END -->
						
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="personNum">家庭人数：</label>
							<div class="controls"><input class="span8" id="personNum" name="personNum" value="${info.personNum}" type="text" /></div>
						</div>
						<!-- // Group END -->
						
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="personTel">联系电话：</label>
							<div class="controls"><input class="span8"  id="personTel" name="personTel" value="${info.personTel}" type="text" /></div>
						</div>
                        
						<!-- // Group END -->
						
					</div>
				</div>
			</div>
		</div>
		<!-- // Website Traffic Chart END -->
		
		<!-- Button Widget -->
		<div class="separator bottom"></div>
		<!-- // Button Widget END -->
		
		<!-- Google Vizualization DataTable Widget -->
    
	    <div class="widget" data-toggle="collapse-widget">
			<div class="widget-head">
				<p class="heading glyphicons book_open"><i></i>房屋情况</p>
			</div>
			<div class="widget-body">
				<div class="row-fluid">
				
					<!-- Column -->
					<div>
					
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="houseAge">房屋年代：</label>
							<div class="controls"><input class="span8" id="houseAge" name="houseAge" value="${info.houseAge}" type="text" /></div>
						</div>
						<!-- // Group END -->
						
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="houseOldSize1">旧住房建筑面积：</label>
							<div class="controls"><input class="span8" id="houseOldSize1" name="houseOldSize1" value="${info.houseOldSize1}" type="text" /></div>
						</div>
						<!-- // Group END -->
						
						<!-- Group -->
                        <div class="control-group span4">
							<label class="control-label span4" for="houseOldType">旧住房结构类型：</label>
							<div class="controls">
								<select id="houseOldType" name="houseOldType" class="span8">
									<option value="0" selected>请选择</option>
									<option value="1" <c:if test="${info.houseOldType == 1}">selected</c:if>>住房结构类型一</option>
									<option value="2" <c:if test="${info.houseOldType == 2}">selected</c:if>>住房结构类型二</option>
								</select>
								
								<input id="houseOldTypeName" name="houseOldTypeName" value="${info.houseOldTypeName}" type="hidden" />
							</div>
						</div>
						<!-- // Group END -->
						
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
				
					<!-- Column -->
					<div>
					
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="toiletOldType">改造前厕所类型：</label>
							<div class="controls">
								<select id="toiletOldType" name="toiletOldType" class="span8">
									<option value="0" selected>请选择</option>
									<option value="1" <c:if test="${info.toiletOldType == 1}">selected</c:if>>改造前厕所类型一</option>
									<option value="2" <c:if test="${info.toiletOldType == 2}">selected</c:if>>改造前厕所类型二</option>
								</select>
								
								<input id="toiletOldTypeName" name="toiletOldTypeName" value="${info.toiletOldTypeName}" type="hidden" />
							</div>
						</div>
						<!-- // Group END -->
						
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="rebuildMode">改造方式：</label>
							<div class="controls">
								<select id="rebuildMode" name="rebuildMode" class="span8">
									<option value="0" selected>请选择</option>
									<option value="1" <c:if test="${info.rebuildMode == 1}">selected</c:if>>改造方式一</option>
									<option value="2" <c:if test="${info.rebuildMode == 2}">selected</c:if>>改造方式二</option>
								</select>
								
								<input id="rebuildModeName" name="rebuildModeName" value="${info.rebuildModeName}" type="hidden" />
							</div>
						</div>
						<!-- // Group END -->
						
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="username">建设方式：</label>
							<div class="controls">
								<select id="buildMode" name="buildMode" class="span8">
									<option value="0" selected>请选择</option>
									<option value="1" <c:if test="${info.buildMode == 1}">selected</c:if>>建设方式一</option>
									<option value="2" <c:if test="${info.buildMode == 2}">selected</c:if>>建设方式二</option>
								</select>
								
								<input id="buildModeName" name="buildModeName" value="${info.buildModeName}" type="hidden" />
							</div>
						</div>
                        
						<!-- // Group END -->
						
					</div>
                    
                    <div>
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="toiletNewType">改造后厕所类型：</label>
							<div class="controls">
								<select id="toiletNewType" name="toiletNewType" class="span8">
									<option value="0" selected>请选择</option>
									<option value="1" <c:if test="${info.toiletNewType == 1}">selected</c:if>>改造后厕所类型一</option>
									<option value="2" <c:if test="${info.toiletNewType == 2}">selected</c:if>>改造后厕所类型二</option>
								</select>
								
								<input id="toiletNewTypeName" name="toiletNewTypeName" value="${info.toiletNewTypeName}" type="hidden" />
							</div>
						</div>
						<!-- // Group END -->
						
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="houseNewSize1">改造后房屋面积：</label>
							<div class="controls"><input class="span8" id="houseNewSize1" name="houseNewSize1" value="${info.houseNewSize1}" type="text" /></div>
						</div>
						<!-- // Group END -->
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
				
					<!-- Column -->
					<div>
					
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="planYear">列入计划年份：</label>
							<div class="controls">
								<select id="planYear" name="planYear" class="span8">
									<option value="0" selected>请选择</option>
									<option value="2016" <c:if test="${info.planYear == 2016}">selected</c:if>>2016</option>
									<option value="2015" <c:if test="${info.planYear == 2015}">selected</c:if>>2015</option>
									<option value="2014" <c:if test="${info.planYear == 2014}">selected</c:if>>2014</option>
								</select>
							</div>
						</div>
						<!-- // Group END -->
						
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="rebuildRate">改造进度：</label>
							<div class="controls">
								<select id="rebuildRate" name="rebuildRate" class="span8">
									<option value="0" selected>请选择</option>
									<option value="1" <c:if test="${info.rebuildRate == 1}">selected</c:if>>改造进度一</option>
									<option value="2" <c:if test="${info.rebuildRate == 2}">selected</c:if>>改造进度二</option>
								</select>
								
								<input id="rebuildRateName" name="rebuildRateName" value="${info.rebuildRateName}" type="hidden" />
							</div>
						</div>
						<!-- // Group END -->
						
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="rebuildBeginDate">开工日期：</label>
							<div class="controls">
								<input class="span8" id="rebuildBeginDate" name="rebuildBeginDate" value="<fmt:formatDate value='${info.rebuildBeginDate}' pattern='yyyy-MM-dd' type='date' dateStyle='long' />" type="text" />
							</div>
						</div>
                        
						<!-- // Group END -->
						
					</div>
                    
                    <div>
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="rebuildEndDate">竣工日期：</label>
							<div class="controls">
								<input class="span8" id="rebuildEndDate" name="rebuildEndDate" value="<fmt:formatDate value='${info.rebuildEndDate}' pattern='yyyy-MM-dd' type='date' dateStyle='long' />" type="text" />
							</div>
						</div>
						<!-- // Group END -->
						
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="isAcceptance">是否验收：</label>
							<div class="controls">
								<select id="isAcceptance" name="isAcceptance" class="span8">
									<option value="-1" selected>请选择</option>
									<option value="0" <c:if test="${info.isAcceptance == 0}">selected</c:if>>否</option>
									<option value="1" <c:if test="${info.isAcceptance == 1}">selected</c:if>>是</option>
								</select>
							</div>
						</div>
						<!-- // Group END -->
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
				
					<!-- Column -->
					<div>
					
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="grantType">享受补助资金类型：</label>
							<div class="controls">
								<select id="grantType" name="grantType" class="span8">
									<option value="0" selected>请选择</option>
									<option value="1" <c:if test="${info.grantType == 1}">selected</c:if>>享受补助资金类型一</option>
									<option value="2" <c:if test="${info.grantType == 2}">selected</c:if>>享受补助资金类型二</option>
								</select>
								
								<input id="grantTypeName" name="grantTypeName" value="${info.grantTypeName}" type="hidden" />
							</div>
						</div>
						<!-- // Group END -->
						
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="sumFund">总投资：</label>
							<div class="controls">
								<input class="span8" id="sumFund" name="sumFund" value="${info.sumFund}" type="text" />
							</div>
						</div>
						<!-- // Group END --> 
						
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="grantProvinceFund">省级补助资金：</label>
							<div class="controls">
								<input class="span8" id="grantProvinceFund" name="grantProvinceFund" value="${info.grantProvinceFund}" type="text" />
							</div>
						</div>
						<!-- // Group END -->
					</div>
                    
                    <div>
                    	
						
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="grantCountiesFund">县市区补助资金：</label>
							<div class="controls">
								<input class="span8" id="grantCountiesFund" name="grantCountiesFund" value="${info.grantCountiesFund}" type="text" />
							</div>
						</div>
						<!-- // Group END -->
						
						<!-- Group -->
						<div class="control-group span4">
							<label class="control-label span4" for="personSelfFund">农户自筹资金：</label>
							<div class="controls">
								<input class="span8" id="personSelfFund" name="personSelfFund" value="${info.personSelfFund}" type="text" />
							</div>
						</div>
						<!-- // Group END -->
					</div>
				</div>
			</div>
		</div>
    
		<div class="separator bottom"></div>
		
		<div class="widget" data-toggle="collapse-widget" style="display:none;">
			<div class="widget-head">
				<p class="heading glyphicons bank"><i></i>改造照片</p>
			</div>
			<div class="widget-body">
	
	           <div class="row-fluid">
				   
	               <div>
						
							<!-- Group -->
							<div class="control-group span4">
								<label class="control-label span4" for="firstname">户主照片：</label>
								<div class="input-append span8">
						    	  <input id="appendedInputButton" value="" type="text">
	  <button class="btn" type="button">浏览</button>
						  	</div>
						</div>
							
							<!-- // Group END -->
							
							<!-- Group -->
							<div class="control-group span4">
								<label class="control-label span4" for="lastname">改造前屋内照片：</label>
								<div class="input-append span8">
						    	  <input id="appendedInputButton" value="" type="text">
	  <button class="btn" type="button">浏览</button>
						  	</div>
						</div>
	                    
	                    	<!-- Group -->
							<div class="control-group span4">
								<label class="control-label span4" for="lastname">改造前屋外照片：</label>
								<div class="input-append span8">
						    	  <input id="appendedInputButton" value="" type="text">
	  <button class="btn" type="button">浏览</button>
						  	</div>
						</div>
							</div>
							<!-- // Group END -->
	                        
	                        
	                        
	                        
	                        <div>
						
							<!-- Group -->
							<div class="control-group span4">
								<label class="control-label span4" for="firstname">改造后屋内照片：</label>
								<div class="input-append span8">
	
	  <input id="appendedInputButton" value="" type="text">
	  <button class="btn" type="button">浏览</button>
	
						  	</div>
						</div>
							
							<!-- // Group END -->
							
							<!-- Group -->
							<div class="control-group span4">
								<label class="control-label span4" for="lastname">改造后屋外照片：</label>
								<div class="input-append span8">
						    	  <input id="appendedInputButton" value="" type="text">
	  <button class="btn" type="button">浏览</button>
						  	</div>
						</div>
	                    
	                    <!-- Group -->
							<div class="control-group span4">
								<label class="control-label span4" for="lastname">验收照片：</label>
								<div class="input-append span8">
						    	  <input id="appendedInputButton" value="" type="text">
	  <button class="btn" type="button">浏览</button>
						  	</div>
						</div>
	                    
							</div>
							<!-- // Group END -->
						</div>
				</div>
	           
			</div>
        
		
		<!-- Modal footer -->
		<div class="modal-footer">
			<a href="javascript:void(0);" id="btn_Submit" name="btn_Submit" class="btn btn-info" >提交</a>
		</div>
		<!-- // Modal footer END -->
		
		</form>
	</div>	
		
</div>
<script type="text/javascript">
jQuery(document).ready(function($) {
	$("#btn_Submit").live("click", function() { 
		
		if (isNull($('#personName').val())) {
			alert("户主姓名不能为空！！！");
			$('#personName').focus();
			return;
		}
		
		if ($('#personSex').val() == 0) {
			alert("请选择性别！！！");
			$('#personSex').focus();
			return;
		}
		
		if (isNull($('#personId').val())) {
			alert("身份证号不能为空！！！");
			$('#personId').focus();
			return;
		}
		
		if (isNull($('#personNation').val())) {
			alert("民族不能为空！！！");
			$('#personNation').focus();
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
	
		if (isNull($('#houseAge').val())) {
			alert("房屋年代不能为空！！！");
			$('#houseAge').focus();
			return;
		}
		
		if (!isUnsignedDouble($('#houseOldSize1').val())) {
			alert("旧住房建筑面积只能是数字！！！");
			$('#houseOldSize1').focus();
			return;
		}
		
		if ($('#houseOldType').val() == 0) {
			alert("请选择旧住房结构类型！！！");
			$('#houseOldType').focus();
			return;
		}
		$("#houseOldTypeName").val($("#houseOldType").find("option:selected").text());

		if ($('#toiletOldType').val() == 0) {
			alert("请选择改造前厕所类型！！！");
			$('#toiletOldType').focus();
			return;
		}
		$("#toiletOldTypeName").val($("#toiletOldType").find("option:selected").text());
		
		if ($('#rebuildMode').val() == 0) {
			alert("请选择改造方式！！！");
			$('#rebuildMode').focus();
			return;
		}
		$("#rebuildModeName").val($("#rebuildMode").find("option:selected").text());

		if ($('#buildMode').val() == 0) {
			alert("请选择建设方式！！！");
			$('#buildMode').focus();
			return;
		}
		$("#buildModeName").val($("#buildMode").find("option:selected").text());

		if ($('#toiletNewType').val() == 0) {
			alert("请选择改造后厕所类型！！！");
			$('#toiletNewType').focus();
			return;
		}
		$("#toiletNewTypeName").val($("#toiletNewType").find("option:selected").text());
		
		if (!isUnsignedDouble($('#houseNewSize1').val())) {
			alert("改造后房屋面积只能是数字！！！");
			$('#houseNewSize1').focus();
			return;
		} 
		
		if ($('#planYear').val() == 0) {
			alert("请选择列入计划年份！！！");
			$('#planYear').focus();
			return;
		}
		
		if ($('#rebuildRate').val() == 0) {
			alert("请选择改造进度！！！");
			$('#rebuildRate').focus();
			return;
		}
		$("#rebuildRateName").val($("#rebuildRate").find("option:selected").text());
		
		if (!isDate($('#rebuildBeginDate').val())) {
			alert("开工日期只能是日期类型！！！");
			$('#rebuildBeginDate').focus();
			return;
		}
		
		if (!isDate($('#rebuildEndDate').val())) {
			alert("竣工日期只能是日期类型！！！");
			$('#rebuildBeginDate').focus();
			return;
		}
	
		if ($('#isAcceptance').val() == -1) {
			alert("请选择是否验收！！！");
			$('#isAcceptance').focus();
			return;
		}
		
		if ($('#grantType').val() == 0) {
			alert("请选择享受补助资金类型！！！");
			$('#grantType').focus();
			return;
		}
		$("#grantTypeName").val($("#grantType").find("option:selected").text());
		
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
		var options = { 
	            success : function(msg) {
	            	if (msg == "-999") {
		        		alert("999");
		        	}
	            	else if (msg == -1) {
	            		alert("修改后的年度指标不符合规则！！！");
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
	
	$('.btn_Search').click(function(){
		var para = "?year=" + $('#year').val();
		window.open("/bk/info/list/${mid}" + para, "_self");
	});
});
</script>
<%@ include file="/bk/bottom.jsp" %>
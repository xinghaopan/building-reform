<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<div class="container_left_title"><h2>厕所完成率排行</h2></div>
	<div class="container_mytable" id="tab">
		<ul>
			<li><span>排行</span><em>地　区</em><span>上报率</span></li>
 				<c:forEach items="${statistic}" var="squota" varStatus="status">
				<li>
					<b>1</b>
					<p>${squota.departmentName}</p>
					<b><fmt:formatNumber value="${(squota.num - squota.restNum) * 100 / squota.num}" pattern="##"  />%</b>
				</li>
			</c:forEach>
		</ul>
	</div>
	
	<script type="text/javascript">
	<!--
	var Ptr=document.getElementById("tab").getElementsByTagName("li");
	function $() {
	      for (i=1;i<Ptr.length+1;i++) { 
	      Ptr[i-1].className = (i%2>0)?"t1":"t2"; 
	      }
	}
	window.onload=$;
	for(var i=0;i<Ptr.length;i++) {
	      Ptr[i].onmouseover=function(){
	      this.tmpClass=this.className;
	          
	      };
	      Ptr[i].onmouseout=function(){
	      this.className=this.tmpClass;
	      };
	}
	//-->
	</script>

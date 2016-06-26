<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/bk/top.jsp" %>
<form id="roleForm" method="post" name="roleForm" action="/bk/role/save/${mid}" enctype="multipart/form-data">
<div class="mainFrame-center-navigation">
	<p>${navigation}</p>
</div>
<div class="mainFrame-center-form">
	<ul>
		<li style="display:none"><p>角色ID：</p><span><input id="id" name="id" class="web_01" type="text" value="${role.id}"  /></span></li>
		<li><p>角色名称：</p><span><input id="name" name="name" class="web_01" type="text" value="${role.name}"  /></span></li>
		<li style="display:none"><p>角色功能：</p><span><input id="power" name="power" class="web_01" type="text" value="${role.power}"  /></span></li>
	</ul>
	
</div>
</form>

<div class="mainFrame-center-form-editor">
    <div class="mainFrame-center-form-editor-title"></div> 
    <div class="mainFrame-center-form-editor-content">
        <div id="tree"></div>
    </div>
</div>
<div class="mainFrame-center-form-editor">
    <div class="mainFrame-center-form-editor-title"></div> 
    <div class="mainFrame-center-form-editor-content">
        <img src="/images/back_13.jpg" class="web_02" id="btn_Submit" name="btn_Submit" />
        <img src="/images/back_44.jpg" class="web_02" id="btn_Back" name="btn_Back" />
    </div>
</div>
<script>
	var aMenus = new Array();
	
	function pushMenus(id, fatherId, name, types) {
		var current = new Object();
	    current.id = id;
	    current.fatherId = fatherId;
	    current.name = name;
	    current.types = types;
	    aMenus.push(current);
	}
</script>

<c:forEach items="${menuList}" var="smenu">
	<script>pushMenus('${smenu.id}', '${smenu.fatherId}', '${smenu.backName}', '0');</script>
</c:forEach>

<script>var power = '${role.power}';</script>

<script type="text/javascript">
var userAgent = window.navigator.userAgent.toLowerCase();
$.browser.msie8 = $.browser.msie && /msie 8\.0/i.test(userAgent);
$.browser.msie7 = $.browser.msie && /msie 7\.0/i.test(userAgent);
$.browser.msie6 = !$.browser.msie8 && !$.browser.msie7 && $.browser.msie && /msie 6\.0/i.test(userAgent);

function initData(list, fId) {
	var t = [];
	var len = 0;
	for (var i = 0; i < list.length; i ++) {
		// 添加节点
		if (list[i].fatherId == fId) {
			var isPower = true;
			if (list[i].types == "1") {
				isPower = false;
			}
			
			var isSel = 1;
			
			if (power.indexOf("," + list[i].id + ",") == -1) {
				isSel = 0;
			}
			
			var root = {
			    "id" : list[i].id,
			    "text" : list[i].name,
			    "value" : list[i].id,
			    "showcheck" : true,
			    complete : true,
			    "isexpand" : true,
			    "checkstate" : isSel,
			    "hasChildren" : isPower
			  };
			
			root["ChildNodes"] = initData(list, list[i].id);
			
			t[len] = root;
			len ++;
		}
	}
	
	return t;
}

function load() {        
    var o = { showcheck: true
    //onnodeclick:function(item){alert(item.text);},        
    };
    o.data = initData(aMenus, 0);                  
    $("#tree").treeview(o);            
    $("#showchecked").click(function(e){
        var s=$("#tree").getCheckedNodes();
        if(s !=null)
        alert(s.join(","));
        else
        alert("NULL");
    });
     $("#showcurrent").click(function(e){
        var s=$("#tree").getCurrentNode();
        if(s !=null)
            alert(s.text);
        else
            alert("NULL");
     });
}   

if( $.browser.msie6)
{
    load();
}
else{
    $(document).ready(load);
}

$(document).ready(function () {
	// ajaxSubmit
	$("#btn_Submit").click(function () {
		if (isNull($('#name').val())) {
			alert("角色名称不能为空！！！");
			$('#name').focus();
			return;
		}
		
		var nodes = $("#tree").getCheckedNodes();
		if (nodes == null) {
			$("#power").val("");
		}
		else {
			var s = nodes.join(",");
			s = "," + s + ",";
			$("#power").val(s);
		}
		
		var options = { 
	            success : function(msg) {
	            	if (msg == "-999") {
		        		alert("999");
		        		//outLogin();
		        	}
	            	else if (msg == 1) {
	            		alert("角色信息保存成功！！！");
	            		window.open("/bk/role/list/${mid}", "_self");
	            	}
	            	else {
	            		alert("角色信息保存失败！！！");
	            	}
	            } 
        }; 
        $("#roleForm").ajaxSubmit(options); 
	});
	
	$('#btn_Back').click(function(){
		history.back();
	});
});
</script> 
<%@ include file="/bk/bottom.jsp" %>
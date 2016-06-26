<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cc.buildingReform.form.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>吉林省建设厅农户改造系统</title>
	<link href="/css/back.css" rel="stylesheet" type="text/css" />
	<link href="/css/popDivs.css" rel="stylesheet" type="text/css" />
	<script src="/js/jquery.min.js"></script>
	<script src="/js/jquery.form.js"></script>
	<script src="/js/jquery.tree.js"></script>
	<script src="/js/union.js"></script>
	<script src="/js/spark-md5.js" type="text/javascript"></script>
    <script src="/js/huploader.js" type="text/javascript" charset="gb2312"></script>
	<script src="/ueditor_/ueditor.config.js" type="text/javascript"></script>
    <script src="/ueditor_/ueditor.all.js" type="text/javascript"></script>
    <link href="/ueditor_/themes/iframe.css" rel="stylesheet" type="text/css" />
    <script src="/My97DatePicker/WdatePicker.js"></script>
    <link href="/css/tree.css" rel="stylesheet" type="text/css" />
    
    <style type="text/css">
        #inputContent{
            width: 900px;
        }
    </style>
</head>
<%
	User user = (User)request.getSession().getAttribute("loginUser"); 
%>
<body>
<script>
	var editor = null;
	var allMenus = new Array();
	var user = null;
</script>

<div class="topFrame">
    <div class="topFrame-line">
        <div class="topFrame-menu">
        	<ul>
			<li><span></span><p><a class='topFrame-menu-link' href='javascript:void(0);'>123</a></p></li>
			</ul>
        </div>
        <div class="grzx_01">
			<p><a href="javascript:void(0);" class="topFrame-logout">退 出</a></p><span><img src="/images/back_04.jpg" /></span>
			<p><a href="/bk/user/updatePassword/57" >修改密码</a></p><span><img src="/images/back_03.jpg" /></span>
			<p><a href="javascript:void(0)" class='tts'>首 页</a></p><span><img src="/images/back_02.jpg" /></span>
			<em class="myName">您好，<%=user.getTrueName() %></em>
		</div>
    </div>
</div>

<script type="text/javascript">
var url = "";
var ids = "";
var nav = "";
jQuery(document).ready(function($) {
	var tIndex = 0;
	var mid = '${mid}';
	
	if (mid == null || mid == '') {
		mid = '0';
	}
	
	$.ajax({
		type : "get",
		url : "/bk/menu/listByJSon",
		data : "radom=" + Math.random() + "&mid=" + mid,
		dataType : "text",
		success : function(msg) {
			 var obj = JSON.parse(msg);
			 if (obj.menu != null) {
			 	tIndex = obj.menu.id;
			 }
			allMenus = obj.list;
			// 初始化 顶部菜单
			var topMenu = "";
			for (var i = 0; i < allMenus.length; i ++) {
				if (allMenus[i].type >= 1) {
					if (allMenus[i].fatherId == 0) {
						if (allMenus[i].backLink != null && allMenus[i].backLink != "") {
							topMenu += "<li><span></span><p><a class='topFrame-menu-link1' href='" + allMenus[i].backLink + "/" + allMenus[i].id + "' >" + allMenus[i].backName + "</a></p></li>";
						}
						else {
							topMenu += "<li><span></span><p><a class='topFrame-menu-link' href='javascript:void(0);' url='" + allMenus[i].id + "'>" + allMenus[i].backName + "</a></p></li>";
						}
						if (tIndex == 0) {
							tIndex = allMenus[i].id; 
						}
					}
				}
			}
			
			if (topMenu != null && topMenu != "") {
				topMenu = "<ul>" + topMenu + "</ul>";	
				
				$(".topFrame-menu").html(topMenu);
			}
			
			// 顶部菜单点击事件
			$('.topFrame-menu-link').click(function(){
				tIndex = $(this).attr("url");
				initLeftFrame();
			});
			
			
			initLeftFrame();
			
			initNavigation();
		},
		error : function(XMLHttpRequest, error, errorThrown) {
			alert("请求菜单信息超时！！！");
		}
	});
	
	
	
	$('.topFrame-logout').click(function(){
		var url = "/bk/user/logout";
		
		$.ajax({
			type : "get",
			url : url,
			data : "radom=" + Math.random(),
			dataType : "text",
			success : function(msg) {
				if (msg == 1) {
					alert('退出登录状态成功！！！');
					window.open("/bk/login.html", "_self");
				}
				else {
					alert('退出登录状态失败！！！');
				}
			},
			error : function(XMLHttpRequest, error, errorThrown) {
				alert(error);
				alert(errorThrown);
			}
		});
	});
	
	
	
	$('.topFrame-changePassword').click(function(){
		changeCenter($(this).attr("url"));
	});
	
	// 初始化 左侧菜单树
	function initLeftFrame() {
		var leftFrameTitle = "";
		var leftFrameMenu = "";
		for(var i = 0; i < allMenus.length; i ++) {
			if (allMenus[i].id == tIndex) {
				leftFrameTitle = allMenus[i].backName;
				if (allMenus[i].backLink != null && allMenus[i].backLink != "") {
					url = allMenus[i].backLink;
					ids = allMenus[i].id;
				}
			}
			
			if (allMenus[i].fatherId == tIndex) {
				var select = false;
				var thisMenu = "<div class='leftFrame-level2'><div class='lmtj'></div>";// + allMenus[i].backName  + "</div>";
				
							
				if (allMenus[i].backLink != "" && allMenus[i].backLink != "") {
					thisMenu += "<a class='leftFrame-menu-link' href='" + allMenus[i].backLink + "/" + allMenus[i].id + "' >" + allMenus[i].backName  + "</a>";
				}
				else {
					thisMenu += "<a class='leftFrame-menu-link' href='javascript:void(0);' url='' sid='s" + allMenus[i].id + "'>" + allMenus[i].backName + "</a>";
				}
				
				thisMenu += "</div>";
				
				var subMenus = "";
				for(var j = 0; j < allMenus.length; j ++) {
					if (allMenus[j].fatherId == allMenus[i].id) {
						if (url == "" && allMenus[j].backLink != null && allMenus[j].backLink != "") {
							url = allMenus[j].backLink;
							ids = allMenus[j].id;
						}
						
						var subMenu = "<li>";
						if (allMenus[j].backLink != "" && allMenus[j].backLink != "") {
							subMenu += "<a class='leftFrame-menu-link' href='" + allMenus[j].backLink + "/" + allMenus[j].id + "' >" + allMenus[j].backName  + "</a>";
						}
						else {
							subMenu += "<a class='leftFrame-menu-link' href='javascript:void(0);' url='' sid='s" + allMenus[j].id + "'>" + allMenus[j].backName + "</a>";
						}
						subMenu = subMenu + "</li>";
						subMenus += subMenu;
						
						if (allMenus[j].id == mid) {
							select = true;
						}
					}
					
				}
				
				if (subMenus != "") {
					subMenus = "<ul>" + subMenus + "</ul>";
				}
				
				if (select) {
					subMenus = "<div class='leftFrame-level3' style='display: block;'>" + subMenus + "</div>";
				}
				else {
					subMenus = "<div class='leftFrame-level3' >" + subMenus + "</div>";
				}
				
				leftFrameMenu += thisMenu + subMenus;
			}
		}
		
		leftFrameMenu = leftFrameTitle + leftFrameMenu;
		$(".leftFrame-level1").html(leftFrameMenu);
		
		$(".leftFrame-level2").click(function(){ 
			$(this).next("div").slideToggle("slow")  
			.siblings(".leftFrame-level3:visible").slideUp("slow");
		});
	}

	
	function initNavigation() {
		if (mid != 0) {
			var menu = getMenu(mid);
			while(menu.fatherId != 0) {
				if (nav == "") {
					nav = "<a class=\"mainFrame-first-a\" href=\""
							+ menu.backLink + "/" + menu.id + "\" >"
							+ menu.backName + "</a>";
				} else {
					nav = "<a class=\"mainFrame-first-a\" href=\""
						+ menu.backLink + "/" + menu.id + "\" >"
						+ menu.backName + "</a>&nbsp;&gt;&nbsp;" + nav;
				}
				menu = getMenu(menu.fatherId);
			}
			nav = "<a class=\"mainFrame-first-a\" href=\"javascript:void(0);\" >"
				+ menu.backName + "</a>&nbsp;&gt;&nbsp;" + nav;
			$(".mainFrame-center-navigation p").html(nav);
		}
	}
	
	// 获取功能信息 2015-08-27 by p
	function getMenu(id) {
		for (var i = 0; i < allMenus.length; i ++) {
			if (allMenus[i].id == id) {
				return allMenus[i];
				break;
			}
		}
		return null;
	}
	
});
</script>
<div class="centerFrame">
	<div class="left">
		<div class="leftFrame-level1">
			<div class="leftFrame-title">z</div>
			
		  	<div class="leftFrame-level2"><div class="jbsz"> </div>基本管理</div>
			      <div class="leftFrame-level3">
			<ul>
					<li> 网站配置</li>
					<li> 管理设置</li>
			        <li> 导航菜单</li>
			</ul>
			  </div>
		    	<div class="leftFrame-level2"><div class="xwzx"> </div>新闻中心</div>
			      <div class="leftFrame-level3">
			      <ul>
					<li> 管理文章</li>
					<li> 文章分类</li>
			        <li> 添加文章</li>
			        </ul>
			      </div>
		    	<div class="leftFrame-level2"><div class="zxcp"> </div>最新产品</div>
			      <div class="leftFrame-level3">
			      <ul>
					<li>图片管理</li>
					<li> 图片分类</li>
			         <li> 添加图片</li>
			         </ul>
			  		</div>
				<div class="leftFrame-level2"><div class="lmtj"> </div> 栏目添加</div>
				<div class="leftFrame-level3">
				<ul>
				<li> 文章系统</li>
				<li> 图片系统</li>
				      <li> 添加表单</li>
				       <li> 招聘系统</li>
				</ul>
				</div>
		</div>
	</div>

	<script type="text/javascript">
$(document).ready(function(){
		
	});
</script>
	<div class="mainFrame-center">
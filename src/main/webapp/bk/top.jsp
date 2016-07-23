<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cc.buildingReform.form.User"%>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="ie lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="ie lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="ie lt-ie9"> <![endif]-->
<!--[if gt IE 8]> <html class="ie gt-ie8"> <![endif]-->
<!--[if !IE]><!--><html><!-- <![endif]-->
<head>
	<title>吉林省农户厕所及污水改造管理系统</title>
	
	<!-- Meta -->
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
	
	<!-- Bootstrap -->
	<link href="/css/bootstrap.css" rel="stylesheet" />
	<link href="/css/responsive.css" rel="stylesheet" />
	
	<!-- Glyphicons Font Icons -->
	<link href="/css/glyphicons.css" rel="stylesheet" />
	
	<!-- Uniform Pretty Checkboxes 
	<link href="/css/uniform.default.css" rel="stylesheet" />
	-->

	
	<!-- Bootstrap Extended 
	<link href="/css/jasny-bootstrap.min.css" rel="stylesheet">
	<link href="/css/jasny-bootstrap-responsive.min.css" rel="stylesheet">
	<link href="/css/bootstrap-wysihtml5-0.0.2.css" rel="stylesheet">
	<link href="/css/bootstrap-select.css" rel="stylesheet" />
	<link href="/css/bootstrap-toggle-buttons.css" rel="stylesheet" />
	-->
	<!-- Select2 Plugin 
	<link href="/css/select2.css" rel="stylesheet" />
	-->
	<!-- DateTimePicker Plugin -->
	<link href="/css/datetimepicker.css" rel="stylesheet" />
	
	<!-- JQueryUI 
	<link href="/css/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" />
	-->
	<!-- MiniColors ColorPicker Plugin
	<link href="/css/jquery.miniColors.css" rel="stylesheet" />
	 -->
	<!-- Notyfy Notifications Plugin 
	<link href="/css/jquery.notyfy.css" rel="stylesheet" />
	<link href="/css/default.css" rel="stylesheet" />
	-->
	<!-- Gritter Notifications Plugin 
	<link href="/css/jquery.gritter.css" rel="stylesheet" />
	-->
	<!-- Easy-pie Plugin
	<link href="/css/jquery.easy-pie-chart.css" rel="stylesheet" />
	 -->

	<!-- Google Code Prettify Plugin 
	<link href="/css/google-code-prettify/prettify.css" rel="stylesheet" />
    -->
	<!-- Main Theme Stylesheet :: CSS -->
	<link href="/css/style-dark.css" rel="stylesheet" />

	<script src="/js/jquery.min.js"></script>
	<script src="/js/jquery.form.min.js"></script>
	<script src="/js/union.js"></script>
	<script src="/ueditor_/ueditor.config.js" type="text/javascript"></script>
    <script src="/ueditor_/ueditor.all.js" type="text/javascript"></script>
    <link href="/ueditor_/themes/iframe.css" rel="stylesheet" type="text/css" />
    <script src="/My97DatePicker/WdatePicker.js"></script>
    
    <link rel="stylesheet" href="/css/zTreeStyle/metro.css">
    <script src="/js/jquery.ztree.all.js"></script>
</head>
<%
	User user = (User)request.getSession().getAttribute("loginUser"); 
%>
<script>
	var editor = null;
	var allMenus = new Array();
	var user = null;
</script>
<body class="">
	
<!-- Main Container Fluid -->
<div class="container-fluid fluid menu-left">

<script type="text/javascript">
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
						topMenu += "<li class='hidden-phone'><a href='javascript:void(0);' menuId=" + allMenus[i].id + " class='topMenu glyphicons home'><i></i><span>" + allMenus[i].backName + "</span></a></li>";
							
						if (tIndex == 0) {
							tIndex = allMenus[i].id; 
						}
					}
				}
			}
			
			$(".tn1").html(topMenu);
			
			
			initLeftFrame();
			
			initNavigation();
		},
		error : function(XMLHttpRequest, error, errorThrown) {
			//alert("请求菜单信息超时！！！");
		}
	});
	
	function initLeftFrame() {
		var leftFrameMenu = "";
		for(var i = 0; i < allMenus.length; i ++) {
			var thisMenu = "";
			if (allMenus[i].fatherId == tIndex) {
				if (allMenus[i].id == mid) {
					thisMenu = "<li class='glyphicons display active'><a href='" + allMenus[i].backLink + "/" + allMenus[i].id + "'><i></i><span>" + allMenus[i].backName  + "</span></a></li>";
				} else {
					thisMenu = "<li class='glyphicons bin'><a href='" + allMenus[i].backLink + "/" + allMenus[i].id + "'><i></i><span>" + allMenus[i].backName  + "</span></a></li>";
				}
				leftFrameMenu += thisMenu;
			}
		}
		
		$(".leftMenu").html(leftFrameMenu);
		
	}
	
	function initNavigation() {
		
		if (mid != 0) {
			var menu = getMenu(mid);
			//$(".heading").html("<i></i>" + menu.backName);
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
				
			$(".center-navigation").html(nav);
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
	
	$(".topMenu").live("click", function() { 
		tIndex = $(this).attr("menuId");
		initLeftFrame(); 
	});
});
</script>
	<!-- Top navbar -->
	<div class="navbar main hidden-print">
	
		<!-- Brand -->
		<a href="javascript:void(0);" class="appbrand pull-left"><span>吉林省农户厕所及污水改造管理系统</span></a>
		
		<!-- Menu Toggle Button -->
		<button type="button" class="btn btn-navbar">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<!-- // Menu Toggle Button END -->
					
		<!-- Top Menu -->
		<ul class="topnav pull-left tn1"></ul>
		<!-- // Top Menu END -->
					
		<!-- Top Menu Right -->
		<ul class="topnav pull-right">
		
			<!-- Language menu -->
			<li class="hidden-phone" id="lang_nav" style="display:none;">
				<a href="#" data-toggle="dropdown" class="glyphicons leaf"><i></i>帮助</a>
		    	<ul class="dropdown-menu pull-left">
		      		
		    	</ul>
			</li>
			<!-- // Language menu END -->
		
			<!-- Profile / Logout menu -->
			<li class="account">
				<a data-toggle="dropdown" href="#" class="glyphicons logout user"><span class="hidden-phone text">用户</span><i></i></a>
				<ul class="dropdown-menu pull-right">
					<li><a href="#" class="glyphicons pushpin">修改密码<i></i></a></li>
					<li class="highlight profile">
						<span>
							<span class="heading">当前用户</span>
							<span class="details">
								admin
							</span>
							<span class="clearfix"></span>
						</span>
					</li>
					<li>
						<span>
							<a class="btn btn-default btn-mini pull-right" href="#">退出</a>
						</span>
					</li>
				</ul>
			</li>
			<!-- // Profile / Logout menu END -->
			
		</ul>
		<!-- // Top Menu Right END -->
		
	 </div>
	<!-- Top navbar END -->
	
	<!-- Sidebar menu & content wrapper -->
	<div id="wrapper">
	
	<!-- Sidebar Menu -->
	<div id="menu" class="hidden-phone hidden-print">
	
		<!-- Scrollable menu wrapper with Maximum height -->
		<div class="slim-scroll" data-scroll-height="">
		
		<!-- Sidebar Profile -->
		<span class="profile">
			<img class="img-circle img" src="/images/photo_01.png" width="60" height="60"  alt=""/>
			<p>
				<strong>您好，管理员！</strong>欢迎登陆！
			</p>
		</span>
		<!-- // Sidebar Profile END -->
		
		<ul class="leftMenu"></ul>
		
		<div class="clearfix"></div>
		
		<!-- Sidebar Stats Widgets -->
		<div class="separator bottom"></div>
		<!-- // Sidebar Stats Widgets END -->
					
					
					
		</div>
		<!-- // Scrollable Menu wrapper with Maximum Height END -->
		
	</div>
	<!-- // Sidebar Menu END -->
			
	<!-- Content -->
				
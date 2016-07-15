<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/top.jsp" %>

<div id="content">
	<div class="loginbar">
		<div class="loginbar_name"><input type="text" id="userName" name="userName" value="admin" class="biao1" /></div>
		<div class="loginbar_password"><input type="password" id="userPassword" name="userPassword" value="123456" class="biao1" /></div>
		<div class="loginbar_codes"><input type="text" id="codes" name="codes" maxlength="4" value="1234" class="biao2" /></div>
		<div class="loginbar_images"><img id="kaptchaImage" class="kaptchaImage" /></div>
		<div class="loginbar_button"><input id="btn_login" name="btn_login" type="image" class="web1" src="/images/index_03.jpg" /></div>
	</div>
<script>
    jQuery(document).ready(function ($) {
    	$('.kaptchaImage').click(function (event) {//生成验证码  
        	$('#kaptchaImage').hide().attr('src', '/kaptcha?' + Math.floor(Math.random()*100) ).fadeIn();    
        	event.stopPropagation();   
        });  
    	
    	$('#kaptchaImage').click();
    	
        $('#userName').focus();

        $('#userName').keypress(function (event) {
            var keycode = (event.keyCode ? event.keyCode : event.which);
            if (keycode == '13') {
                $('#userPassword').focus();
                event.stopPropagation();
            }
        });

        $('#userPassword').keypress(function (event) {
            var keycode = (event.keyCode ? event.keyCode : event.which);
            if (keycode == '13') {
                $('#codes').focus();
                event.stopPropagation();
            }
        });

        $('#codes').keypress(function (event) {
            var keycode = (event.keyCode ? event.keyCode : event.which);
            if (keycode == '13') {
                $('#btn_login').click();
                event.stopPropagation();
            }
        });

        $('#btn_login').click(function () {
        	if ($('#userName').val() == "") {
        		alert("用户名不能为空！！！");
        		$('#userName').focus();
        		return;
        	}
            
        	if ($('#userPassword').val() == "") {
        		alert("密码不能为空！！！");
        		$('#userPassword').focus();
        		return;
        	}
            
        	if ($('#codes').val() == "") {
        		alert("验证码不能为空！！！");
        		$('#codes').focus();
        		return;
        	}
            
        	$.ajax({
                type: "get",
                url: "/bk/user/login",
                data: "radom=" + Math.random() + "&name=" + $('#userName').val() + "&password=" + $('#userPassword').val() + "&codes=" + $('#codes').val(),
                dataType: "json",
                success: function (msg) {
                    if (msg == 0) {
                        alert("系统错误！！！");
                    }
                    else if (msg == -1) {
                        alert("用户密码错误！！！");
                    }
                    else if (msg == -2) {
                    	alert("用户密码错误！！！");
                    }
                    else if (msg == -3) {
                        alert("验证码错误！！！");
                    }
                    else if (msg == 1) {
                        window.open("/bk/main", "_self");
                    }
                    else {
                        alert("未知错误！！！");
                    }
                },
                error: function (XMLHttpRequest, error, errorThrown) {
                    alert(error);
                    alert(errorThrown);
                }
            });
        });
    });
</script>

	<div class="hot">
		<ul>
			<li style="float:left;"><a href="#" target="_blank"><img src="images/index_06.jpg" width="240" height="79"  alt=""/></a></li>
			<li style="float:left; margin-left:13px;"><a href="#" target="_blank"><img src="images/index_07.jpg" width="240" height="79"  alt=""/></a></li>
			<li style="float:left; margin-left:13px;"><a href="#" target="_blank"><img src="images/index_08.jpg" width="240" height="79"  alt=""/></a></li>
			<li style="float:right;"><a href="#" target="_blank"><img src="images/index_09.jpg" width="240" height="79"  alt=""/></a></li>
		</ul>
	</div>

	
	<div class="container">
		<div class="container_left"><%@ include file="/ranking.jsp" %></div>
		
		<div class="container_right">
			<div class="container_right_one">
				<div class="container_right_title">
					<a href="/news/list/17" target="_self">
						<img src="images/index_12.jpg" width="370" height="45"  alt=""/>
					</a>
				</div>
				
				<div class="container_right_list">
					<ul>
						<c:forEach items="${gzdt}" var="snews" varStatus="status">
							<li>
								<c:set var="dates">  
								    <fmt:formatDate value="${snews.date}" pattern="yyyy-MM-dd " type="date"/>  
								</c:set> 
								<c:choose>
									<c:when test="${fn:length(snews.title) > 16}">
										<p><a href="/news/page/${snews.menuId}?id=${snews.id}" target="_blank">${fn:substring(snews.title, 0, 16)}...</a></p>
									</c:when>
									<c:otherwise>
										<p><a href="/news/page/${snews.menuId}?id=${snews.id}" target="_blank">${snews.title}</a></p>
									</c:otherwise>
								</c:choose>
								<span><fmt:formatDate value="${snews.date}" pattern="yyyy-MM-dd" type="date"  dateStyle="long"/></span>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		
			<div class="container_right_two">
				<div class="container_right_title">
					<a href="/news/list/16" target="_selft">
						<img src="images/index_13.jpg" width="370" height="45"  alt=""/>
					</a>
				</div>
				
				<div class="container_right_list">
					<ul>
						<c:forEach items="${zcwj}" var="snews" varStatus="status">
							<li>
								<c:set var="dates">  
								    <fmt:formatDate value="${snews.date}" pattern="yyyy-MM-dd " type="date"/>  
								</c:set> 
								<c:choose>
									<c:when test="${fn:length(snews.title) > 16}">
										<p><a href="/news/page/${snews.menuId}?id=${snews.id}" target="_blank">${fn:substring(snews.title, 0, 16)}...</a></p>
									</c:when>
									<c:otherwise>
										<p><a href="/news/page/${snews.menuId}?id=${snews.id}" target="_blank">${snews.title}</a></p>
									</c:otherwise>
								</c:choose>
								<span><fmt:formatDate value="${snews.date}" pattern="yyyy-MM-dd" type="date"  dateStyle="long"/></span>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		
			<div class="container_right_three">
				<div class="container_right_title">
					<a href="/news/list/15" target="_self">
						<img src="images/index_14.jpg" width="370" height="45"  alt=""/>
					</a>
				</div>
				
				<div class="container_right_list">
					<ul>
						<c:forEach items="${xtgx}" var="snews" varStatus="status">
							<li>
								<c:set var="dates">  
								    <fmt:formatDate value="${snews.date}" pattern="yyyy-MM-dd " type="date"/>  
								</c:set> 
								<c:choose>
									<c:when test="${fn:length(snews.title) > 16}">
										<p><a href="/news/page/${snews.menuId}?id=${snews.id}" target="_blank">${fn:substring(snews.title, 0, 16)}...</a></p>
									</c:when>
									<c:otherwise>
										<p><a href="/news/page/${snews.menuId}?id=${snews.id}" target="_blank">${snews.title}</a></p>
									</c:otherwise>
								</c:choose>
								<span><fmt:formatDate value="${snews.date}" pattern="yyyy-MM-dd" type="date"  dateStyle="long"/></span>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		
			<div class="container_right_four">
				<div class="container_right_title">
					<a href="/news/list/14" target="_self">
						<img src="images/index_15.jpg" width="370" height="45"  alt=""/>
					</a>
				</div>
				
				<div class="container_right_list">
					<ul>
						<c:forEach items="${xzzq}" var="snews" varStatus="status">
							<li>
								<c:set var="dates">  
								    <fmt:formatDate value="${snews.date}" pattern="yyyy-MM-dd " type="date"/>  
								</c:set> 
								<c:choose>
									<c:when test="${fn:length(snews.title) > 16}">
										<p><a href="/news/page/${snews.menuId}?id=${snews.id}" target="_blank">${fn:substring(snews.title, 0, 16)}...</a></p>
									</c:when>
									<c:otherwise>
										<p><a href="/news/page/${snews.menuId}?id=${snews.id}" target="_blank">${snews.title}</a></p>
									</c:otherwise>
								</c:choose>
								<span><fmt:formatDate value="${snews.date}" pattern="yyyy-MM-dd" type="date"  dateStyle="long"/></span>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		
		<div style="clear:both"></div>
	</div>
</div>

<%@ include file="/bottom.jsp" %>



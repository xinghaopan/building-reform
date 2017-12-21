<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/bk/top.jsp" %>
<!-- Content -->
<div id="content">
	
	<div class="row-fluid row-merge"></div>

	<div class="innerLR">

		<div class="separator bottom"></div>
	 
	 	<div class="widget" data-toggle="collapse-widget">
			<div class="widget-head">
				<p class="heading glyphicons edit "></p>
			</div>
	         
			<div class="widget-body">
	           
				<div class="row-fluid">
					<div class="widget-titel">
						<div class="span7">
							<form id="sqlForm" method="post" name="sqlForm" action="/bk/admin/excuteSQL">
								<input type="text" id="sql" name="sql" value="" placeholder="语句" style="width: 500px;" >&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" id="state" name="state" value="0" placeholder="语句" style="display:none;" >
								<div class="input-append">
									<button class="btn btn_Excute" type="button" currentPage="0">运行</button>
									<button class="btn btn_Search" type="button" currentPage="0">查询</button>
								</div>
							</form>
           				</div>
						
					</div>
	           </div>
	         
				<div class="row-fluid">
					<table id="result" name="result" class="table table-bordered table-condensed table-striped table-primary table-vertical-center checkboxs">
					<tbody>

					</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>	
		
</div>
<script type="text/javascript">
jQuery(document).ready(function($) {
    $(".btn_Search").live("click", function() {
        if (isNull($('#sql').val())) {
            alert("sql语句不能为空！！！");
            $('#sql').focus();
            return;
        }

        $("#state").val("0");
        var options = {
            success : function(msg) {
                if (msg == "-1") {
                    alert("错误！！！");
                }
                else if (msg == -1) {
                    alert("错误！！！");
                }
                else {
                    var data = JSON.parse(msg);
                    var tb = $("#result");
                    tb.html("");
                    for(var o in data){
                        var arr = data[o].split("----");
                        var $tr = $("<tr></tr>");
                        for (var i = 0; i < arr.length; i ++) {
                            var $td = $("<td>" + arr[i] + "</td>");
                            $tr.append($td);
                        }
                        tb.append($tr);
                    }
                }
            }
        };
        $("#sqlForm").ajaxSubmit(options);
    });

    $(".btn_Excute").live("click", function() {
        if (isNull($('#sql').val())) {
            alert("sql语句不能为空！！！");
            $('#sql').focus();
            return;
        }

        $("#state").val("1");
        var options = {
            success : function(msg) {
                if (msg == "-1") {
                    alert("错误！！！");
                }
                else if (msg == -1) {
                    alert("错误！！！");
                }
                else if (msg == 1) {
                    alert("成功");
				}
                else {
                    alert("未知");
                }
            }
        };
        $("#sqlForm").ajaxSubmit(options);
    });
});
</script>

<%@ include file="/bk/bottom.jsp" %>
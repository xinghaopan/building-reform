<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	
	 	<form id="roleForm" method="post" name="roleForm" action="/bk/role/save/${mid}">
	 	<input id="id" name="id" value="${role.id}" type="hidden" />
	 	<input id="power" name="power" value="${role.power}" type="hidden" />
	 	<!-- Website Traffic Chart -->
		<div class="widget" data-toggle="collapse-widget">
			<div class="widget-head">
				<p class="heading glyphicons folder_open"><i></i>角色信息</p>
			</div>
			
			<div class="widget-body">
	           <div class="row-fluid">
	           		<div class="control-group span2"></div>
	           		
					<div class="control-group span8">
						<label class="control-label span2" for="name">角色名称：</label>
						<div class="controls"><input class="span6" id="name" name="name" value="${role.name}" type="text" /></div>
					</div>
				</div>
				
				<div class="row-fluid">
					<div class="control-group span2"></div>
					
					<div class="control-group span8">
						<label class="control-label span2" for="order">排序：</label>
						<div class="controls"><input class="span6"  id="order" name="order" value="${role.order}" type="text" /></div>
					</div>
				</div>
                   
               	<div class="row-fluid">
					<div class="control-group span2"></div>
					
					<div class="control-group span8">
						<label class="control-label span2" for="name">功能：</label>
						<div class="controls">
							<ul id="tree" class="ztree span6" style="width:560px; overflow:auto;"></ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- // Website Traffic Chart END -->
		
		<!-- Button Widget -->
		<div class="separator bottom"></div>
		<!-- // Button Widget END -->
		
		<!-- Modal footer -->
		<div class="modal-footer">
			<a href="javascript:void(0);" id="btn_Submit" name="btn_Submit" class="btn btn-info" >提交</a>
			<a href="javascript:void(0);" class="btn btn-default btn-close">关闭</a> 
		</div>
		<!-- // Modal footer END -->
		
		</form>
	</div>	
		
</div>
<script>
    var zTree;
    var demoIframe;

    function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#editBtn_"+treeNode.tId).length>0) return;
        var addStr = "<span class='button edit action-edit' href='#modal-simple' data-toggle='modal' url='/bk/department/edit/${mid}' fatherId='" + treeNode.pId + "' tid='" + treeNode.id + "' id='editBtn_" + treeNode.tId + "'></span>";
        addStr += "<span class='button remove' id='removeBtn_" + treeNode.tId + "' title='add node' onfocus='this.blur();'></span>";
        sObj.after(addStr);
    };

    function removeHoverDom(treeId, treeNode) {
        $("#removeBtn_"+treeNode.tId).unbind().remove();
        $("#editBtn_"+treeNode.tId).unbind().remove();
    };

    var setting = {
        check: {
            enable: true
        },
        view: {
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            dblClickExpand: false,
            showLine: true,
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: ""
            }
        },
        callback: {
            beforeClick: function(treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj("tree");
                if (treeNode.isParent) {
                    zTree.expandNode(treeNode);
                    return false;
                } else {
                    demoIframe.attr("src",treeNode.file + ".html");
                    return true;
                }
            }
        }
    };

    var zNodes = ${menuList};

    $(document).ready(function(){
        var t = $("#tree");
        t = $.fn.zTree.init(t, setting, zNodes);
        demoIframe = $("#testIframe");
        demoIframe.bind("load", loadReady);
        var zTree = $.fn.zTree.getZTreeObj("tree");
        zTree.selectNode(zTree.getNodeByParam("id", 101));

    });

    function loadReady() {
        var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
                htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
                maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
                h = demoIframe.height() >= maxH ? minH:maxH ;
        if (h < 530) h = 530;
        demoIframe.height(h);
    }
</script>
<script type="text/javascript">
jQuery(document).ready(function($) {
	$("#btn_Submit").live("click", function() { 
		
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getCheckedNodes(true);
		
		if (nodes == null) {
			$("#power").val("");
		}
		else {
			var s = ","
			for (var i = 0; i < nodes.length; i ++) {
				s += nodes[i].id + ",";
			}
			$("#power").val(s);
		}
		
		
		if (isNull($('#name').val())) {
			alert("角色名称！！！");
			$('#name').focus();
			return;
		}
		
		if (!isUnsignedInteger($('#order').val())) {
			alert("排序只能是数字！！！");
			$('#order').focus();
			return;
		}
		
		var options = { 
	            success : function(msg) {
	            	if (msg == "-999") {
		        		alert("999");
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
	
	$(".btn-close").live("click", function() { 
		window.open("/bk/role/list/${mid}", "_self");
	});
});
</script>
<%@ include file="/bk/bottom.jsp" %>
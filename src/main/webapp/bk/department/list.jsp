<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/bk/top.jsp" %>
<!-- Content -->
<div id="content">
	<div class="breadcrumb">
		<img src="/images/photo_02.jpg"  alt=""/>&nbsp;您当前的位置：
		<b class="center-navigation" style="font-weight:normal"></b>
	</div>

	<div class="row-fluid row-merge"></div>

	<div class="innerLR">

	    <!-- Modal -->
		<div class="modal hide fade" id="modal-simple"></div>
		<!-- // Modal END -->	
	
		<div class="separator bottom"></div>
	
	 
	 	<div class="widget" data-toggle="collapse-widget">
			<div class="widget-head">
				<p class="heading glyphicons edit "></p>
			</div>
	         
			<div class="widget-body">
	           
				<div class="row-fluid">
					<div class="widget-titel">
						<div class="span7">
						</div>
						
			           	<div class=" pull-right">
			           	</div>
					</div>
	           </div>
	         
				<div class="row-fluid">
					<ul id="tree" class="ztree" style="width:560px; overflow:auto;"></ul>
				</div>
			</div>
		</div>
	    
	 
		<div class="separator bottom"></div> 
	    
		<div class="widget widget-tabs">		
			<div class="widget-body">
	
			</div>
		</div>
		<!-- // Google Vizualization DataTable Widget END -->
		
	</div>	
		
</div>
<script>
    var zTree;
    var demoIframe;

    function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#editBtn_"+treeNode.tId).length>0) return;
        var addStr = "<span class='button add action-edit' href='#modal-simple' data-toggle='modal' url='/bk/department/edit/${mid}' fatherId='" + treeNode.id + "' tid='0' id='addBtn_" + treeNode.tId + "'></span>";
        addStr += "<span class='button edit action-edit' href='#modal-simple' data-toggle='modal' url='/bk/department/edit/${mid}' fatherId='" + treeNode.pId + "' tid='" + treeNode.id + "' id='editBtn_" + treeNode.tId + "'></span>";
        addStr += "<span class='button remove' id='removeBtn_" + treeNode.tId + "' title='add node' onfocus='this.blur();'></span>";
        sObj.after(addStr);
    };

    function removeHoverDom(treeId, treeNode) {
    	$("#addBtn_" + treeNode.tId).unbind().remove();
    	$("#removeBtn_" + treeNode.tId).unbind().remove();
        $("#editBtn_" + treeNode.tId).unbind().remove();
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

    var zNodes = ${list};

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
	$('.action-del').click(function(){
		if( confirm('您确定要删除【' + $(this).attr("bname") + '】吗？') ){
			var url = $(this).attr("url");
			$.ajax({
				type : "get",
				url : url,
				data : "radom=" + Math.random(),
				dataType : "text",
				success : function(msg) {
					if (msg == "-999") {
		        		outLogin();
		        	}
	            	else if (msg == 1) {
	            		window.location.reload();
					}
					else {
						alert('【' + $(this).attr("bname") + '】删除失败！！！');
					}
				},
				error : function(XMLHttpRequest, error, errorThrown) {
					//alert("请求超时！！！");
				}
			});
		}
	});
	
	$('.action-edit').live("click", function() { 
		var url = $(this).attr("url") + "?fatherId=" + $(this).attr("fatherId");
		if ($(this).attr("tid") != "0") {
			url += "&id=" + $(this).attr("tid");
		}
		
		$.ajax({
			type : "get",
			url : url,
			data : "radom=" + Math.random() ,
			dataType : "text",
			success : function(text) {
				$("#modal-simple").html(text);
			},
			error : function(XMLHttpRequest, error, errorThrown) {
				//alert("请求超时！！！");
			}
		});
	});
	
	$("#btn_Submit").live("click", function() { 
		if (isNull($('#id').val())) {
			alert("机构编码不能为空！！！");
			$('#id').focus();
			return;
		}
		
		if (isNull($('#name').val())) {
			alert("机构名称不能为空！！！");
			$('#name').focus();
			return;
		}
		
		if (!isUnsignedInteger($('#order').val())) {
			alert("排序只能为数字！！！");
			$('#order').focus();
			return;
		}
		
		var options = { 
	            success : function(msg) {
	            	if (msg == "-999") {
		        		alert("999");
		        	}
	            	else if (msg == 1) {
	            		alert("组织机构信息保存成功！！！");
	            		window.location.reload();
	            	}
	            	else {
	            		alert("组织机构信息保存失败！！！");
	            	}
	            } 
        }; 
        $("#departmentForm").ajaxSubmit(options); 
	});
});
</script>
<%@ include file="/bk/bottom.jsp" %>
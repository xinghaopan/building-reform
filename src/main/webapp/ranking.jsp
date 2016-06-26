<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

	<div class="container_left_title"><h2>上报量排行</h2></div>
	<div class="container_mytable" id="tab">
		<ul>
			<li><span>排行</span><em>地　区</em><span>上报量</span></li>
			<li><b>1</b><p>长白县</p><b>2015</b></li>
			<li><b>2</b><p>永吉县</p><b>2015</b></li>
			<li><b>3</b><p>伊通县</p><b>2015</b></li>
			<li><b>4</b><p>东辽县</p><b>2015</b></li>
			<li><b>5</b><p>双辽县</p><b>2015</b></li>
			<li><b>6</b><p>柳河县</p><b>2015</b></li>
			<li><b>7</b><p>前郭县</p><b>2015</b></li>
			<li><b>8</b><p>抚松县</p><b>2015</b></li>
			<li><b>9</b><p>东丰县</p><b>2015</b></li>
			<li><b>10</b><p>靖宇县</p><b>2015</b></li>
			<li><b>11</b><p>乾安县</p><b>2015</b></li>
			<li><b>12</b><p>扶余县</p><b>2015</b></li>
			<li><b>13</b><p>汪清县</p><b>2015</b></li>
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

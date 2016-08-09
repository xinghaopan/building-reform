var maskDiv = "#bg";
var popDiv = "#selectItem";
var currentObject = null;
var currentUrl = null;
var currentPara = null;

var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];    // 加权因子   
var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];            // 身份证验证位值.10代表X   
function IdCardValidate(idCard) { 
    idCard = trim(idCard.replace(/ /g, ""));               //去掉字符串头尾空格                     
    if (idCard.length == 15) {   
        return isValidityBrithBy15IdCard(idCard);       //进行15位身份证的验证    
    } else if (idCard.length == 18) {   
        var a_idCard = idCard.split("");                // 得到身份证数组   
        if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){   //进行18位身份证的基本验证和第18位的验证
            return true;   
        }else {   
            return false;   
        }   
    } else {   
        return false;   
    }   
}   
/**  
 * 判断身份证号码为18位时最后的验证位是否正确  
 * @param a_idCard 身份证号码数组  
 * @return  
 */  
function isTrueValidateCodeBy18IdCard(a_idCard) {   
    var sum = 0;                             // 声明加权求和变量   
    if (a_idCard[17].toLowerCase() == 'x') {   
        a_idCard[17] = 10;                    // 将最后位为x的验证码替换为10方便后续操作   
    }   
    for ( var i = 0; i < 17; i++) {   
        sum += Wi[i] * a_idCard[i];            // 加权求和   
    }   
    valCodePosition = sum % 11;                // 得到验证码所位置   
    if (a_idCard[17] == ValideCode[valCodePosition]) {   
        return true;   
    } else {   
        return false;   
    }   
}   
/**  
  * 验证18位数身份证号码中的生日是否是有效生日  
  * @param idCard 18位书身份证字符串  
  * @return  
  */  
function isValidityBrithBy18IdCard(idCard18){   
    var year =  idCard18.substring(6,10);   
    var month = idCard18.substring(10,12);   
    var day = idCard18.substring(12,14);   
    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
    // 这里用getFullYear()获取年份，避免千年虫问题   
    if(temp_date.getFullYear()!=parseFloat(year)   
          ||temp_date.getMonth()!=parseFloat(month)-1   
          ||temp_date.getDate()!=parseFloat(day)){   
            return false;   
    }else{   
        return true;   
    }   
}   
  /**  
   * 验证15位数身份证号码中的生日是否是有效生日  
   * @param idCard15 15位书身份证字符串  
   * @return  
   */  
  function isValidityBrithBy15IdCard(idCard15){   
      var year =  idCard15.substring(6,8);   
      var month = idCard15.substring(8,10);   
      var day = idCard15.substring(10,12);   
      var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
      // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
      if(temp_date.getYear()!=parseFloat(year)   
              ||temp_date.getMonth()!=parseFloat(month)-1   
              ||temp_date.getDate()!=parseFloat(day)){   
                return false;   
        }else{   
            return true;   
        }   
  }   
//去掉字符串头尾空格   
function trim(str) {   
    return str.replace(/(^\s*)|(\s*$)/g, "");   
}  

  function validate_img(file, fileName) {
	  var filepath = file.name;
	  var extStart = filepath.lastIndexOf(".");
	  var ext = filepath.substring(extStart,filepath.length).toUpperCase();
	  
	  if(ext != ".PNG" && ext != ".JPG" && ext != ".JPEG") {
		  alert(fileName + "的图片限于png,jpeg,jpg格式！！！");
		  return false;
	  } 
	  
	  /*var file_size = 0;
	  if ( $.browser.msie) {
		  var img=new Image();
		  img.src=filepath;
		  while(true) {
			  if(img.fileSize > 0){
				  if(img.fileSize > 2*1024){
					  alert(fileName + "的上传图片大小不能超过200K！！！");
					  return false;
				  } 
				  
				  break;
			  }
		  }
	  } else {
		  file_size = file.size;
		  var size = file_size / 1024;
		  if(size > 200){
			  alert(fileName + "的上传图片大小不能超过200K！！！");
			  return false;
		  } 
	  }*/
	  
	  return true;
  }


function changeCenterIndex(url) {
	$.ajax({
		type : "get",
		url : url,
		data : "radom=" + Math.random(),
		dataType : "text",
		success : function(msg) {
			if (msg == "-999") {
				outLogin();
			} else {
				$(".mainFrame-index").html(msg);
			}
		},
		error : function(XMLHttpRequest, error, errorThrown) {
			alert(error);
			alert(errorThrown);
		}
	});
}

function changeCenterSearch(url, sonUrl, classesId, currentPage, searchText) {
	$.ajax({
		type : "get",
		url : url,
		data : "radom=" + Math.random() + "&url=" + sonUrl + "&classesId="
				+ classesId + "&currentPage=" + currentPage + "&searchText="
				+ searchText,
		dataType : "text",
		success : function(msg) {
			if (msg == "-999") {
				outLogin();
			} else {
				$(".mainFrame-index").html(msg);
				$('#div_search_mask').fadeOut(100);
				$('#div_search_main').slideUp(200);
			}
		},
		error : function(XMLHttpRequest, error, errorThrown) {
			alert(error);
			alert(errorThrown);
		}
	});
}

jQuery.fn.load = function() {
	var thisClass = $(this).attr("class");
	var url = $(this).attr("url");
	var para = $(this).attr("para");
	if (para == null) {
		para = "";
	}

	$.ajax({
		type : "get",
		url : url,
		data : "radom=" + Math.random() + para,
		dataType : "text",
		success : function(msg) {
			if (msg == "-999") {
				outLogin(null, url, para);
			} else {
				$("." + thisClass).html(msg);
			}
		},
		error : function(XMLHttpRequest, error, errorThrown) {
			alert(error);
			alert(errorThrown);
		}
	});
};

function changeSearch(url, para) {
	if (url != "") {
		$('.mainFrame-search').attr("url", url);
		$('.mainFrame-search').attr("para", para);
		$('.mainFrame-search').load();
	}
}

function changeLeft(url) {
	if (url != "") {
		$('.leftFrame').attr("url", url);
		$('.leftFrame').attr("para", "");
		$('.leftFrame').load();
	}
}

function changeCenter(url) {
	if (url != "") {
		$('.mainFrame-center').attr("url", url);
		$('.mainFrame-center').attr("para", "");
		$('.mainFrame-center').load();
	}
}

function changeCenter(url, para) {
	if (url != "") {
		$('.mainFrame-center').attr("url", url);
		$('.mainFrame-center').attr("para", para);
		$('.mainFrame-center').load();
	}
}

function changeCenterOnce(url) {
	if (url != "") {
		var oldUrl = $('.mainFrame-center').attr("url");
		$('.mainFrame-center').attr("url", url);
		$('.mainFrame-center').load();
		$('.mainFrame-center').attr("url", oldUrl);
	}
}

/**
 * js校验值是否为空 2014-11-18 by p
 * 
 * @param value
 * @returns {Boolean}
 */
function isNull(value) {
	if (value == null || value == "") {
		return true;
	}
}

/**
 * js校验值是否为正整数 2014-11-18 by p
 * 
 * @param value
 * @returns {Boolean}
 */
function isUnsignedInteger(value) {
	if (value == "") {
		return false;
	} else {
		var regex = /^[0-9]*$/;
		return regex.test(value);
	}
}

/**
 * js校验值是否为正浮点 2016-07-17 by p
 * 
 * @param value
 * @returns {Boolean}
 */
function isUnsignedDouble(value) {
	if (value == "") {
		return false;
	} else {
		var regex = /^[-\+]?\d+(\.\d+)?$/;
		return regex.test(value);
	}
}

/**
 * js校验值是否为字母和数字 2014-11-18 by p
 * 
 * @param value
 * @returns {Boolean}
 */
function isCharInteger(value) {
	if (value == "") {
		return false;
	} else {
		var regex = /^[A-Za-z0-9]*$/;
		return regex.test(value);
	}
}

/**
 * js校验值是否为日期 2014-11-18 by p
 * 
 * @param value
 * @returns {Boolean}
 */
function isDate(value) {
	var regex = new RegExp(
			"^(?:(?:([0-9]{4}(-|\/)(?:(?:0?[1,3-9]|1[0-2])(-|\/)(?:29|30)|((?:0?[13578]|1[02])(-|\/)31)))|([0-9]{4}(-|\/)(?:0?[1-9]|1[0-2])(-|\/)(?:0?[1-9]|1\\d|2[0-8]))|(((?:(\\d\\d(?:0[48]|[2468][048]|[13579][26]))|(?:0[48]00|[2468][048]00|[13579][26]00))(-|\/)0?2(-|\/)29))))$");
	return regex.test(value);
}

function outLogin(o, url, para) {
	alert("您未登录或登录超时！！！");
	window.open("/login.html", "_self");
}

/** 遮罩层开关 * */
function popDivsMaskSwitch(flag) {
	if (flag) {
		var h = $(document).height() > $(document.body).outerHeight(true) ? $(
				document).height() : $(document.body).outerHeight(true);
		$(maskDiv).fadeIn(100);
		$(maskDiv).css("height", h);
	} else {
		$(maskDiv).fadeOut(100);
	}
}

/** 弹出层开关 * */
function popDivsMainSwitch(flag) {
	if (flag) {
		var x = ($(maskDiv).width() - $(popDiv).width()) / 2;
		var y = $(document).scrollTop() + 100;
		$(popDiv).slideDown(200, function() {
			$('#userName').focus();
		});
		$(popDiv).css("left", x);
		$(popDiv).css("top", y);
	} else {
		$(popDiv).slideUp(200);
	}
}

function popDivsOpen(url, para) {
	popDivsMaskSwitch(true);
	popDivsMainSwitch(true);
	$('#kaptchaImage').click();
	$('#userName').val("");
	$('#userPassword').val("");
	$('#codes').val("");
}

function popDivsClose() {
	popDivsMaskSwitch(false);
	popDivsMainSwitch(false);
}
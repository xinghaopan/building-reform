
(function ($) {
    $.HUploader = function (arg) {
        var place = arg.place;     
        var md5 = "";
        var file;
        var length = 0;
        var fileLength = 0;
        var fileComplete = 0;
        var chunkSize = 2 * 1024 * 1024
        var isOver = false;
        
        this.open = function () {
        	
            place.empty();
            var maskElement = $("<div class='popDivs-mask'></div>").appendTo(place);
            var mainElement = $("<div class='popDivs-main'></div>").appendTo(place);
            mainElement.append("<div class='popDivs-main-title'><H2 class=popDivs-left>文件上传</H2><SPAN class='pointer right closeUploader' >x</SPAN></div>");
            mainElement.append("<div class='popDivs-main-line'></div>");
            var mainContentElement = $("<div class='popDivs-main-content'></div>").appendTo(mainElement);
            var mainContentUlElement = $("<ul></ul>").appendTo(mainContentElement);
            
            var mainContentUlLiElement = $("<li></li>").appendTo(mainContentUlElement);
            mainContentUlLiElement.append("<strong>文件：</strong>");
            var fileElement = $("<input type='file' class='popDivs-main-content-input1' accept='" + arg.accept + "' />").appendTo(mainContentUlLiElement);
            
            mainContentUlLiElement = $("<li></li>").appendTo(mainContentUlElement);
            mainContentUlLiElement.append("<strong>MD5：</strong>");
            var fileMD5Element = $("<div>102030</div>").appendTo(mainContentUlLiElement);
            
            mainContentUlLiElement = $("<li></li>").appendTo(mainContentUlElement);
            var explainElement = $("<strong>分析文件：</strong>").appendTo(mainContentUlLiElement);
            var fileProgressElement = $("<progress value='0' max='100' style='height:24px;width:194px;'></progress>").appendTo(mainContentUlLiElement);
            var filePercentElement = $("<span>0</span>").appendTo(mainContentUlLiElement);
            mainContentUlLiElement.append("%");
            
            mainContentUlLiElement = $("<li></li>").appendTo(mainContentUlElement);
            mainContentUlLiElement.append("<input class='popDivs-main-content-button setFile' type='button' value=' 保 存 ' disabled='disabled' />&nbsp;&nbsp;");
            mainContentUlLiElement.append("<input class='popDivs-main-content-button closeUploader' type='button' value=' 取 消 ' />");
            
            fileElement.bind('change', function() {
            	isOver = false;
            	$(".setFile").attr("disabled", true);
            	var haveFile = "";
                var fileReader = new FileReader();
                blobSlice = File.prototype.mozSlice || File.prototype.webkitSlice || File.prototype.slice, file = this.files[0],
                chunks = Math.ceil(file.size / chunkSize), currentChunk = 0, spark = new SparkMD5();
                
        		var fileArray = new Array(chunks);
        		var fileExtName = getExtName(file.name);
        		length = chunks;
                if (file) {
                    var fileSize = 0;
                    if (file.size > 1024 * 1024)
                      fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
                    else
                      fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';

                    //fileNameElement.html('Name: ' + file.name);
                    //fileSizeElement.html('Size: ' + fileSize);
                    //fileTypeElement.html('Type: ' + file.type);
                    explainElement.html('分析文件：');
        		}
                
                fileReader.onload = function(e) {
                	fileProgressElement.val(Math.ceil((currentChunk + 1) / chunks  * 100));
                	filePercentElement.html(Math.ceil((currentChunk + 1) / chunks * 100));
                    console.log("read chunk nr", currentChunk + 1, "of", chunks);
                    spark.appendBinary(e.target.result);
                    
                    // append binary string
                    currentChunk++;

                    if (currentChunk < chunks) {
                        loadNext();
                    } else {
                        console.log("finished loading");
                        md5 = spark.end();
                        console.info("computed hash", md5);
                        fileMD5Element.html(md5);
                        $.ajax({
                    		type: "get",
                    		url: "/upload/list/" + md5,
                    		data: "radom=" + Math.random(),
                    		dataType: "text",
                    		success: function(msg) {
                    			haveFile = msg;
                    			uploadFile();
                    			
                    		},
                    		error : function(XMLHttpRequest, error, errorThrown) {
                    			alert("请求上传信息失败！！！\n" + error);
                    		}
                    	});


                    }
                };

                function loadNext() {
                	console.log("currentChunk = " + currentChunk);
                    var start = currentChunk * chunkSize, end = start + chunkSize >= file.size ? file.size : start + chunkSize;
                    fileArray[currentChunk] = blobSlice.call(file, start, end);
                    fileReader.readAsBinaryString(fileArray[currentChunk]);
                }

                loadNext();
                
        		function uploadFile() {
        			var progressArray = new Array();
        			var percent = 0;
        			fileLength = 0;
        			fileComplete = 0;
        			
        			explainElement.html('上传文件：');
        			fileProgressElement.val(0);
                	filePercentElement.html(0);
                	
        			for (var i = 0; i < fileArray.length; i ++) {
        				if (haveFile.indexOf("," + i + ",") == -1) {
        					fileLength ++;
        				}
        				
        				progressArray.push(0);
        			}

        			for (var i = 0; i < fileArray.length; i ++) {
        				if (haveFile.indexOf("," + i + ",") == -1) {
        					var fd = new FormData();
        					fd.append("index", i);
        					fd.append("md5", md5);
        					fd.append("length", fileLength);
        					fd.append("fileName", file.name);
        					fd.append("file", fileArray[i]);
        		            var xhr = new XMLHttpRequest();
        		            
        		            xhr.addEventListener("load", uploadComplete, false);
        		            xhr.addEventListener("error", uploadFailed, false);
        		            xhr.addEventListener("abort", uploadCanceled, false);
        		            xhr.open("POST", "/upload/transfer");
        		 
        		            xhr.send(fd);
        				}
        			}
        		}
        		
        		function uploadComplete(evt) {
                    if (evt.target.responseText == "1") {
                    	fileComplete ++;
                    }
                    
                    fileProgressElement.val(Math.ceil(fileComplete * 100 / fileLength));
                    filePercentElement.html(Math.ceil(fileComplete * 100 / fileLength));
                    if (fileLength == fileComplete) {
                    	var fd = new FormData();
        				fd.append("md5", md5);
        				fd.append("length", length);
        				fd.append("fileName", file.name);
        	            var xhr = new XMLHttpRequest();
        	            xhr.addEventListener("load", mergeComplete, false);
        	            xhr.addEventListener("error", mergeFailed, false);
        	            xhr.open("POST", "/upload/merge");
        	            xhr.send(fd);
                    }
        		}
        		
        		function uploadCanceled(evt) {
        	           // alert("The upload has been canceled by the user or the browser dropped the connection.");
        		}
        		
        		function uploadFailed(evt) {
        			alert("文件上传失败，传输错误！！！");
        		}

        		
        		function mergeComplete(evt) {
                    if (evt.target.responseText == "1") {
                    	isOver = true;
                    	$(".setFile").attr("disabled", false);
                    	
                    	alert("文件上传成功！！！");
                    }
        		}
        		
        		function mergeFailed(evt) {
        			alert("文件上传失败，无法合并文件！！！");
        		}
            });
            
            function openUploader() {
            	var h = $(document).height() > $(document.body).outerHeight(true) ? $(document).height() : $(document.body).outerHeight(true);
        		maskElement.fadeIn(100);
        		maskElement.css("height", h);
        		
        		var x = (maskElement.width() - mainElement.width()) / 2;
        		var y = $(document).scrollTop() + 100;
        		mainElement.slideDown(200, function() {
        			//$('#userName').focus();
        		});
        		mainElement.css("left", x);
        		mainElement.css("top", y);
            }
                        
            $(".setFile").bind('click', function() {
            	if (isOver) {
            		var thisName = md5 + getExtName(file.name);
            		if (!arg.noDic) {
            			thisName = '/uploads/' + thisName;
            		}
            		arg.files.val(thisName);
            		if(arg.image) {
            			arg.image.attr("src", thisName);
            		}
            		maskElement.fadeOut(100);
                	mainElement.slideUp(200);
                	
                	place.empty();
            	}
            	else {
            		alert("您还未上传文件，无法保存！！！");
            	}
            });
            
            $(".closeUploader").bind('click', function() {
            	if (isOver) {
            		if (confirm("您已上传了文件，是否保存？") == true) {
            			var thisName = md5 + getExtName(file.name);
                		if (!arg.noDic) {
                			thisName = '/uploads/' + thisName;
                		}
            		    arg.files.val(thisName);
            		    if(arg.image) {
                			arg.image.attr("src", thisName);
                		}
            		}
            	}
            	
            	maskElement.fadeOut(100);
            	mainElement.slideUp(200);
            	
            	place.empty();
            });
            
            openUploader();
        };
        
        
        
        function getExtName(fileName){
        	var pos = fileName.lastIndexOf(".");
    		return fileName.substring(pos); 
        }
        
        
    };
})(jQuery);
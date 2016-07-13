package com.cc.buildingReform.Common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import net.sf.json.JSONObject;

public class Common {
	
	public static String frontPages(Integer mid, int currentPage, int maxPage, String url, String path) {
		String str = "";
		int bi = 0;
		int ei = 0;
		
		str = "<a class='btn_Search' href='javascript:void(0);' currentPage='0'><img src='/images/p_1.png' /></a>";
		if (currentPage != 0) {
			if (currentPage == 1) {
				str += "<a class='btn_Search' href='javascript:void(0);' currentPage='0'><img src='/images/p_2.png' /></a>";
			} else {
				str += "<a class='btn_Search' href='javascript:void(0);' currentPage='" + (currentPage - 1) + "'><img src='/images/p_2.png' /></a>";
			}
		}
		if (maxPage <= 9) {
			for (int i = 0; i < maxPage; i++) {
				if (currentPage == i) {
					str += "<span class='current'>" + (i + 1) + "</span>";
				} else {
					if (i == 0) {
						str += "<a class='btn_Search' href='javascript:void(0);' currentPage='0'>" + (i + 1) + "</a>";
					} else {
						str += "<a class='btn_Search' href='javascript:void(0);' currentPage='" + i + "'>" + (i + 1) + "</a>";
					}
				}
			}
		} else {
			if (currentPage > 3) {
				if ((maxPage - currentPage - 1) > 3) {
					bi = currentPage - 4;
					ei = bi + 8;
				} else {
					ei = maxPage - 1;
					bi = ei - 8;
				}
			} else {
				bi = 0;
				ei = bi + 8;
			}

			for (int i = bi; i <= ei; i++) {
				if (currentPage == i) {
					str += "<span class='current'>" + (i + 1) + "</span>";
				} else {
					if (i == 0) {
						str += "<a class='btn_Search' href='javascript:void(0);' currentPage='0'>" + (i + 1) + "</a>";
					} else {
						str += "<a class='btn_Search' href='javascript:void(0);' currentPage='" + i + "'>" + (i + 1) + "</a>";
					}
				}
			}
		}
		if ((currentPage + 1) != maxPage) {
			str += "<a class='btn_Search' href='javascript:void(0);' currentPage='" + (currentPage + 1) + "' ><img src='/images/p_3.png' /></a>";
		}

		if (maxPage == 1) {
			str += "<a class='btn_Search' href='javascript:void(0);' currentPage='0'><img src='/images/p_4.png' /></a>";
		} else {
			str += "<a class='btn_Search' href='javascript:void(0);' currentPage='" + (maxPage - 1) + "'><img src='/images/p_4.png' /></a>";
		}
		str += "<em>当前页数&nbsp;" + (currentPage + 1) + "&nbsp;/&nbsp;" + maxPage + "</em>";
		return str;
	}
	
	public static String pages(Integer mid, int currentPage, int maxPage, String url, String path) {
		String str = "";
		int bi = 0;
		int ei = 0;
		
		str = "<li><a class='btn_Search' href='javascript:void(0);' currentPage='0'>&laquo;</a></li>";
		if (maxPage <= 9) {
			for (int i = 0; i < maxPage; i++) {
				if (currentPage == i) {
					str += "<li><a href='javascript:void(0);' class='active'>" + (i + 1) + "</a></li>";
				} else {
					if (i == 0) {
						str += "<li><a class='btn_Search' href='javascript:void(0);' currentPage='0'>" + (i + 1) + "</a></li>";
					} else {
						str += "<li><a class='btn_Search' href='javascript:void(0);' currentPage='" + i + "'>" + (i + 1) + "</a></li>";
					}
				}
			}
		} else {
			if (currentPage > 3) {
				if ((maxPage - currentPage - 1) > 3) {
					bi = currentPage - 4;
					ei = bi + 8;
				} else {
					ei = maxPage - 1;
					bi = ei - 8;
				}
			} else {
				bi = 0;
				ei = bi + 8;
			}

			for (int i = bi; i <= ei; i++) {
				if (currentPage == i) {
					str += "<li><a href='javascript:void(0);' class='active'>" + (i + 1) + "</a></li>";
				} else {
					if (i == 0) {
						str += "<li><a class='btn_Search' href='javascript:void(0);' currentPage='0'>" + (i + 1) + "</a></li>";
					} else {
						str += "<li><a class='btn_Search' href='javascript:void(0);' currentPage='" + i + "'>" + (i + 1) + "</a></li>";
					}
				}
			}
		}

		if (maxPage == 1) {
			str += "<li><a class='btn_Search' href='javascript:void(0);' currentPage='0'>&raquo;</a></li>";
		} else {
			str += "<li><a class='btn_Search' href='javascript:void(0);' currentPage='" + (maxPage - 1) + "'>&raquo;</a></li>";
		}
		
		return str;
	}
	
	/**
	 * String���� ת�� Integer����
	 * 
	 * @param params
	 * @return
	 */
	public static List<Integer> StringToIntegerArray(String params) {
		String tmp = "";
		if (params != null && params != "") {
			tmp = params.substring(1);
		} else {
			tmp = "0";
		}
		String[] temp = tmp.split(",");

		List<Integer> r = new ArrayList<Integer>();
		for (int i = 0; i < temp.length; i++) {
			r.add(Integer.parseInt(temp[i]));
		}
		return r;
	}

	public static void WriteJSON(HttpServletResponse response, Map<String, Object> map)
			throws Exception {

		JSONObject result =JSONObject.fromObject(map);
		
		response.setCharacterEncoding("UTF-8");
		
		response.getWriter().print(result);
	}

	// MD5����
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void print(HttpServletResponse response, String message) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(message);
	}

	public static void print(HttpServletResponse response, Integer message) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(message);
	}
	
	public static void reDirect(HttpServletResponse response, String url, String message,
			String script) {
		response.setContentType("text/html;charset=UTF-8");

		String html = "<script type=\"text/javascript\">";

		if (message != null && !message.equals("")) {
			html += "alert('" + message + "');";
		}
		if (script != null) {
			html += script;
		}
		if (url != null && !url.equals("")) {
			html += "window.location.href='" + url + "';";
		}
		html += "</script>";

		try {
			response.getWriter().print(html);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void reHistoryBack(HttpServletResponse response, String message, String script) {
		response.setContentType("text/html;charset=UTF-8");

		String html = "<script type=\"text/javascript\">";

		if (message != null && !message.equals("")) {
			html += "alert('" + message + "');";
		}
		if (script != null) {
			html += script;
		}
		html += "history.back();";
		html += "</script>";

		try {
			response.getWriter().print(html);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getExtensionName(String filename) {   
        if ((filename != null) && (filename.length() > 0)) {   
            int dot = filename.lastIndexOf('.');   
            if ((dot >-1) && (dot < (filename.length() - 1))) {   
                return filename.substring(dot + 1);   
            }   
        }   
        return filename;   
    } 
	
	public static boolean doc2XmlFile(Document document, String filename) {
		boolean flag = true;
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			/** 编码 */
			// transformer.setOutputProperty(OutputKeys.ENCODING, "GB2312");
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filename));
			transformer.transform(source, result);
		} 
		catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		
		return flag;
	}
	
	public static String readHtml(String httpUrl) {
		long time = new Date().getTime();
		String htmlCode = "";
		try {
			System.out.println("begin readHtml ==>" + httpUrl);
			InputStream in;
			URL url = new java.net.URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/4.0");
			connection.connect();
			in = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line;
			while ((line = reader.readLine()) != null) {
				htmlCode += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			time = new Date().getTime() - time;
			System.out.println("重新生成地址�? " + httpUrl + ",执行时间: " + time + "毫秒.");
		}
		return htmlCode;
	}
	
	public static String getIpAddr(HttpServletRequest request) {  
	    String ip = request.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	}
}

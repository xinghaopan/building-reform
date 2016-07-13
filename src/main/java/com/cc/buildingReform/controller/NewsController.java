package com.cc.buildingReform.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.cc.buildingReform.Annotation.Permissions;
import com.cc.buildingReform.Common.Common;
import com.cc.buildingReform.form.Menu;
import com.cc.buildingReform.form.News;
import com.cc.buildingReform.form.User;
import com.cc.buildingReform.service.MenuService;
import com.cc.buildingReform.service.NewsService;

@RestController
public class NewsController {
	@Autowired
	private NewsService newsService;

	@Autowired
	private MenuService menuService;

	private static Logger log = LoggerFactory.getLogger(NewsController.class);
	
	/**
	 * 列表页面 2015-08-05 by p
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "/bk/login")
	@RequestMapping("/bk/news/list{path}/{mid}")
	public String list(@PathVariable("mid") Integer mid, @PathVariable("path") String path, 
			@RequestParam(value = "search_title", required = false) String title,
			@RequestParam(value = "search_content", required = false) String content,
			@RequestParam(value = "author", required = false) String author,
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "count", required = false) Integer count,
			HttpServletRequest request, Model model) throws Exception {
		try {
			if (currentPage == null) {
				currentPage = 0;
			}
			
			if (count == null || count == 0) {
				count = 10;
			}
			
			if (title == null) {
				title = "";
			}
			
			if (content == null) {
				content = "";
			}
			
			if (author == null) {
				author = "";
			}
			
			int maxCount = newsService.getCountByMid(mid, 0, title, content, author);
			int maxPage = (maxCount - 1) / count + 1;
			model.addAttribute("count", count);
			model.addAttribute("maxPage", maxPage);
			model.addAttribute("mid", mid);
			model.addAttribute("path", path);
			model.addAttribute("user", (User)request.getSession().getAttribute("loginUser"));
			model.addAttribute("title", title);
			model.addAttribute("content", content);
			model.addAttribute("author", author);
			model.addAttribute("list", newsService.findByMid(mid, 0, title, content, author, currentPage * count, count));
			model.addAttribute("pages", Common.pages(mid, currentPage, maxPage, "", ""));
		}
		catch(Exception e) {
			log.error("/bk/news/list" + path + "/" + mid, e);
		}
		
		return "/bk/news/list" + path;
	}

	/**
	 * 修改 2015-08-05 by p
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "/bk/login")
	@RequestMapping("/bk/news/edit{path}/{mid}")
	public String edit(@PathVariable("mid") Integer mid, @PathVariable("path") String path, 
			@RequestParam(value = "id", required = false) Integer id, Model model) throws Exception {
		try {
			News news = new News();
			Menu menu = menuService.findById(mid);
			news.setMenuId(mid);
			news.setOrder(0);
			news.setAudit(menu.getIsaudit());
			news.setHits(0);
			news.setDate(new Date());
			if (id != null) {
				if (id != 0) {
					news = newsService.findById(id, 0);
				}
			}
			else {
				List<News> l = newsService.findByMid(mid, 0, 0, 1);
				if (l != null && l.size() >0) {
					news = l.get(0);
				}
			}
			model.addAttribute("mid", mid);
			model.addAttribute("news", news);
		}
		catch(Exception e) {
			log.error("/bk/news/edit" + path + "?id=" + id, e);
		}
		
		return "/bk/news/add" + path;
	}
	
	/**
	 * 保存 2015-08-12 by p
	 * 
	 * @param mid
	 * @param news
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/news/save/{mid}", method = RequestMethod.POST)
	public void save(@PathVariable("mid") Integer mid, @ModelAttribute("news") News news, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			User user = (User)request.getSession().getAttribute("loginUser");
			
			CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
			  
			commonsMultipartResolver.setDefaultEncoding("utf-8");  
 
			if (commonsMultipartResolver.isMultipart(request)) {  
				String path = request.getSession().getServletContext().getRealPath("/");
				
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				
				MultipartFile file = multiRequest.getFile("bigImg");
				if (file != null) {
					String fileName = "/uploads/" + new Date().getTime() + "." + Common.getExtensionName(file.getOriginalFilename());
					file.transferTo(new File(path + fileName)); 
					news.setBigImage(fileName);
				}
				
				file = multiRequest.getFile("middleImg");
				if (file != null) {
					String fileName = "/uploads/" + new Date().getTime() + "."  + Common.getExtensionName(file.getOriginalFilename());
					file.transferTo(new File(path + fileName)); 
					news.setMiddleImage(fileName);
				}
				
				file = multiRequest.getFile("smallImg");
				if (file != null) {
					String fileName = "/uploads/" + new Date().getTime() + "."  + Common.getExtensionName(file.getOriginalFilename());
					file.transferTo(new File(path + fileName)); 
					news.setSmallImage(fileName);
				}
			}  
			
			news.setContent(news.getSubTitle());
			news.setUser(user);
			newsService.save(news);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/news/save/" + mid, e);
		}
		
		Common.print(response, msg);
	}

	/**
	 * 删除 2015-08-05 by p
	 * 
	 * @param id
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/news/del/{mid}")
	public void del(@PathVariable("mid") Integer mid, @RequestParam("id") Integer id, Model model, 
			HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			newsService.remove(id);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/news/del?id=" + id, e);
		}
		
		Common.print(response, msg);
	}
	
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/news/audit/{mid}")
	public void audit(@PathVariable("mid") Integer mid, @RequestParam("id") Integer id, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			User user = (User)request.getSession().getAttribute("loginUser");
			if (user.getRole().getPower().indexOf("-1") >= 0) {
				News news = newsService.findById(id, 0);
				news.setAudit(1);
				newsService.save(news);
			}
			else {
				msg = -1;
			}
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/news/audit?id=" + id, e);
		}
		
		Common.print(response, msg);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor = new CustomDateEditor(df, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}
	
}

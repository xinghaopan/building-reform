package com.cc.buildingReform.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cc.buildingReform.Common.Common;
import com.cc.buildingReform.form.News;
import com.cc.buildingReform.Annotation.Permissions;
import com.cc.buildingReform.service.MenuService;
import com.cc.buildingReform.service.NewsService;


@RestController
public class MainController {
	private static Logger log = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model)	throws Exception {
		try {
			long starTime = System.currentTimeMillis();
			
			model.addAttribute("mid", 0);
			model.addAttribute("gzdt", newsService.findByMid(17, 1, 0, 5));
			model.addAttribute("zcwj", newsService.findByMid(16, 1, 0, 5));
			model.addAttribute("xtgx", newsService.findByMid(15, 1, 0, 5));
			model.addAttribute("xzzq", newsService.findByMid(14, 1, 0, 5));
			
			long endTime = System.currentTimeMillis();
			log.warn("首页加载耗时： " + (endTime-starTime));
		}
		catch(Exception e) {
			log.error("/index", e);
		}
		
		return "/index";
	}
	
	@RequestMapping("/news/list{path}/{mid}")
	public String list(@PathVariable("mid") Integer mid, @PathVariable("path") String path, 
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "count", required = false) Integer count,
			HttpServletRequest request, Model model) throws Exception {
		try {
			if (currentPage == null) {
				currentPage = 0;
			}
			
			if (count == null || count == 0) {
				count = 20;
			}
			
			int maxCount = newsService.getCountByMid(mid, 1);
			int maxPage = (maxCount - 1) / count + 1;
			model.addAttribute("count", count);
			model.addAttribute("maxPage", maxPage);
			model.addAttribute("mid", mid);
			model.addAttribute("menu", menuService.findById(mid));
			model.addAttribute("path", path);
			model.addAttribute("list", newsService.findByMid(mid, 1, currentPage * count, count));
			model.addAttribute("pages", Common.frontPages(mid, currentPage, maxPage, "", ""));
		}
		catch(Exception e) {
			log.error("/news/list" + path + "/" + mid, e);
		}
		
		return "/news/list" + path;
	}

	@RequestMapping("/news/page{path}/{mid}")
	public String page(@PathVariable("mid") Integer mid, @PathVariable("path") String path, 
			@RequestParam(value = "id", required = false) Integer id,
			HttpServletRequest request, Model model) throws Exception {
		try {
			News news = new News();
			if (id != null) {
				news = newsService.findById(id, 1);
			}
			else {
				List<News> l = newsService.findByMid(mid, 1, 0, 1);
				if (l != null && l.size() > 0) {
					news = l.get(0);
				}
			}
			
			
			model.addAttribute("news", news);
			model.addAttribute("mid", mid);
			model.addAttribute("menu", menuService.findById(mid));
			model.addAttribute("path", path);
			
		}
		catch(Exception e) {
			log.error("/news/page" + path + "/" + mid, e);
		}
		
		return "/news/page" + path;
	}
	/**
	 * 后台 主页面
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/bk/main")
	@Permissions(target = "loginUser", url = "/bk/login")
	public String list()	throws Exception {
		try {
			   
		}
		catch(Exception e) {
			log.error("/bk/main", e);
		}
		
		return "/bk/main";
	}
	
}

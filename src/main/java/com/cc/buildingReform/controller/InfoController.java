package com.cc.buildingReform.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.cc.buildingReform.Annotation.Permissions;
import com.cc.buildingReform.Common.Common;
import com.cc.buildingReform.form.Info;
import com.cc.buildingReform.form.User;
import com.cc.buildingReform.service.InfoService;

@RestController
public class InfoController {
	@Autowired
	private InfoService infoService;

	private static Logger log = LoggerFactory.getLogger(InfoController.class);
	
	/**
	 * 列表页面 2015-08-05 by p
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "/index")
	@RequestMapping("/bk/info/list{path}/{mid}")
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
			
			int maxCount = infoService.getCount();
			int maxPage = (maxCount - 1) / count + 1;
			model.addAttribute("count", count);
			model.addAttribute("maxPage", maxPage);
			model.addAttribute("mid", mid);
			model.addAttribute("path", path);
			model.addAttribute("user", (User)request.getSession().getAttribute("loginUser"));
			model.addAttribute("title", title);
			model.addAttribute("content", content);
			model.addAttribute("author", author);
			model.addAttribute("list", infoService.findAll(currentPage * count, count));
			model.addAttribute("pages", Common.pages(mid, currentPage, maxPage, "", ""));
		}
		catch(Exception e) {
			log.error("/bk/info/list" + path + "/" + mid, e);
		}
		
		return "/bk/info/list" + path;
	}

	/**
	 * 修改 2015-08-05 by p
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "/index")
	@RequestMapping("/bk/info/edit{path}/{mid}")
	public String edit(@PathVariable("mid") Integer mid, @PathVariable("path") String path, 
			@RequestParam(value = "id", required = false) Integer id, Model model) throws Exception {
		try {
			Info info = new Info();
			info.setDate(new Date());
			if (id != null) {
				if (id != 0) {
					info = infoService.findById(id);
				}
			}
			model.addAttribute("mid", mid);
			model.addAttribute("info", info);
		}
		catch(Exception e) {
			log.error("/bk/info/edit" + path + "?id=" + id, e);
		}
		
		return "/bk/info/add" + path;
	}
	
	/**
	 * 保存 2015-08-12 by p
	 * 
	 * @param mid
	 * @param info
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/info/save/{mid}", method = RequestMethod.POST)
	public void save(@PathVariable("mid") Integer mid, @ModelAttribute("info") Info info, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			User user = (User)request.getSession().getAttribute("loginUser");
			
			info.setDepartmentId(user.getDepartmentId());
			info.setDepartmentName(user.getDepartmentName());
			info.setUserId(user.getId());
			info.setUserName(user.getTrueName());
			info.setDate(new Date());
			infoService.save(info);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/info/save", e);
			
			if (e.getMessage() != null) {
				if (e.getMessage().equals("-1")) {
					msg = -1;
					log.warn("/bk/info/save/", "计划年度" + info.getPlanYear() + "没有剩余的指标进行上报！！！");
				}
				else {
					msg = 0;
					log.error("/bk/info/save/", e);
				}
			}
			else {
				msg = 0;
				log.error("/bk/info/save/", e);
			}
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
	@RequestMapping(value = "/bk/info/del/{mid}")
	public void del(@PathVariable("mid") Integer mid, @RequestParam("id") Integer id, Model model, 
			HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			infoService.remove(id);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/info/del?id=" + id, e);
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

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cc.buildingReform.Annotation.Permissions;
import com.cc.buildingReform.Common.Common;
import com.cc.buildingReform.form.ArchiveInfo;
import com.cc.buildingReform.form.Quota;
import com.cc.buildingReform.form.User;
import com.cc.buildingReform.service.ArchiveInfoService;
import com.cc.buildingReform.service.AuditService;
import com.cc.buildingReform.service.DicService;

@RestController
public class ArchiveInfoController {
	@Autowired
	private ArchiveInfoService archiveInfoService;

	@Autowired
	private DicService dicService;

	@Autowired
	private AuditService auditService;

	private static Logger log = LoggerFactory.getLogger(ArchiveInfoController.class);
	
	/**
	 * 列表页面 2015-08-05 by p
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "/index")
	@RequestMapping("/bk/archiveInfo/list{path}/{mid}")
	public String list(@PathVariable("mid") Integer mid, 
			@RequestParam(value = "year", required = false) Integer year, 
			@RequestParam(value = "personId", required = false) String personId, 
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "count", required = false) Integer count,
			HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		try {
			User user = (User) request.getSession().getAttribute("loginUser");
			
			if (year == null) {
				year = Quota.getCurrentYear();
			}
			
			if (currentPage == null) {
				currentPage = 0;
			}
			
			if (count == null || count == 0) {
				count = 10;
			}
				
			int maxCount = archiveInfoService.getCountByDepartmentId(year, user, personId);
			int maxPage = (maxCount - 1) / count + 1;
			model.addAttribute("count", count);
			model.addAttribute("maxPage", maxPage);
			model.addAttribute("mid", mid);
			model.addAttribute("user", (User)request.getSession().getAttribute("loginUser"));
			model.addAttribute("personId", personId);
			model.addAttribute("list", archiveInfoService.findByDepartmentId(year, user, personId, currentPage * count, count));
			model.addAttribute("pages", Common.pages(mid, currentPage, maxPage, "", ""));
			
			model.addAttribute("dicList", dicService.findAll());
			model.addAttribute("year", year);
		}
		catch(Exception e) {
			log.error("/bk/archiveInfo/list", e);
		}
		
		return "/bk/archiveInfo/list";
	}
	
	@Permissions(target = "loginUser", url = "/index")
	@RequestMapping("/bk/archiveInfo/print/{mid}")
	public String print(@PathVariable("mid") Integer mid,
			@RequestParam(value = "id", required = true) Integer id, Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ArchiveInfo info = archiveInfoService.findById(id);
			
			model.addAttribute("mid", mid);
			model.addAttribute("info", info);
			
			if (info.getId() != null && info.getId() != 0) {
				model.addAttribute("auditList", auditService.findByInfoId(info.getId()));
			}
		}
		catch(Exception e) {
			log.error("/bk/archiveInfo/print?id=" + id, e);
		}
		
		return "/bk/archiveInfo/print";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor = new CustomDateEditor(df, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}
	
}

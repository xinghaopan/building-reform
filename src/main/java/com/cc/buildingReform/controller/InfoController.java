package com.cc.buildingReform.controller;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.cc.buildingReform.Annotation.Permissions;
import com.cc.buildingReform.Common.Common;
import com.cc.buildingReform.form.Department;
import com.cc.buildingReform.form.Info;
import com.cc.buildingReform.form.Quota;
import com.cc.buildingReform.form.User;
import com.cc.buildingReform.service.DepartmentService;
import com.cc.buildingReform.service.DicService;
import com.cc.buildingReform.service.InfoService;

@RestController
public class InfoController {
	@Autowired
	private InfoService infoService;

	@Autowired
	private DicService dicService;

	@Autowired
	private DepartmentService departmentService;

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
			@RequestParam(value = "year", required = false) Integer year,
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "count", required = false) Integer count,
			HttpServletRequest request, Model model) throws Exception {
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
			
			int maxCount = infoService.getCount(year, user);
			int maxPage = (maxCount - 1) / count + 1;
			model.addAttribute("count", count);
			model.addAttribute("maxPage", maxPage);
			model.addAttribute("mid", mid);
			model.addAttribute("path", path);
			model.addAttribute("user", user);
			
			model.addAttribute("list", infoService.findAll(year, user, currentPage * count, count));
			model.addAttribute("pages", Common.pages(mid, currentPage, maxPage, "", ""));
			
			model.addAttribute("year", year);
			model.addAttribute("dicList", dicService.findAll());
		}
		catch(Exception e) {
			log.error("/bk/info/list" + path + "/" + mid, e);
		}
		
		return "/bk/info/list" + path;
	}

	@Permissions(target = "loginUser", url = "/index")
	@RequestMapping("/bk/info/waitSubmit/{mid}")
	public String waitSubmit(@PathVariable("mid") Integer mid, 
			@RequestParam(value = "year", required = false) Integer year,
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "count", required = false) Integer count,
			HttpServletRequest request, Model model) throws Exception {
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
			
			int maxCount = infoService.getCountByEdit(year, user);
			int maxPage = (maxCount - 1) / count + 1;
			model.addAttribute("count", count);
			model.addAttribute("maxPage", maxPage);
			model.addAttribute("mid", mid);
			model.addAttribute("user", user);
			model.addAttribute("list", infoService.findByEdit(year, user, currentPage * count, count));
			model.addAttribute("pages", Common.pages(mid, currentPage, maxPage, "", ""));
			model.addAttribute("year", year);
			model.addAttribute("dicList", dicService.findAll());
		}
		catch(Exception e) {
			log.error("/bk/info/waitSubmit/" + mid, e);
		}
		
		return "/bk/info/waitSubmit";
	}
	
	@Permissions(target = "loginUser", url = "/index")
	@RequestMapping("/bk/info/waitAudit/{mid}")
	public String waitAudit(@PathVariable("mid") Integer mid, 
			@RequestParam(value = "year", required = false) Integer year,
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "count", required = false) Integer count,
			HttpServletRequest request, Model model) throws Exception {
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
			
			int maxCount = infoService.getCountByWaitAudit(year, user);
			int maxPage = (maxCount - 1) / count + 1;
			model.addAttribute("count", count);
			model.addAttribute("maxPage", maxPage);
			model.addAttribute("mid", mid);
			model.addAttribute("user", user);
			model.addAttribute("list", infoService.findByWaitAudit(year, user, currentPage * count, count));
			model.addAttribute("pages", Common.pages(mid, currentPage, maxPage, "", ""));
			model.addAttribute("year", year);
			model.addAttribute("dicList", dicService.findAll());
		}
		catch(Exception e) {
			log.error("/bk/info/waitAudit/" + mid, e);
		}
		
		return "/bk/info/waitAudit";
	}
	
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/info/submit/{mid}")
	public void submit(@PathVariable("mid") Integer mid, @RequestParam("id") Integer id, Model model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			User user = (User) request.getSession().getAttribute("loginUser");
			
			infoService.submit(user, id);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/info/del?id=" + id, e);
			
			if (e.getMessage() != null) {
				if (e.getMessage().equals("-1")) {
					msg = -1;
					log.warn("/bk/info/del?id=" + id, "上报信息错误！！！");
				}
				else if (e.getMessage().equals("-2")) {
					msg = -1;
					log.warn("/bk/info/del?id=" + id, "上报信息状态错误！！！");
				}
				else if (e.getMessage().equals("-3")) {
					msg = -1;
					log.warn("/bk/info/del?id=" + id, "上级机构不存在！！！");
				}
				else if (e.getMessage().equals("-4")) {
					msg = -1;
					log.warn("/bk/info/del?id=" + id, "没有权限进行审核操作！！！");
				}
				else {
					msg = 0;
					log.error("/bk/info/del?id=" + id, e);
				}
			}
			else {
				msg = 0;
				log.error("/bk/info/del?id=" + id, e);
			}
		}
		
		Common.print(response, msg);
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
			@RequestParam(value = "id", required = false) Integer id, Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			User user = (User) request.getSession().getAttribute("loginUser");
			
			Info info = new Info();
			info.setSumFund(0d);
			info.setGrantProvinceFund(0d);
			info.setGrantCountiesFund(0d);
			info.setPersonSelfFund(0d);
			info.setDate(new Date());
			info.setState(Info.STATE_EDIT);
			if (id != null) {
				if (id != 0) {
					info = infoService.findById(id);
				}
			}
			
			Department department = departmentService.findById(user.getDepartmentId());
			Department fatherDepartment = departmentService.findById(department.getFatherId());
			
			model.addAttribute("mid", mid);
			model.addAttribute("info", info);
			
			model.addAttribute("department", department);
			model.addAttribute("fatherDepartment", fatherDepartment);
			model.addAttribute("dicList", dicService.findAll());
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
			
			CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
			  
			commonsMultipartResolver.setDefaultEncoding("utf-8");  
 
			if (commonsMultipartResolver.isMultipart(request)) {  
				String path = request.getSession().getServletContext().getRealPath("/");
				
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				
				MultipartFile file = multiRequest.getFile("personImg");
				if (file != null) {
					String fileName = "/uploads/" + new Date().getTime() + "." + Common.getExtensionName(file.getOriginalFilename());
					file.transferTo(new File(path + fileName)); 
					info.setPersonImage(fileName);
				}
				
				file = multiRequest.getFile("acceptanceImg");
				if (file != null) {
					String fileName = "/uploads/" + new Date().getTime() + "." + Common.getExtensionName(file.getOriginalFilename());
					file.transferTo(new File(path + fileName)); 
					info.setAcceptanceImage(fileName);
				}
				
				file = multiRequest.getFile("fundSendImg");
				if (file != null) {
					String fileName = "/uploads/" + new Date().getTime() + "." + Common.getExtensionName(file.getOriginalFilename());
					file.transferTo(new File(path + fileName)); 
					info.setFundSendImage(fileName);
				}
				
				file = multiRequest.getFile("houseOldImg");
				if (file != null) {
					String fileName = "/uploads/" + new Date().getTime() + "." + Common.getExtensionName(file.getOriginalFilename());
					file.transferTo(new File(path + fileName)); 
					info.setHouseOldImage(fileName);
				}
				
				file = multiRequest.getFile("houseBuildingImg");
				if (file != null) {
					String fileName = "/uploads/" + new Date().getTime() + "." + Common.getExtensionName(file.getOriginalFilename());
					file.transferTo(new File(path + fileName)); 
					info.setHouseBuildingImage(fileName);
				}
				
				file = multiRequest.getFile("houseOutNewImg");
				if (file != null) {
					String fileName = "/uploads/" + new Date().getTime() + "." + Common.getExtensionName(file.getOriginalFilename());
					file.transferTo(new File(path + fileName)); 
					info.setHouseOutNewImage(fileName);
				}
				
				file = multiRequest.getFile("houseInNewImg");
				if (file != null) {
					String fileName = "/uploads/" + new Date().getTime() + "." + Common.getExtensionName(file.getOriginalFilename());
					file.transferTo(new File(path + fileName)); 
					info.setHouseInNewImage(fileName);
				}
			}  
			
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
			
			if (e.getMessage() != null) {
				if (e.getMessage().equals("-1")) {
					msg = -1;
					log.warn("/bk/info/del?id=" + id, "上报信息错误！！！");
				}
				else if (e.getMessage().equals("-2")) {
					msg = -1;
					log.warn("/bk/info/del?id=" + id, "上报信息状态错误！！！");
				}
				else if (e.getMessage().equals("-3")) {
					msg = -1;
					log.warn("/bk/info/del?id=" + id, "指标信息错误！！！");
				}
				else {
					msg = 0;
					log.error("/bk/info/del?id=" + id, e);
				}
			}
			else {
				msg = 0;
				log.error("/bk/info/del?id=" + id, e);
			}
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

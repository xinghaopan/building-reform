package com.cc.buildingReform.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cc.buildingReform.Annotation.Permissions;
import com.cc.buildingReform.Common.Common;
import com.cc.buildingReform.form.Department;
import com.cc.buildingReform.form.Quota;
import com.cc.buildingReform.form.User;
import com.cc.buildingReform.service.DepartmentService;
import com.cc.buildingReform.service.DicService;
import com.cc.buildingReform.service.QuotaService;

@RestController
public class QuotaController {
	@Autowired
	private QuotaService quotaService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private DicService dicService;

	private static Logger log = LoggerFactory.getLogger(QuotaController.class);
	
	/**
	 * 列表页面 2015-07-30 by p
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "/index")
	@RequestMapping("/bk/quota/list/{mid}")
	public String list(@PathVariable("mid") Integer mid, 
			@RequestParam(value = "year", required = false) Integer year, 
			@RequestParam(value = "fatherId", required = false) String fatherId, 
			HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		try {
			User user = (User) request.getSession().getAttribute("loginUser");
			
			if (year == null) {
				year = Quota.getCurrentYear();
			}
			
			if (fatherId == null || fatherId == "") {
				fatherId = user.getDepartmentId();
				model.addAttribute("list", quotaService.findByDepartmentId(year, fatherId));
			} else {
				model.addAttribute("list", quotaService.findByFatherDepartmentId(year, fatherId));
			}
			
			Department userDepartment = departmentService.findById(user.getDepartmentId());
			if (userDepartment != null && userDepartment.getIsWork() == 1) {
				if (fatherId.equals(user.getDepartmentId())) {
					List<Department> l = departmentService.findByFatherId(fatherId);
					if (l != null && !l.isEmpty() && l.get(0).getId().length() != 10) {
						model.addAttribute("isDistribute", 1);
					}
				}
			}
			
			
			model.addAttribute("mid", mid);
			model.addAttribute("dicList", dicService.findAll());
			model.addAttribute("year", year);
			model.addAttribute("user", user);
		}
		catch(Exception e) {
			log.error("/bk/quota/list", e);
		}
		
		return "/bk/quota/list";
	}

	/**
	 * 修改 2015-07-30 by p
	 * 
	 * @param id
	 * @param mid
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "/index")
	@RequestMapping("/bk/quota/edit/{mid}")
	public String edit(@PathVariable("mid") Integer mid, 
			@RequestParam(value = "id", required = false) Integer id, 
			Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			User user = (User)request.getSession().getAttribute("loginUser");
			Quota quota = new Quota();
			quota.setYear(Quota.getCurrentYear());
			quota.setDepartmentId(user.getDepartmentId());
			quota.setDepartmentName(user.getDepartmentName());
			quota.setNum(0);
			quota.setRestNum(0);
			quota.setDistributeDepartmentId(user.getDepartmentId());
			if (id != null && id != 0) {
				quota = quotaService.findById(id);
			}
			
			model.addAttribute("mid", mid);
			model.addAttribute("quota", quota);
		}
		catch(Exception e) {
			log.error("/bk/quota/edit?id=" + id, e);
		}
		
		return "/bk/quota/add";
	}
	
	/**
	 * 保存 2015-07-30 by p
	 * 
	 * @param mid
	 * @param quota
	 * @param response
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/quota/save/{mid}", method = RequestMethod.POST)
	public void save(@PathVariable("mid") Integer mid, 
			@ModelAttribute("quota") Quota quota, 
			HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			quota.setDate(new Date());
			
			quotaService.save(quota);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/quota/save", e);
			
			if (e.getMessage() != null) {
				if (e.getMessage().equals("-1")) {
					msg = -1;
					log.warn("/bk/quota/save/", "修改后的年度指标不符合规则！！！");
				}
				else if (e.getMessage().equals("-2")) {
					msg = -2;
					log.warn("/bk/quota/save/", "本机构的年度指标已经存在！！！");
				}
				else if (e.getMessage().equals("-3")) {
					msg = -3;
					log.warn("/bk/quota/save/", "本机构的年度指标不存在，无法发放！！！");
				}
				else if (e.getMessage().equals("-4")) {
					msg = -4;
					log.warn("/bk/quota/save/", "修改后本机构指标不够发放下级机构！！！");
				}
				else {
					msg = 0;
					log.error("/bk/quota/save/", e);
				}
			}
			else {
				msg = 0;
				log.error("/bk/quota/save/", e);
			}
		}
		
		Common.print(response, msg);
	}

	/**
	 * 删除 2015-07-30 by p
	 * 
	 * @param mid
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/quota/del/{mid}")
	public void del(@PathVariable("mid") Integer mid, @RequestParam("id") Integer id, HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			quotaService.remove(id);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/quota/del", e);
			
			if (e.getMessage() != null) {
				if (e.getMessage().equals("-1")) {
					msg = -1;
					log.warn("/bk/quota/del/", "指标已分配无法删除！！！");
				}
				else {
					msg = 0;
					log.error("/bk/quota/del/", e);
				}
			}
			else {
				msg = 0;
				log.error("/bk/quota/del/", e);
			}
		}
		Common.print(response, msg);
	}
	
	/**
	 * 指标发放 2016-07-18 by p
	 * 
	 * @param mid
	 * @param response
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/quota/distribute/{mid}")
	public String distribute(@PathVariable("mid") Integer mid, 
			HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		try {
			User user = (User)request.getSession().getAttribute("loginUser");
			
			model.addAttribute("departmentList", departmentService.findWaitDistribute(user));
			model.addAttribute("dicList", dicService.findAll());
			model.addAttribute("mid", mid);
			model.addAttribute("user", user);
			
		} catch (Exception e) {
			log.error("/bk/quota/distribute", e);
		}
		
		return "/bk/quota/distribute";
	}
	
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/quota/distributeSave/{mid}", method = RequestMethod.POST)
	public void distributeSave(@PathVariable("mid") Integer mid, 
			@ModelAttribute("quota") Quota quota, 
			HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		int msg = 1;
		try {
			User user = (User)request.getSession().getAttribute("loginUser");
			
			quota.setDate(new Date());
			
			quotaService.saveDistribute(quota, user);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/quota/distributeSave", e);
			
			if (e.getMessage() != null) {
				if (e.getMessage().equals("-1")) {
					msg = -1;
					log.warn("/bk/quota/distributeSave/", "本单位剩余指标不够此次发放！！！");
				}
				if (e.getMessage().equals("-2")) {
					msg = -2;
					log.warn("/bk/quota/distributeSave/", "下发单位的指标已经存在！！！");
				}
				else {
					msg = 0;
					log.error("/bk/quota/distributeSave/", e);
				}
			}
			else {
				msg = 0;
				log.error("/bk/quota/distributeSave/", e);
			}
		}
		
		Common.print(response, msg);
	}
}

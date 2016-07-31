package com.cc.buildingReform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cc.buildingReform.Annotation.Permissions;
import com.cc.buildingReform.form.Quota;
import com.cc.buildingReform.form.User;
import com.cc.buildingReform.service.DicService;
import com.cc.buildingReform.service.ViewStateService;


@RestController
public class ViewStateController {
	@Autowired
	private ViewStateService viewStateService;

	@Autowired
	private DicService dicService;

	private static Logger log = LoggerFactory.getLogger(ViewStateController.class);
	
	/**
	 * 列表页面 2015-07-30 by p
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "/index")
	@RequestMapping("/bk/viewState/list/{mid}")
	public String list(@PathVariable("mid") Integer mid, 
			@RequestParam(value = "year", required = false) Integer year, 
			@RequestParam(value = "fatherId", required = false) String fatherId,
			HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		try {
			User user = (User)request.getSession().getAttribute("loginUser");
			
			if (fatherId == null || fatherId == "") {
				fatherId = user.getDepartmentId();
			}
			
			if (year == null || year == 0) {
				year = Quota.getCurrentYear();
			}
			
			model.addAttribute("mid", mid);
			
			model.addAttribute("list", viewStateService.findByFatherId(year, fatherId));
			model.addAttribute("dicList", dicService.findAll());
			model.addAttribute("fatherId", fatherId);
			model.addAttribute("year", year);
		}
		catch(Exception e) {
			log.error("/bk/viewState/list", e);
		}
		
		return "/bk/viewState/list";
	}

}

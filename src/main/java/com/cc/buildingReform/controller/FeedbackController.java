package com.cc.buildingReform.controller;

import java.util.Date;

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
import com.cc.buildingReform.form.Feedback;
import com.cc.buildingReform.form.User;
import com.cc.buildingReform.service.FeedbackService;

@RestController
public class FeedbackController {
	@Autowired
	private FeedbackService feedbackService;


	private static Logger log = LoggerFactory.getLogger(FeedbackController.class);
	
	/**
	 * 普通用户的反馈信息列表页面 2016-08-01 by p
	 * 
	 * @param mid
	 * @param currentPage
	 * @param count
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "/index")
	@RequestMapping("/bk/feedback/list/{mid}")
	public String list(@PathVariable("mid") Integer mid, 
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "count", required = false) Integer count,
			HttpServletRequest request, HttpServletResponse response,  Model model) throws Exception {
		try {
			User user = (User) request.getSession().getAttribute("loginUser");
			
			if (currentPage == null) {
				currentPage = 0;
			}
			
			if (count == null || count == 0) {
				count = 10;
			}
			
			int maxCount = feedbackService.getCountByUserId(user.getId());
			int maxPage = (maxCount - 1) / count + 1;
			model.addAttribute("count", count);
			model.addAttribute("maxPage", maxPage);
			model.addAttribute("mid", mid);
			
			model.addAttribute("list", feedbackService.findByUserId(user.getId(), currentPage * count, count));
			model.addAttribute("pages", Common.pages(mid, currentPage, maxPage, "", ""));
		}
		catch(Exception e) {
			log.error("/bk/feedback/list", e);
		}
		
		return "/bk/feedback/list";
	}

	@Permissions(target = "loginUser", url = "/index")
	@RequestMapping("/bk/feedback/listAll/{mid}")
	public String listAll(@PathVariable("mid") Integer mid, 
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "count", required = false) Integer count,
			HttpServletRequest request, HttpServletResponse response,  Model model) throws Exception {
		try {
			if (currentPage == null) {
				currentPage = 0;
			}
			
			if (count == null || count == 0) {
				count = 10;
			}
			
			int maxCount = feedbackService.getCount();
			int maxPage = (maxCount - 1) / count + 1;
			model.addAttribute("count", count);
			model.addAttribute("maxPage", maxPage);
			model.addAttribute("mid", mid);
			
			model.addAttribute("list", feedbackService.findAll(currentPage * count, count));
			model.addAttribute("pages", Common.pages(mid, currentPage, maxPage, "", ""));
		}
		catch(Exception e) {
			log.error("/bk/feedback/listAll", e);
		}
		
		return "/bk/feedback/listAll";
	}
	
	@Permissions(target = "loginUser", url = "/index")
	@RequestMapping("/bk/feedback/add/{mid}")
	public String add(@PathVariable("mid") Integer mid, Model model) throws Exception {
		try {
			Feedback feedback = new Feedback();
			model.addAttribute("mid", mid);
			model.addAttribute("feedback", feedback);
		}
		catch(Exception e) {
			log.error("/bk/feedback/add", e);
		}
		
		return "/bk/feedback/add";
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
	@RequestMapping("/bk/feedback/edit/{mid}")
	public String edit(@PathVariable("mid") Integer mid, 
			@RequestParam("id") Integer id, 
			Model model) throws Exception {
		try {
			Feedback feedback = feedbackService.findById(id);
			
			model.addAttribute("mid", mid);
			model.addAttribute("feedback", feedback);
		}
		catch(Exception e) {
			log.error("/bk/feedback/edit?id=" + id, e);
		}
		
		return "/bk/feedback/edit";
	}
	
	/**
	 * 添加反馈信息 2016-08-01 by p
	 * 
	 * @param mid
	 * @param feedback
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/feedback/saveAdd/{mid}", method = RequestMethod.POST)
	public void saveAdd(@PathVariable("mid") Integer mid, 
			@ModelAttribute("feedback") Feedback feedback, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			User user = (User) request.getSession().getAttribute("loginUser");
			feedback.setDepartmentId(user.getDepartmentId());
			feedback.setDepartmentName(user.getDepartmentName());
			feedback.setUserId(user.getId());
			feedback.setUserName(user.getTrueName());
			feedback.setDate(new Date());
			feedback.setState(0);
			feedbackService.save(feedback);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/feedback/save", e);
		}
		
		Common.print(response, msg);
	}

	/**
	 * 回复反馈信息 2016-08-01 by p
	 * 
	 * @param mid
	 * @param feedback
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/feedback/saveEdit/{mid}", method = RequestMethod.POST)
	public void saveEdit(@PathVariable("mid") Integer mid, 
			@ModelAttribute("feedback") Feedback feedback, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			User user = (User) request.getSession().getAttribute("loginUser");
			Feedback old = feedbackService.findById(feedback.getId());
			old.setReplyContent(feedback.getReplyContent());
			old.setReplyDepartmentId(user.getDepartmentId());
			old.setReplyDepartmentName(user.getDepartmentName());
			old.setReplyUserId(user.getId());
			old.setReplyUserName(user.getTrueName());
			old.setReplyDate(new Date());
			old.setState(1);
			feedbackService.save(old);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/feedback/save", e);
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
	@RequestMapping(value = "/bk/feedback/del/{mid}")
	public void del(@PathVariable("mid") Integer mid, @RequestParam("id") Integer id, HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			feedbackService.remove(id);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/feedback/del?id=" + id, e);
		}
		
		Common.print(response, msg);
	}
}

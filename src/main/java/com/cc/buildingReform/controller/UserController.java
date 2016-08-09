package com.cc.buildingReform.controller;


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
import com.cc.buildingReform.Common.ServerProperty;
import com.cc.buildingReform.form.Department;
import com.cc.buildingReform.form.User;
import com.cc.buildingReform.service.DepartmentService;
import com.cc.buildingReform.service.RoleService;
import com.cc.buildingReform.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ServerProperty serverProperty;
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * 列表页面 2015-08-27 by p
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "/index")
	@RequestMapping("/bk/user/list/{mid}")
	public String list(@PathVariable("mid") Integer mid, 
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "count", required = false) Integer count,
			@RequestParam(value = "roleId", required = false) Integer roleId,
			@RequestParam(value = "departmentId", required = false) String departmentId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "trueName", required = false) String trueName,
			Model model) throws Exception {
		try {
			if (currentPage == null) {
				currentPage = 0;
			}
			
			if (count == null || count == 0) {
				count = 10;
			}
			
			int maxCount = userService.getCount(roleId, departmentId, name, trueName);
			int maxPage = (maxCount - 1) / count + 1;
			model.addAttribute("count", count);
			model.addAttribute("maxPage", maxPage);
			
			model.addAttribute("mid", mid);
			model.addAttribute("list", userService.search(roleId, departmentId, name, trueName, currentPage * count, count));
			model.addAttribute("pages", Common.pages(mid, currentPage, maxPage, "", ""));
			model.addAttribute("roleId", roleId);
			model.addAttribute("departmentId", departmentId);
			model.addAttribute("name", name);
			model.addAttribute("trueName", trueName);
			model.addAttribute("roleList", roleService.findAll());
			model.addAttribute("password", serverProperty.getUserPassword());
		}
		catch(Exception e) {
			log.error("/bk/user/list", e);
		}
		
		return "/bk/user/list";
	}

	/**
	 * 修改 2015-08-27 by p
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "/index")
	@RequestMapping("/bk/user/edit/{mid}")
	public String edit(@PathVariable("mid") Integer mid, 
			@RequestParam(value = "id", required = false) Integer id, 
			Model model) throws Exception {
		try {
			User user = new User();
			if (id != null && id != 0) {
				user = userService.findById(id);
			}
			model.addAttribute("mid", mid);
			model.addAttribute("user", user);
			model.addAttribute("password", serverProperty.getUserPassword());
			model.addAttribute("roleList", roleService.findAll());
		}
		catch(Exception e) {
			log.error("/bk/user/edit" + "?id=" + id, e);
		}
		
		return "/bk/user/add";
	}
	
	/**
	 * 保存 2015-08-27 by p
	 * 
	 * @param user
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/user/save/{mid}", method = RequestMethod.POST)
	public void save(@PathVariable("mid") Integer mid, @ModelAttribute("user") User user, 
			HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			User u = userService.findByName(user.getId(), user.getName());
			if (u == null) {
				if (user.getId() == null) {
					user.setPassword(Common.MD5(serverProperty.getUserPassword()));
				}
				
				Department department = departmentService.findById(user.getDepartmentId());
				
				if (department != null) {
					userService.save(user);
				}
				else {
					msg = -2;
				}
			}
			else {
				msg = -1;
			}
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/user/save", e);
		}
		
		Common.print(response, msg);
	}

	/**
	 * 删除 2015-08-27 by p
	 * 
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/user/del/{mid}")
	public void del(@PathVariable("mid") Integer mid, @RequestParam(value = "id", required = false) Integer id, 
			HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			userService.remove(id);
		} catch (Exception e) {
			msg = 0;
			log.error("/user/del?id=" + id, e);
		}
		
		Common.print(response, msg);
	}
	
	/**
	 * 初始化用户密�? 2015-08-27 by p
	 * 
	 * @param mid
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "login")
	@RequestMapping(value = "/bk/user/initPassword/{mid}")
	public void initPassword(@PathVariable("mid") Integer mid, @RequestParam("id") Integer id, 
			HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			User user = userService.findById(id);
			if (user != null) {
				user.setPassword(Common.MD5(serverProperty.getUserPassword()));
				userService.save(user);
			}
			else {
				msg = -1;
			}
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/user/initPassword?id=" + id, e);
		}
		
		Common.print(response, msg);
	}
	
	/**
	 * 用户登录 2015-08-28 by p
	 * 
	 * @param name
	 * @param password
	 * @param codes
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/bk/user/login")
	public void login(@RequestParam("name") String name,
			@RequestParam("password") String password, @RequestParam(value = "codes", required = false) String codes,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			String kaptchaExpected = (String) request.getSession().getAttribute(
					com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			if (codes != null && (codes.equals(kaptchaExpected))) {
				User user = userService.findByName(0, name);
				if (user == null) {
					msg = -1;
				} else if (!Common.MD5(password).equals(user.getPassword())) {
					msg = -2;
				} else {
					request.getSession().setAttribute("loginUser", user);
				}
			}
			else {
				msg = -3;
			}
			
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/user/login", e);
		}
		
		Common.print(response, msg);
	}
	
	@Permissions(target = "loginUser", url = "login")
	@RequestMapping("/bk/user/updatePassword/{mid}")
	public String updatePassword(@PathVariable("mid") Integer mid, 
			Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			User user = (User)request.getSession().getAttribute("loginUser");
			model.addAttribute("mid", mid);
			model.addAttribute("user", user);
		}
		catch(Exception e) {
			log.error("/bk/user/updatePassword", e.getMessage());
		}
		
		return "/bk/user/updatePassword";
	}
	
	@Permissions(target = "loginUser", url = "login")
	@RequestMapping(value = "/bk/user/savePassword/{mid}")
	public void savePassword(@PathVariable("mid") Integer mid, 
			@RequestParam(value = "opassword", required = false) String opassword,
			@RequestParam(value = "npassword", required = false) String npassword,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			User u = (User)request.getSession().getAttribute("loginUser");
			User user = userService.findById(u.getId());
			if (!user.getPassword().equals(Common.MD5(opassword))) {
				msg = -1;
			}
			else {
				user.setPassword(Common.MD5(npassword));
				userService.save(user);
			}
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/user/savePassword", e.getMessage());
		}
		
		Common.print(response, msg);
	}
	
	/**
	 * 用户登出 2015-08-28 by p
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/bk/user/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			request.getSession().invalidate();
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/user/logout", e);
		}
		
		Common.print(response, msg);
	}
}

package com.cc.buildingReform.controller;


import com.cc.buildingReform.Annotation.Permissions;
import com.cc.buildingReform.Common.Common;
import com.cc.buildingReform.Common.ServerProperty;
import com.cc.buildingReform.form.Department;
import com.cc.buildingReform.form.Dic;
import com.cc.buildingReform.form.ResultMessage;
import com.cc.buildingReform.form.User;
import com.cc.buildingReform.service.DepartmentService;
import com.cc.buildingReform.service.DicService;
import com.cc.buildingReform.service.RoleService;
import com.cc.buildingReform.service.UserService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@RestController
public class LoginController {
	@Autowired
	private UserService userService;

	@Autowired
	private DicService dicService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ServerProperty serverProperty;
	
	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * 用户登录 2015-08-28 by p
	 * 
	 * @param name
	 * @param password
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/rest/login", method = RequestMethod.GET)
	public @ResponseBody ResultMessage login(@RequestParam("name") String name, @RequestParam("password") String password,
						HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultMessage result = new ResultMessage();

		try {
			if (name.isEmpty()) {
				return result.setMessage("登录用户名不能为空！");
			}

			if (password.isEmpty()) {
				return result.setMessage("密码不能为空！");
			}

			User user = userService.findByName(0, name);
			if (user == null) {
				return result.setMessage("用户名不存在！");
			}

			// TODO 身份锁登陆，待定
//			if (!user.getKey().isEmpty()) {
//				return result.setMessage("请用身份锁登录！");
//			}

			if (!Objects.equals(Common.MD5(password), user.getPassword())) {
				return result.setMessage("密码错误！");
			}

			List<Dic> dicList = dicService.findAll();
			JSONObject userJ = JSONObject.fromObject(user);
			JSONObject roleJ = JSONObject.fromObject(user.getRole());
			userJ.remove("password");
			userJ.remove("order");
			userJ.remove("role");
			userJ.remove("key");
			userJ.remove("state");

			result.getData().put("user", userJ);
			result.getData().put("dics", dicList);
		} catch (Exception e) {
			return result.setMessage("未知错误！").setDetail(e.getMessage());
		}
		
		return result;
	}
	

}

package com.cc.buildingReform.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.cc.buildingReform.form.Menu;
import com.cc.buildingReform.form.User;
import com.cc.buildingReform.service.MenuService;

@RestController
public class MenuController {
	@Autowired
	private MenuService menuService;

	private static Logger log = LoggerFactory.getLogger(MenuController.class);
	
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/menu/listByJSon")
	public void listByJSon(@RequestParam(value = "mid", required = false) Integer mid,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User user = (User)request.getSession().getAttribute("loginUser");
			map.put("menu", menuService.findFather(mid));
			map.put("list", menuService.findAll().stream().filter(c -> user.getRole().getPower().contains("," + c.getId() + ",")).collect(Collectors.toList()));
		} catch (Exception e) {
			log.error("/bk/menu/listByJSon", e);
		}
		
		Common.WriteJSON(response, map);
	}
	
	/**
	 * 列表页面 2015-07-30 by p
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "/bk/login")
	@RequestMapping("/bk/menu/list/{mid}")
	public String list(@PathVariable("mid") Integer mid, @RequestParam(value = "fatherId", required = false) Integer fatherId, Model model) throws Exception {
		try {
			if (fatherId == null) {
				fatherId = 0;
			}
			model.addAttribute("mid", mid);
			model.addAttribute("fatherId", fatherId);
			model.addAttribute("list", menuService.findByFatherId(fatherId));
		}
		catch(Exception e) {
			log.error("/bk/menu/list", e);
		}
		
		return "/bk/menu/list";
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
	@Permissions(target = "loginUser", url = "/bk/login")
	@RequestMapping("/bk/menu/edit/{mid}")
	public String edit(@PathVariable("mid") Integer mid, @RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "fatherId", required = false) Integer fatherId, Model model) throws Exception {
		try {
			String fatherName = "顶层";
			if (fatherId == null) {
				fatherId = 0;
			}
			Menu menu = new Menu();
			menu.setFatherId(fatherId);
			menu.setIsSubject(0);
			menu.setIsNavigation(0);
			menu.setOrder(0);
			if (id != null && id != 0) {
				menu = menuService.findById(id);
			}
			
			if (fatherId != 0) {
				fatherName = menuService.findById(fatherId).getBackName();
			}
			model.addAttribute("mid", mid);
			model.addAttribute("fatherName", fatherName);
			model.addAttribute("menu", menu);
		}
		catch(Exception e) {
			log.error("/bk/menu/edit?id=" + id, e);
		}
		
		return "/bk/menu/add";
	}
	
	/**
	 * 保存 2015-07-30 by p
	 * 
	 * @param mid
	 * @param menu
	 * @param response
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/menu/save/{mid}", method = RequestMethod.POST)
	public void save(@PathVariable("mid") Integer mid, @ModelAttribute("menu") Menu menu, HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			menuService.save(menu);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/menu/save", e);
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
	@RequestMapping(value = "/bk/menu/del/{mid}")
	public void del(@PathVariable("mid") Integer mid, @RequestParam("id") Integer id, HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			menuService.remove(id);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/menu/del?id=" + id, e);
		}
		
		Common.print(response, msg);
	}
}

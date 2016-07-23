package com.cc.buildingReform.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.cc.buildingReform.form.Role;
import com.cc.buildingReform.form.Tree;
import com.cc.buildingReform.service.MenuService;
import com.cc.buildingReform.service.RoleService;

import net.sf.json.JSONArray;

@RestController
public class RoleController {
	@Autowired
	private RoleService roleService;

	@Autowired
	private MenuService menuService;

	private static Logger log = LoggerFactory.getLogger(RoleController.class);
	
	/**
	 * 列表页面 2015-08-27 by p
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "/index")
	@RequestMapping("/bk/role/list/{mid}")
	public String list(@PathVariable("mid") Integer mid, 
			Model model) throws Exception {
		try {
			model.addAttribute("mid", mid);
			model.addAttribute("list", roleService.findAll());
		}
		catch(Exception e) {
			log.error("/bk/role/list", e);
		}
		
		return "/bk/role/list";
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
	@RequestMapping("/bk/role/edit/{mid}")
	public String edit(@PathVariable("mid") Integer mid, 
			@RequestParam(value = "id", required = false) Integer id, 
			Model model) throws Exception {
		try {
			Role role = new Role();
			if (id != null && id != 0) {
				role = roleService.findById(id);
			}
			model.addAttribute("mid", mid);
			model.addAttribute("role", role);
			List<Menu> lists = menuService.findAll();
			List<Tree> treeList = new ArrayList<>();
			for (int i = 0; i < lists.size(); i ++) {
				Tree tree = new Tree();
				tree.setId(lists.get(i).getId().toString());
				tree.setpId(lists.get(i).getFatherId().toString());
				tree.setName("<div class='inline' >" + lists.get(i).getBackName() + "</div>");
				tree.setOpen(true);
				if (role.getPower() != null && role.getPower().contains("," + lists.get(i).getId() + ",")) {
					tree.setChecked(true);
				}
				treeList.add(tree);
			}
			model.addAttribute("menuList", JSONArray.fromObject(treeList));
		}
		catch(Exception e) {
			log.error("/bk/role/edit?id=" + id, e);
		}
		
		return "/bk/role/add";
	}
	
	/**
	 * 保存 2015-08-27 by p
	 * 
	 * @param role
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/role/save/{mid}", method = RequestMethod.POST)
	public void save(@PathVariable("mid") Integer mid, @ModelAttribute("role") Role role, 
			HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			roleService.save(role);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/role/save", e);
		}
		
		Common.print(response, msg);
	}

	/**
	 * 删除 2015-08-27 by p
	 * 
	 * @param id
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/role/del/{mid}")
	public void del(@PathVariable("mid") Integer mid, @RequestParam("id") Integer id, Model model, 
			HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			roleService.remove(id);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/role/del?id=" + id, e);
		}
		
		Common.print(response, msg);
	}
}

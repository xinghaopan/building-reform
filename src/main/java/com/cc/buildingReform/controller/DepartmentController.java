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
import com.cc.buildingReform.form.Department;
import com.cc.buildingReform.form.Tree;
import com.cc.buildingReform.service.DepartmentService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;

	private static Logger log = LoggerFactory.getLogger(DepartmentController.class);
	
	/**
	 * 列表页面 2015-07-30 by p
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "/index")
	@RequestMapping("/bk/department/list/{mid}")
	public String list(@PathVariable("mid") Integer mid, @RequestParam(value = "fatherId", required = false) String fatherId, Model model) throws Exception {
		try {
			if (fatherId == null) {
				fatherId = "01";
			}
			model.addAttribute("mid", mid);
			model.addAttribute("fatherId", fatherId);
			List<Department> lists = departmentService.findAll();
			List<Tree> treeList = new ArrayList<>();
			for (int i = 0; i < lists.size(); i ++) {
				Tree tree = new Tree();
				tree.setId(lists.get(i).getId());
				tree.setpId(lists.get(i).getFatherId());
				tree.setName("<div class='inline' >" + lists.get(i).getName() + "</div>" + "<div class='inline' >" + lists.get(i).getId() + "</div>");
				treeList.add(tree);
			}
			model.addAttribute("list", JSONArray.fromObject(treeList));
		}
		catch(Exception e) {
			log.error("/bk/department/list", e);
		}
		
		return "/bk/department/list";
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
	@RequestMapping("/bk/department/edit/{mid}")
	public String edit(@PathVariable("mid") Integer mid, 
			@RequestParam(value = "id", required = false) String id, 
			@RequestParam(value = "fatherId", required = false) String fatherId, 
			Model model) throws Exception {
		try {
			String fatherName = "顶层";
			if (fatherId == null) {
				fatherId = "00";
			}
			Department department = new Department();
			department.setFatherId(fatherId);
			department.setOrder(0);
			if (id != null && id != "") {
				department = departmentService.findById(id);
			}
			
			if (fatherId != "" && !fatherId.equals("00")) {
				fatherName = departmentService.findById(fatherId).getName();
			}
			
			model.addAttribute("mid", mid);
			model.addAttribute("fatherName", fatherName);
			model.addAttribute("department", department);
		}
		catch(Exception e) {
			log.error("/bk/department/edit?id=" + id, e);
		}
		
		return "/bk/department/add";
	}
	
	/**
	 * 保存 2015-07-30 by p
	 * 
	 * @param mid
	 * @param department
	 * @param response
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/department/save/{mid}", method = RequestMethod.POST)
	public void save(@PathVariable("mid") Integer mid, 
			@ModelAttribute("department") Department department, 
			HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			departmentService.save(department);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/department/save", e);
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
	@RequestMapping(value = "/bk/department/del/{mid}")
	public void del(@PathVariable("mid") Integer mid, @RequestParam("id") String id, HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			departmentService.remove(id);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/department/del?id=" + id, e);
		}
		
		Common.print(response, msg);
	}
}

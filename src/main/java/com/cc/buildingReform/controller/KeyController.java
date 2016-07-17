package com.cc.buildingReform.controller;

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
import com.cc.buildingReform.form.Key;
import com.cc.buildingReform.service.KeyService;

@RestController
public class KeyController {
	@Autowired
	private KeyService keyService;

	private static Logger log = LoggerFactory.getLogger(KeyController.class);
	
	/**
	 * 列表页面 2015-07-30 by p
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "/index")
	@RequestMapping("/bk/key/list/{mid}")
	public String list(@PathVariable("mid") Integer mid, Model model) throws Exception {
		try {
			model.addAttribute("mid", mid);
			model.addAttribute("list", keyService.findAll());
		}
		catch(Exception e) {
			log.error("/bk/key/list", e);
		}
		
		return "/bk/key/list";
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
	@RequestMapping("/bk/key/edit/{mid}")
	public String edit(@PathVariable("mid") Integer mid, 
			@RequestParam(value = "id", required = false) Integer id, 
			Model model) throws Exception {
		try {
			Key key = new Key();
			if (id != null && id != 0) {
				key = keyService.findById(id);
			}
			
			model.addAttribute("mid", mid);
			model.addAttribute("key", key);
		}
		catch(Exception e) {
			log.error("/bk/key/edit?id=" + id, e);
		}
		
		return "/bk/key/add";
	}
	
	/**
	 * 保存 2015-07-30 by p
	 * 
	 * @param mid
	 * @param key
	 * @param response
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/key/save/{mid}", method = RequestMethod.POST)
	public void save(@PathVariable("mid") Integer mid, 
			@ModelAttribute("key") Key key, 
			HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			keyService.save(key);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/key/save", e);
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
	@RequestMapping(value = "/bk/key/del/{mid}")
	public void del(@PathVariable("mid") Integer mid, @RequestParam("id") Integer id, HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			keyService.remove(id);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/key/del?id=" + id, e);
		}
		
		Common.print(response, msg);
	}
}

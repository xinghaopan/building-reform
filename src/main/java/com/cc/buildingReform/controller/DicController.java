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
import com.cc.buildingReform.form.Dic;
import com.cc.buildingReform.service.DicService;
import com.cc.buildingReform.service.KeyService;

@RestController
public class DicController {
	@Autowired
	private DicService dicService;

	@Autowired
	private KeyService keyService;

	private static Logger log = LoggerFactory.getLogger(DicController.class);
	
	/**
	 * 列表页面 2015-07-30 by p
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "/index")
	@RequestMapping("/bk/dic/list/{mid}")
	public String list(@PathVariable("mid") Integer mid, 
			@RequestParam(value = "keyId", required = false) Integer keyId, Model model) throws Exception {
		try {
			
			model.addAttribute("mid", mid);
			model.addAttribute("keyId", keyId);
			
			if (keyId == null) {
				model.addAttribute("list", dicService.findAll());
			} else {
				model.addAttribute("list", dicService.findByKeyId(keyId));
			}
			
			model.addAttribute("keyList", keyService.findAll());
		}
		catch(Exception e) {
			log.error("/bk/dic/list", e);
		}
		
		return "/bk/dic/list";
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
	@RequestMapping("/bk/dic/edit/{mid}")
	public String edit(@PathVariable("mid") Integer mid, 
			@RequestParam(value = "id", required = false) Integer id, 
			Model model) throws Exception {
		try {
			Dic dic = new Dic();
			if (id != null && id != 0) {
				dic = dicService.findById(id);
			}
			
			model.addAttribute("mid", mid);
			model.addAttribute("dic", dic);
			model.addAttribute("keyList", keyService.findAll());
		}
		catch(Exception e) {
			log.error("/bk/dic/edit?id=" + id, e);
		}
		
		return "/bk/dic/add";
	}
	
	/**
	 * 保存 2015-07-30 by p
	 * 
	 * @param mid
	 * @param dic
	 * @param response
	 * @throws Exception
	 */
	@Permissions(target = "loginUser", url = "")
	@RequestMapping(value = "/bk/dic/save/{mid}", method = RequestMethod.POST)
	public void save(@PathVariable("mid") Integer mid, 
			@ModelAttribute("dic") Dic dic, 
			HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			dicService.save(dic);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/dic/save", e);
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
	@RequestMapping(value = "/bk/dic/del/{mid}")
	public void del(@PathVariable("mid") Integer mid, @RequestParam("id") Integer id, HttpServletResponse response) throws Exception {
		int msg = 1;
		try {
			dicService.remove(id);
		} catch (Exception e) {
			msg = 0;
			log.error("/bk/dic/del?id=" + id, e);
		}
		
		Common.print(response, msg);
	}
}

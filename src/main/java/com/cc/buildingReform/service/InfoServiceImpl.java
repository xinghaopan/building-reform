package com.cc.buildingReform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.buildingReform.dao.InfoDAO;
import com.cc.buildingReform.dao.QuotaDAO;
import com.cc.buildingReform.form.Info;
import com.cc.buildingReform.form.Quota;

@Service
@Transactional
public class InfoServiceImpl implements InfoService {

	@Autowired
	private InfoDAO infoDAO;
	
	@Autowired
	private QuotaDAO quotaDAO;
	
	public void save(Info info) {
		if (info == null || info.getId() == null || info.getId() == 0) {
			// 校验当前机构在指定的计划年度是否还有剩余指标
			List<Quota> list =quotaDAO.findByDepartmentId(info.getPlanYear(), info.getDepartmentId());
			if (list == null || list.isEmpty() || list.get(0).getRestNum() == 0) {
				throw new RuntimeException("-1");
			}
			
			// 剩余指标 - 1
			list.get(0).setRestNum(list.get(0).getRestNum() - 1);
			
			quotaDAO.saveOrUpdate(list.get(0));
			
		}
		
		// 保存上报信息，无论新增还是修改，都将状态置为 编辑状态
		info.setState(Info.STATE_EDIT);
		
		infoDAO.saveOrUpdate(info);
	}

	public void remove(Integer id) {
		infoDAO.delete(id);
	}
	
	public Info findById(Integer id) {
		return infoDAO.get(id);
	}
	
	public List<Info> findAll() {
		return infoDAO.findAll();
	}
	
	public int getCount() {
		return infoDAO.getCount();
	}
	
	public List<Info> findAll(int firstResult, int maxResult) {
		return infoDAO.findAll(firstResult, maxResult);
	}
}

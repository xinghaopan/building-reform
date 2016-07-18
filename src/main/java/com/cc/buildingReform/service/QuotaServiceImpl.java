package com.cc.buildingReform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.buildingReform.dao.QuotaDAO;
import com.cc.buildingReform.form.Quota;
import com.cc.buildingReform.form.User;

@Service
@Transactional
public class QuotaServiceImpl implements QuotaService {

	@Autowired
	private QuotaDAO quotaDAO;

	public void save(Quota quota) {
		if (quota.getId() != null && quota.getId() != 0) {
			// 校验更改后的指标数量和剩余数量是否符合规则
			Quota oldQuota = quotaDAO.get(quota.getId());
			Integer restNum = quota.getNum() - (oldQuota.getNum() - oldQuota.getRestNum());
			if (restNum < 0) {
				throw new RuntimeException("-1");
			}
			
			oldQuota.setNum(quota.getNum());
			oldQuota.setRestNum(restNum);
			oldQuota.setDate(quota.getDate());
			
			quotaDAO.update(oldQuota);
			
		} else {
			// 校验年度、机构是否已经存在指标信息
			if (quotaDAO.findByDepartmentId(quota.getYear(), quota.getDepartmentId()).size() > 0) {
				throw new RuntimeException("-2");
			}
			
			quota.setRestNum(quota.getNum());
			
			quotaDAO.save(quota);
		}
	}

	public void saveDistribute(Quota quota, User user) {
		List<Quota> list = quotaDAO.findByDepartmentId(quota.getYear(), user.getDepartmentId());
		if (list != null && !list.isEmpty()) {
			// 校验本单位的剩余指标是否够此次发放
			if (list.get(0).getRestNum() < quota.getNum()) {
				throw new RuntimeException("-1");
			}
			
			list.get(0).setRestNum(list.get(0).getRestNum() - quota.getNum());
			quotaDAO.saveOrUpdate(list.get(0));
			
			quota.setRestNum(quota.getNum());
			
			quotaDAO.saveOrUpdate(quota);
		}
	}
	
	public void remove(Integer id) {
		// TODO 删除前校验此项年度指标是否已经使用，如果使用不允许删除 
		
		quotaDAO.delete(id);
	}
	
	public Quota findById(Integer id) {
		return quotaDAO.get(id);
	}

	public List<Quota> findAll(Integer year) {
		return quotaDAO.findAll(year);
	}
	
	public int getCount(Integer year) {
		return quotaDAO.getCount(year);
	}
	
	public List<Quota> findAll(Integer year, int firstResult, int maxResult) {
		return quotaDAO.findAll(year, firstResult, maxResult);
	}
	
	public List<Quota> findByDepartmentId(Integer year, String departmentId) {
		return quotaDAO.findByDepartmentId(year, departmentId);
	}
	
	public List<Integer> findExistYear() {
		return quotaDAO.findExistYear();
	}
}

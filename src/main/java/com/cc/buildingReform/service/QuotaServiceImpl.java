package com.cc.buildingReform.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.buildingReform.dao.DepartmentDAO;
import com.cc.buildingReform.dao.QuotaDAO;
import com.cc.buildingReform.form.Department;
import com.cc.buildingReform.form.Quota;
import com.cc.buildingReform.form.User;

@Service
@Transactional
public class QuotaServiceImpl implements QuotaService {

	@Autowired
	private QuotaDAO quotaDAO;

	@Autowired
	private DepartmentDAO departmentDAO;

	public void save(Quota quota) {
		if (quota.getId() != null && quota.getId() != 0) {
			// 校验更改后的指标数量和剩余数量是否符合规则
			Quota oldQuota = quotaDAO.get(quota.getId());
			Integer restNum = quota.getNum() - (oldQuota.getNum() - oldQuota.getRestNum());
			if (restNum < 0) {
				throw new RuntimeException("-1");
			}
			
			// 不是省厅用户修改自己的指标
			if (!quota.getDepartmentId().equals("01")) {
				// 校验 新修改的下属机构指标 和自己剩余指标数量是否匹配
				List<Quota> list = quotaDAO.findByDepartmentId(oldQuota.getYear(), oldQuota.getDistributeDepartmentId());
				if (list == null || list.isEmpty()) {
					throw new RuntimeException("-3");
				}
				
				Integer selfRestNum = list.get(0).getRestNum() - (quota.getNum() - oldQuota.getNum());
				if (selfRestNum < 0) {
					throw new RuntimeException("-4");
				}
				
				list.get(0).setRestNum(selfRestNum);
				
				quotaDAO.update(list.get(0));
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
			
			// 本单位指标 - 1
			list.get(0).setRestNum(list.get(0).getRestNum() - quota.getNum());
			quotaDAO.saveOrUpdate(list.get(0));
			
			quota.setRestNum(quota.getNum());
			quota.setDistributeDepartmentId(user.getDepartmentId());
			quotaDAO.saveOrUpdate(quota);
		}
	}
	
	public void remove(Integer id) {
		Quota quota = quotaDAO.get(id);
		if (!quota.getNum().equals(quota.getRestNum())) {
			throw new RuntimeException("-1");
		}
		
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
	
	public List<Quota> findByFatherDepartmentId(Integer year, String fatherDepartmentId) {
		Department department = departmentDAO.get(fatherDepartmentId);
		
		if (department.getIsWork() == 1) {
			// 对于处理业务的机构，有上级下发的指标，同时也有给下级下发的指标
			return quotaDAO.findByFatherDepartmentId(year, fatherDepartmentId);
		} else {
			// 对于不处理业务的机构，没有上级下发的指标，但下级肯定会有指标
			return quotaDAO.findByDepartmentId(year, departmentDAO.findByFatherId(fatherDepartmentId)
					.stream().map(Department::getId).collect(Collectors.toList()));
		}
	}
}

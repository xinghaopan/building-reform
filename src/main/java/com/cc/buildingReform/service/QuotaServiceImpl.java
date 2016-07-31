package com.cc.buildingReform.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.buildingReform.Common.Compare;
import com.cc.buildingReform.dao.DepartmentDAO;
import com.cc.buildingReform.dao.QuotaDAO;
import com.cc.buildingReform.dao.ViewStateDAO;
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

	@Autowired
	private ViewStateDAO viewStateDAO;
	
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
			
			Department department = departmentDAO.get(quota.getDepartmentId());
			quota.setDepartmentIsStatistics(department.getIsStatistics());
			
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
			
			Department department = departmentDAO.get(quota.getDepartmentId());
			quota.setDepartmentIsStatistics(department.getIsStatistics());
			
			quotaDAO.saveOrUpdate(quota);
		}
	}
	
	public void remove(Integer id) {
		Quota quota = quotaDAO.get(id);
		if (!quota.getNum().equals(quota.getRestNum())) {
			throw new RuntimeException("-1");
		}
		
		// 父指标 余额增加
		List<Quota> list = quotaDAO.findByDepartmentId(quota.getYear(), quota.getDistributeDepartmentId());
		if (list != null && !list.isEmpty()) {
			list.get(0).setRestNum(list.get(0).getRestNum() + quota.getNum());
			quotaDAO.saveOrUpdate(list.get(0));
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
	
	public List<Integer> findExistYear() {
		return quotaDAO.findExistYear();
	}
	
	public List<Quota> findByDepartmentId(Integer year, String fatherDepartmentId) {
		Department department = departmentDAO.get(fatherDepartmentId);
		
		if (department.getIsWork() == 1) {
			// 对于处理业务的机构，只显示自己的指标信息
			
			List<Quota> quotaList = quotaDAO.findByDepartmentId(year, fatherDepartmentId);
			if (quotaList != null && !quotaList.isEmpty()) {
				
				// 先统计自己的各种数量
				List<Object[]> stateList = viewStateDAO.findByFatherId(year, fatherDepartmentId, fatherDepartmentId.length());
				
				for (int i = 0; i < quotaList.size(); i ++) {
					// 查找对应指标的统计信息
					for (int j = 0; j < stateList.size(); j ++) {
						if (quotaList.get(i).getDepartmentId().equals(stateList.get(j)[0])) {
							quotaList.get(i).setBeginNum(Integer.valueOf(String.valueOf(stateList.get(j)[2])));
							quotaList.get(i).setEndNum(Integer.valueOf(String.valueOf(stateList.get(j)[3])));
							quotaList.get(i).setAcceptanceNum(Integer.valueOf(String.valueOf(stateList.get(j)[4])));
							quotaList.get(i).setFundSendNum(Integer.valueOf(String.valueOf(stateList.get(j)[5])));
							
							break;
						}
					}
				}
				
				return quotaList;
			} else {
				return null;
			}
		} else {
			// 对于不处理业务的机构，没有上级下发的指标，但下级肯定会有指标
			
			// 查询子机构
			List<Department> sonList = departmentDAO.findByFatherId(fatherDepartmentId);
			
			if (sonList != null && !sonList.isEmpty()) {
				// 先统计子机构的各种数量
				List<Object[]> stateList = viewStateDAO.findByFatherId(year, fatherDepartmentId, sonList.get(0).getId().length());
				
				List<Quota> quotaList = quotaDAO.findByDepartmentId(year, sonList
						.stream().map(Department::getId).collect(Collectors.toList()));
				
				for (int i = 0; i < quotaList.size(); i ++) {
					// 查找对应指标的统计信息
					for (int j = 0; j < stateList.size(); j ++) {
						if (quotaList.get(i).getDepartmentId().equals(stateList.get(j)[0])) {
							quotaList.get(i).setBeginNum(Integer.valueOf(String.valueOf(stateList.get(j)[2])));
							quotaList.get(i).setEndNum(Integer.valueOf(String.valueOf(stateList.get(j)[3])));
							quotaList.get(i).setAcceptanceNum(Integer.valueOf(String.valueOf(stateList.get(j)[4])));
							quotaList.get(i).setFundSendNum(Integer.valueOf(String.valueOf(stateList.get(j)[5])));
							
							break;
						}
					}

				}
				return quotaList;
			} else {
				return null;
			}
		}
	}
	
	public List<Quota> findByFatherDepartmentId(Integer year, String fatherDepartmentId) {
			
		// 查询子机构
		List<Department> sonList = departmentDAO.findByFatherId(fatherDepartmentId);
		
		if (sonList != null && !sonList.isEmpty()) {
			// 先统计子机构的各种数量
			List<Object[]> stateList = viewStateDAO.findByFatherId(year, fatherDepartmentId, sonList.get(0).getId().length());
			
			List<Quota> quotaList = quotaDAO.findByDepartmentId(year, sonList
					.stream().map(Department::getId).collect(Collectors.toList()));
			
			// 为了兼容省厅进行如下处理，省厅子机构有的处理业务，有的不处理
			// 不处理业务的机构没有指标信息，但还要显示，暂时只显示机构名，数据不做统计，但可以点击，查看子机构的信息
			// 先把这样的子机构添加到指标列表中，并汇总子机构的子机构信息
			for (int i = 0; i < sonList.size(); i ++ ) {
				boolean noFind = true;
				
				for (int j = 0; j < quotaList.size(); j ++) {
					if (quotaList.get(j).getDepartmentId().equals(sonList.get(i).getId())) {
						noFind = false;
						break;
					}
				}
				if (noFind) {
					Quota quota = new Quota();
					quota.setDepartmentId(sonList.get(i).getId());
					quota.setDepartmentName(sonList.get(i).getName());
					quota.setYear(year);
					quotaList.add(quota);
				}
			}
			for (int i = 0; i < quotaList.size(); i ++) {
				// 查找对应指标的统计信息
				for (int j = 0; j < stateList.size(); j ++) {
					if (quotaList.get(i).getDepartmentId().equals(stateList.get(j)[0])) {
						quotaList.get(i).setBeginNum(Integer.valueOf(String.valueOf(stateList.get(j)[2])));
						quotaList.get(i).setEndNum(Integer.valueOf(String.valueOf(stateList.get(j)[3])));
						quotaList.get(i).setAcceptanceNum(Integer.valueOf(String.valueOf(stateList.get(j)[4])));
						quotaList.get(i).setFundSendNum(Integer.valueOf(String.valueOf(stateList.get(j)[5])));
						
						break;
					}
				}
			}
			
			return quotaList;
		} else {
			return null;
		}
		
		
//		Department department = departmentDAO.get(fatherDepartmentId);
//		if (department.getIsWork() == 1) {
//			// 对于处理业务的机构，有上级下发的指标，同时也有给下级下发的指标
//			return quotaDAO.findByFatherDepartmentId(year, fatherDepartmentId);
//		} else {
//			// 对于不处理业务的机构，没有上级下发的指标，但下级肯定会有指标
//			return quotaDAO.findByDepartmentId(year, departmentDAO.findByFatherId(fatherDepartmentId)
//					.stream().map(Department::getId).collect(Collectors.toList()));
//		}
	}
	
	public List<Quota> yearsStatistics(Integer year, Integer num) {
		List<Quota> list = quotaDAO.yearsStatistics(year);
		// 按上报率排序，并取出指定数量
		if (list.size() < num) {
			List<Department> departmentList = departmentDAO.findByIsStatistics(list.stream().map(n -> n.getDepartmentId()).collect(Collectors.toList()), 0, num - list.size());
			for (int i = 0; i < departmentList.size(); i ++) {
				Quota quota = new Quota();
				quota.setDepartmentId(departmentList.get(i).getId());
				quota.setDepartmentName(departmentList.get(i).getName());
				quota.setNum(1);
				quota.setRestNum(1);
				list.add(quota);
			}
		}
		
		list = list.stream()
				.sorted(Compare.<Quota>compare()
						//.thenComparing((a, b) -> getPercent(b.getNum(), b.getRestNum()).compareTo(getPercent(a.getNum(), a.getRestNum())))
						.thenComparing((a, b) -> getPercent(b.getNum(), b.getRestNum()).compareTo(getPercent(a.getNum(), a.getRestNum())))
						.thenComparing((a, b) -> a.getDepartmentId().compareTo(b.getDepartmentId())))
				.skip(0)
				.limit(num)
				.collect(Collectors.toList());
		
		return list;
	}
	
	private Double getPercent(Integer num, Integer restNum) {
		if (num != null && restNum != null && num != 0) {
			return Double.valueOf((num - restNum) * 100 / num);
		} else {
			return 0d;
		}
	}
}

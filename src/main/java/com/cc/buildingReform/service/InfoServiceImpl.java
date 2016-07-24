package com.cc.buildingReform.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.buildingReform.dao.DepartmentDAO;
import com.cc.buildingReform.dao.InfoDAO;
import com.cc.buildingReform.dao.QuotaDAO;
import com.cc.buildingReform.form.Department;
import com.cc.buildingReform.form.Info;
import com.cc.buildingReform.form.Quota;
import com.cc.buildingReform.form.User;

@Service
@Transactional
public class InfoServiceImpl implements InfoService {

	@Autowired
	private InfoDAO infoDAO;
	
	@Autowired
	private QuotaDAO quotaDAO;
	
	@Autowired
	private DepartmentDAO departmentDAO;
	
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
		Info info = infoDAO.get(id);
		// 不能为空
		if (info == null) {
			throw new RuntimeException("-1");
		}
		
		// 状态只能是 <= 编辑状态
		if (info.getState() > Info.STATE_EDIT) {
			throw new RuntimeException("-2");
		}
		
		// 指标信息不能为空
		List<Quota> list = quotaDAO.findByDepartmentId(info.getPlanYear(), info.getDepartmentId());
		if (list == null || list.isEmpty()) {
			throw new RuntimeException("-3");
		}
		
		// 删除上报信息时，本机构指定年度的指标 + 1
		list.get(0).setRestNum(list.get(0).getRestNum() + 1);
		quotaDAO.saveOrUpdate(list.get(0));
		infoDAO.delete(id);
	}
	
	public void submit(User user, Integer id) {
		Info info = infoDAO.get(id);
		// 不能为空
		if (info == null) {
			throw new RuntimeException("-1");	// 提交的信息错误
		}
		
		// 状态只能是 >= 编辑状态 并且 不能是结束状态
		if (info.getState() < Info.STATE_EDIT || info.getState().equals(Info.STATE_OVER)) {
			throw new RuntimeException("-2");	// 信息状态错误
		}
		
		// 编辑状态的上报信息，说明是基层用户提交审核
		if (info.getState().equals(Info.STATE_EDIT)) {
			// 父机构
			Department father = getFatherDepartment(user.getDepartmentId());
			
			if (father == null) {
				throw new RuntimeException("-3");	// 机构错误
			} else {
				// 状态设置为父机构编码长度 * 10
				info.setState(father.getId().length() * 10);
				info.setAuditDepartmentId(father.getId());
			}
		} else {
			// 不是编辑状态，说明是要往上报，可能当前上报信息需要省厅用户审核，省厅用户同意，直接结束
			if (info.getState().equals(Info.STATE_SUBMIT_TO_PROVINCE)) {
				if (user.getDepartmentId().length() == 2) {
					info.setState(Info.STATE_OVER);
					info.setAuditDepartmentId("");
				} else {
					throw new RuntimeException("-4");	// 审核人错误, 没有权限
				}
			} else {
				// 不是需要省厅用户审核的信息，需要往上报
				// 父机构
				Department father = getFatherDepartment(user.getDepartmentId());
				// 父机构为空，提出
				if (father == null) {
					throw new RuntimeException("-3");	// 机构错误
				} else {
					// 校验下当前人员是否具有权限
					if (info.getState().equals(user.getDepartmentId().length() * 10)) {
						// 状态设置为父机构编码长度 * 10
						info.setState(father.getId().length() * 10);
						info.setAuditDepartmentId(father.getId());
					} else {
						throw new RuntimeException("-4");	// 审核人错误, 没有权限
					}
				}
			}
		}
		
		infoDAO.saveOrUpdate(info);
	}

	private Department getFatherDepartment(String departmentId) {
		int len = departmentId.length();
		// 当前机构id长度必须大于2
		if (len > 2) {
			String fatherId = departmentId.substring(0, len - 2);
			 // 如果父机构编码的后2位为00,则说明有断档，直接跳过00，再找上级父机构
			if (!fatherId.substring(fatherId.length() - 2, fatherId.length()).equals("00")) {
				Department father = departmentDAO.get(fatherId);
				// 查询父机构是否存在
				if (father != null) {
					// 父机构是否处理业务, 如果不处理则跳过，再找上级父机构
					if (father.getIsWork() == 1) {
						return father;
					} else {
						return getFatherDepartment(fatherId);
					}
				} else {
					return null;
				}
			} else {
				return getFatherDepartment(fatherId);
			}
		} else {
			return null;
		}
	}
	
	public Info findById(Integer id) {
		return infoDAO.get(id);
	}
	
	public List<Info> findAll() {
		return infoDAO.findAll();
	}
	
	public int getCount(Integer year, User user) {
		List<Integer> length = new ArrayList<>();
		length.add(10);
		
		return infoDAO.getCountByDepartmentId(year, null, 
				departmentDAO.findByRange(user.getDepartmentId(), length).stream().map(Department::getId).collect(Collectors.toList()));
	}
	
	public List<Info> findAll(Integer year, User user, int firstResult, int maxResult) {
		List<Integer> length = new ArrayList<>();
		length.add(10);
		
		return infoDAO.findByDepartmentId(year, null, 
				departmentDAO.findByRange(user.getDepartmentId(), length).stream().map(Department::getId).collect(Collectors.toList()), 
				firstResult, maxResult);
	}
	
	public int getCountByEdit(Integer year, User user) {
		List<String> departmentIdList = new ArrayList<>();
		departmentIdList.add(user.getDepartmentId());
		
		return infoDAO.getCountByDepartmentId(year, Info.STATE_EDIT, departmentIdList);
	}
	
	public List<Info> findByEdit(Integer year, User user, int firstResult, int maxResult) {
		List<String> departmentIdList = new ArrayList<>();
		departmentIdList.add(user.getDepartmentId());
		
		return infoDAO.findByDepartmentId(year, Info.STATE_EDIT, departmentIdList, firstResult, maxResult);
	}
	
	public int getCountByWaitAudit(Integer year, User user) {
		return infoDAO.getCountByAuditDepartmentId(year, user.getDepartmentId());
	}
	
	public List<Info> findByWaitAudit(Integer year, User user, int firstResult, int maxResult) {
		return infoDAO.findByAuditDepartmentId(year, user.getDepartmentId(), firstResult, maxResult);
	}
}

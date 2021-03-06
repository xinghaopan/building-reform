package com.cc.buildingReform.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.buildingReform.dao.ArchiveInfoDAO;
import com.cc.buildingReform.dao.AuditDAO;
import com.cc.buildingReform.dao.DepartmentDAO;
import com.cc.buildingReform.dao.IdcardDAO;
import com.cc.buildingReform.dao.InfoDAO;
import com.cc.buildingReform.dao.QuotaDAO;
import com.cc.buildingReform.form.ArchiveInfo;
import com.cc.buildingReform.form.Audit;
import com.cc.buildingReform.form.Department;
import com.cc.buildingReform.form.Idcard;
import com.cc.buildingReform.form.Info;
import com.cc.buildingReform.form.Quota;
import com.cc.buildingReform.form.User;

@Service
@Transactional
public class InfoServiceImpl implements InfoService {
	static Boolean lock = true;

	@Autowired
	private InfoDAO infoDAO;
	
	@Autowired
	private IdcardDAO idcardDAO;
	
	@Autowired
	private ArchiveInfoDAO archiveInfoDAO;
	
	@Autowired
	private QuotaDAO quotaDAO;
	
	@Autowired
	private DepartmentDAO departmentDAO;
	
	@Autowired
	private AuditDAO auditDAO;

	public int maintain(Info info) {
		return infoDAO.maintain(info);
	}

	public void save(Info info) {
		synchronized(InfoServiceImpl.lock) {
			if (info == null || info.getId() == null || info.getId() == 0) {
				// 校验当前机构在指定的计划年度是否还有剩余指标
				List<Quota> list = quotaDAO.findByDepartmentId(info.getPlanYear(), info.getDepartmentId());
				if (list == null || list.isEmpty() || list.get(0).getRestNum() == 0) {
					throw new RuntimeException("-1");
				}

				// 校验身份证号是否被占用
				if (idcardDAO.checkId(info.getId(), info.getPersonId()) == 0) {
					throw new RuntimeException("-10");
				}

				// 剩余指标 - 1
				list.get(0).setRestNum(list.get(0).getRestNum() - 1);

				quotaDAO.saveOrUpdate(list.get(0));

			}

			if (!info.getState().equals(Info.STATE_OVER)) {
				// 保存上报信息，无论新增还是修改，都将状态置为 编辑状态
				info.setState(Info.STATE_EDIT);
			}
			infoDAO.saveOrUpdate(info);

			// 保存身份证号
			idcardDAO.saveOrUpdate(new Idcard(info));
		}
	}

	public void remove(Integer id) {
		synchronized(InfoServiceImpl.lock) {
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
			if (idcardDAO.get(id) != null) {
				idcardDAO.delete(id);
			}
		}
	}
	
	public void submit(User user, Integer id, String content) {
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
			
			// 保存审核信息
			Audit audit = new Audit();
			audit.setInfoId(info.getId());
			audit.setState(1);
			audit.setContent(content);
			audit.setDepartmentId(info.getDepartmentId());
			audit.setDepartmentName(info.getDepartmentName());
			audit.setAuditDepartmentId(user.getDepartmentId());
			audit.setAuditDepartmentName(user.getDepartmentName());
			audit.setAuditUserId(user.getId());
			audit.setAuditUserName(user.getTrueName());
			audit.setDate(new Date());
			
			auditDAO.save(audit);
		}
		
		infoDAO.saveOrUpdate(info);
	}

	public void batchSubmit(User user, String ids, String content) {
		if (ids != null && ids != "") {
			String[] idArr = ids.split(",");
			for (int i = 0; i < idArr.length; i ++) {
				Integer id = Integer.parseInt(idArr[i]);
				this.submit(user, id, content);
			}
		}
	}
	
	public void batchBack(User user, String ids, String content) {
		if (ids != null && ids != "") {
			String[] idArr = ids.split(",");
			for (int i = 0; i < idArr.length; i ++) {
				Integer id = Integer.parseInt(idArr[i]);
				this.back(user, id, content);
			}
		}
	}
	
	public void back(User user, Integer id, String content) {
		Info info = infoDAO.get(id);
		// 不能为空
		if (info == null) {
			throw new RuntimeException("-1");	// 提交的信息错误
		}
		
		// 状态只能是 >= 编辑状态 并且 不能是结束状态
		if (!info.getState().equals(Info.STATE_SUBMIT_TO_TOWN) && !info.getState().equals(Info.STATE_SUBMIT_TO_COUNTY) && !info.getState().equals(Info.STATE_SUBMIT_TO_CITY) && !info.getState().equals(Info.STATE_SUBMIT_TO_PROVINCE)) {
			throw new RuntimeException("-2");	// 信息状态错误
		}
		
		if (!info.getState().equals(user.getDepartmentId().length() * 10)) {
			throw new RuntimeException("-4");	// 审核人错误, 没有权限
		}
		
		info.setState(Info.STATE_AUDIT_RETURN);
		
		infoDAO.saveOrUpdate(info);
		
		// 保存审核信息
		Audit audit = new Audit();
		audit.setInfoId(info.getId());
		audit.setState(-1);
		audit.setContent(content);
		audit.setDepartmentId(info.getDepartmentId());
		audit.setDepartmentName(info.getDepartmentName());
		audit.setAuditDepartmentId(user.getDepartmentId());
		audit.setAuditDepartmentName(user.getDepartmentName());
		audit.setAuditUserId(user.getId());
		audit.setAuditUserName(user.getTrueName());
		audit.setDate(new Date());
		
		auditDAO.save(audit);
	}
	
	private Department getFatherDepartment(String departmentId) {
		int len = departmentId.length();
		// 当前机构id长度必须大于2
		if (len > 2) {
			Department department = departmentDAO.get(departmentId);
			if (department != null) {
				Department father = departmentDAO.get(department.getQuotaManageId());
				if (father != null) {
					return father;
				}
			}
//			String fatherId = departmentId.substring(0, len - 2);
//			 // 如果父机构编码的后2位为00,则说明有断档，直接跳过00，再找上级父机构
//			if (!fatherId.substring(fatherId.length() - 2, fatherId.length()).equals("00")) {
//				Department father = departmentDAO.get(fatherId);
//				// 查询父机构是否存在
//				if (father != null) {
//					// 父机构是否处理业务, 如果不处理则跳过，再找上级父机构
//					if (father.getIsWork() == 1) {
//						return father;
//					} else {
//						return getFatherDepartment(fatherId);
//					}
//				} else {
//					return null;
//				}
//			} else {
//				return getFatherDepartment(fatherId);
//			}
		} 
			
		return null;
		
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
		length.add(8);
		length.add(6);
		
		return infoDAO.findByDepartmentId(year, null, 
				departmentDAO.findByRange(user.getDepartmentId(), length).stream().map(Department::getId).collect(Collectors.toList()), 
				firstResult, maxResult);
	}

	public int getCountByEdit(Integer year, User user) {
		List<String> departmentIdList = new ArrayList<>();
		departmentIdList.add(user.getDepartmentId());

		return infoDAO.getCountByDepartmentId(year, Info.STATE_EDIT, departmentIdList);
	}

	public int getCountByEdit(Integer year, User user, String personName, String personId) {
		List<String> departmentIdList = new ArrayList<>();
		departmentIdList.add(user.getDepartmentId());

		return infoDAO.getCountByDepartmentId(year, Info.STATE_EDIT, departmentIdList, personName, personId);
	}

	public List<Info> findByEdit(Integer year, User user) {
		List<String> departmentIdList = new ArrayList<>();
		departmentIdList.add(user.getDepartmentId());
		
		return infoDAO.findByDepartmentId(year, Info.STATE_EDIT, departmentIdList);
	}

	public List<Info> findByEdit(Integer year, User user, int firstResult, int maxResult) {
		List<String> departmentIdList = new ArrayList<>();
		departmentIdList.add(user.getDepartmentId());

		return infoDAO.findByDepartmentId(year, Info.STATE_EDIT, departmentIdList, firstResult, maxResult);
	}

	public List<Info> findByEdit(Integer year, User user, int firstResult, int maxResult, String personName, String personId) {
		List<String> departmentIdList = new ArrayList<>();
		departmentIdList.add(user.getDepartmentId());

		return infoDAO.findByDepartmentId(year, Info.STATE_EDIT, departmentIdList, firstResult, maxResult, personName, personId);
	}

	public int getCountByWaitAudit(Integer year, User user) {
		return infoDAO.getCountByAuditDepartmentId(year, user.getDepartmentId());
	}

	public int getCountByWaitAudit(Integer year, User user, String personName, String personId, String departmentId) {
		return infoDAO.getCountByAuditDepartmentId(year, user.getDepartmentId(), personName, personId, departmentId);
	}

	public List<Info> findByWaitAudit(Integer year, User user, int firstResult, int maxResult) {
		return infoDAO.findByAuditDepartmentId(year, user.getDepartmentId(), firstResult, maxResult);
	}

	public List<Info> findByWaitAudit(Integer year, User user, int firstResult, int maxResult, String personName, String personId, String departmentId) {
		return infoDAO.findByAuditDepartmentId(year, user.getDepartmentId(), firstResult, maxResult, personName, personId, departmentId);
	}

	public int getCountByAuditInfo(Integer year, User user) {
		List<Integer> stateList = new ArrayList<>();
		stateList.add(Info.STATE_SUBMIT_TO_CITY);
		stateList.add(Info.STATE_SUBMIT_TO_COUNTY);
		stateList.add(Info.STATE_SUBMIT_TO_PROVINCE);
		stateList.add(Info.STATE_SUBMIT_TO_TOWN);

		return infoDAO.getCountByDepartmentId(year, stateList, user.getDepartmentId());
	}

	public int getCountByAuditInfo(Integer year, User user, String personName, String personId) {
		List<Integer> stateList = new ArrayList<>();
		stateList.add(Info.STATE_SUBMIT_TO_CITY);
		stateList.add(Info.STATE_SUBMIT_TO_COUNTY);
		stateList.add(Info.STATE_SUBMIT_TO_PROVINCE);
		stateList.add(Info.STATE_SUBMIT_TO_TOWN);

		return infoDAO.getCountByDepartmentId(year, stateList, user.getDepartmentId(), personName, personId);
	}

	public List<Info> findByAuditInfo(Integer year, User user, int firstResult, int maxResult) {
		List<Integer> stateList = new ArrayList<>();
		stateList.add(Info.STATE_SUBMIT_TO_CITY);
		stateList.add(Info.STATE_SUBMIT_TO_COUNTY);
		stateList.add(Info.STATE_SUBMIT_TO_PROVINCE);
		stateList.add(Info.STATE_SUBMIT_TO_TOWN);

		return infoDAO.findByDepartmentId(year, stateList, user.getDepartmentId(), firstResult, maxResult);
	}

	public List<Info> findByAuditInfo(Integer year, User user, int firstResult, int maxResult, String personName, String personId) {
		List<Integer> stateList = new ArrayList<>();
		stateList.add(Info.STATE_SUBMIT_TO_CITY);
		stateList.add(Info.STATE_SUBMIT_TO_COUNTY);
		stateList.add(Info.STATE_SUBMIT_TO_PROVINCE);
		stateList.add(Info.STATE_SUBMIT_TO_TOWN);

		return infoDAO.findByDepartmentId(year, stateList, user.getDepartmentId(), firstResult, maxResult, personName, personId);
	}

	public int getCountByBackInfo(Integer year, User user) {
		List<Integer> stateList = new ArrayList<>();
		stateList.add(Info.STATE_AUDIT_RETURN);

		return infoDAO.getCountByDepartmentId(year, stateList, user.getDepartmentId());
	}

	public int getCountByBackInfo(Integer year, User user, String personName, String personId) {
		List<Integer> stateList = new ArrayList<>();
		stateList.add(Info.STATE_AUDIT_RETURN);

		return infoDAO.getCountByDepartmentId(year, stateList, user.getDepartmentId(), personName, personId);
	}

	public List<Info> findByBackInfo(Integer year, User user, int firstResult, int maxResult) {
		List<Integer> stateList = new ArrayList<>();
		stateList.add(Info.STATE_AUDIT_RETURN);

		return infoDAO.findByDepartmentId(year, stateList, user.getDepartmentId(), firstResult, maxResult);
	}

	public List<Info> findByBackInfo(Integer year, User user, int firstResult, int maxResult, String personName, String personId) {
		List<Integer> stateList = new ArrayList<>();
		stateList.add(Info.STATE_AUDIT_RETURN);

		return infoDAO.findByDepartmentId(year, stateList, user.getDepartmentId(), firstResult, maxResult, personName, personId);
	}

	public int checkId(Integer id, String idcard) {
		return idcardDAO.checkId(id, idcard);
	}
	
	public int getCountByDepartmentId(Integer year, String departmentId, String personName, String personId, Integer state) {
		List<String> list = new ArrayList<>();
		if (departmentId.length() > 2) {
			if (departmentId.length() == 10) {
				list.add(departmentId);
			}
			else {
				list = departmentDAO.findByQuotaManageId(departmentId).stream().distinct().map(n -> n.getId()).collect(Collectors.toList());
			}
		}
		
		String property1 = null;
		String property2 = null;
		
		if (state.equals(1)) {
			property1 = "rebuildBeginDate";
			property2 = "rebuildEndDate";
		}
		else if (state.equals(2)) {
			property1 = "rebuildEndDate";
			property2 = "acceptanceDate";
		}
		else if (state.equals(3)) {
			property1 = "acceptanceDate";
		}
		
		return infoDAO.getCountByManageDepartmentId(year, list, personName, personId, property1, property2);
	}

	public List<Info> findByDepartmentId(Integer year, String departmentId, String personName, String personId, Integer state, int firstResult, int maxResult) {
		List<String> list = new ArrayList<>();
		if (departmentId.length() > 2) {
			if (departmentId.length() == 10) {
				list.add(departmentId);
			}
			else {
				list = departmentDAO.findByQuotaManageId(departmentId).stream().distinct().map(n -> n.getId()).collect(Collectors.toList());
			}
		}
		
		String property1 = null;
		String property2 = null;
		
		if (state.equals(1)) {
			property1 = "rebuildBeginDate";
			property2 = "rebuildEndDate";
		}
		else if (state.equals(2)) {
			property1 = "rebuildEndDate";
			property2 = "acceptanceDate";
		}
		else if (state.equals(3)) {
			property1 = "acceptanceDate";
		}
		
		return infoDAO.findByManageDepartmentId(year, list, personName, personId, property1, property2, firstResult, maxResult);
	}

	public int getCountByNoOpen(Integer year, String departmentId) {
		return infoDAO.getCountByDate(year, departmentId, "rebuildBeginDate");
	}

	public int getCountByNoOpen(Integer year, String departmentId, String personName, String personId) {
		return infoDAO.getCountByDate(year, departmentId, "rebuildBeginDate", personName, personId);
	}

	public List<Info> findByNoOpen(Integer year, String departmentId, int firstResult, int maxResult) {
		return infoDAO.findByDate(year, departmentId, "rebuildBeginDate", firstResult, maxResult);
	}

	public List<Info> findByNoOpen(Integer year, String departmentId, int firstResult, int maxResult, String personName, String personId) {
		return infoDAO.findByDate(year, departmentId, "rebuildBeginDate", firstResult, maxResult, personName, personId);
	}

	public int getCountByNoOver(Integer year, String departmentId) {
		return infoDAO.getCountByDate(year, departmentId, "rebuildEndDate");
	}

	public int getCountByNoOver(Integer year, String departmentId, String personName, String personId) {
		return infoDAO.getCountByDate(year, departmentId, "rebuildEndDate", personName, personId);
	}

	public List<Info> findByNoOver(Integer year, String departmentId, int firstResult, int maxResult) {
		return infoDAO.findByDate(year, departmentId, "rebuildEndDate", firstResult, maxResult);
	}

	public List<Info> findByNoOver(Integer year, String departmentId, int firstResult, int maxResult, String personName, String personId) {
		return infoDAO.findByDate(year, departmentId, "rebuildEndDate", firstResult, maxResult, personName, personId);
	}

	public int getCountByNoAcceptance(Integer year, String departmentId) {
		return infoDAO.getCountByDate(year, departmentId, "acceptanceDate");
	}

	public int getCountByNoAcceptance(Integer year, String departmentId, String personName, String personId) {
		return infoDAO.getCountByDate(year, departmentId, "acceptanceDate", personName, personId);
	}

	public List<Info> findByNoAcceptance(Integer year, String departmentId, int firstResult, int maxResult) {
		return infoDAO.findByDate(year, departmentId, "acceptanceDate", firstResult, maxResult);
	}

	public List<Info> findByNoAcceptance(Integer year, String departmentId, int firstResult, int maxResult, String personName, String personId) {
		return infoDAO.findByDate(year, departmentId, "acceptanceDate", firstResult, maxResult, personName, personId);
	}

	public int getCountByAcceptanceInfo(Integer year, User user) {
		return infoDAO.getCountByAcceptanceInfo(year, user.getDepartmentId());
	}

	public int getCountByAcceptanceInfo(Integer year, User user, String personName, String personId) {
		return infoDAO.getCountByAcceptanceInfo(year, user.getDepartmentId(), personName, personId);
	}

	public List<Info> findByAcceptanceInfo(Integer year, User user, int firstResult, int maxResult) {
		return infoDAO.findByAcceptanceInfo(year, user.getDepartmentId(), firstResult, maxResult);
	}

	public List<Info> findByAcceptanceInfo(Integer year, User user, int firstResult, int maxResult, String personName, String personId) {
		return infoDAO.findByAcceptanceInfo(year, user.getDepartmentId(), firstResult, maxResult, personName, personId);
	}

	/**
	 * 信息归档 2016-08-21 by p
	 * 
	 */
	public void archive(String ids, User user) {
		String[] arr = ids.split(",");
		for (int i = 0; i < arr.length; i ++) {
			Info info = infoDAO.get(Integer.valueOf(arr[i]));
			if (info != null && info.getState().equals(Info.STATE_OVER)) {
				archiveInfoDAO.saveOrUpdate(new ArchiveInfo(info));
				// TODO 暂时不删除归档信息
				//infoDAO.delete(info.getId());
				info.setState(Info.STATE_ARCHIVE);
				infoDAO.saveOrUpdate(info);
			}
			else {
				throw new RuntimeException("-1");
			}
		}
	}
	
	public void statisticsQuota(Integer year) {
		infoDAO.statisticsQuota(year);
	}
}

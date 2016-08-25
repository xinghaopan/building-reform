package com.cc.buildingReform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.buildingReform.dao.ArchiveInfoDAO;
import com.cc.buildingReform.form.ArchiveInfo;
import com.cc.buildingReform.form.User;

@Service
@Transactional
public class ArchiveInfoServiceImpl implements ArchiveInfoService {

	@Autowired
	private ArchiveInfoDAO archiveInfoDao;
	
	public ArchiveInfo findById(Integer id) {
		return archiveInfoDao.get(id);
	}
	
	public int getCountByDepartmentId(Integer year, User user, String idcard) {
		return archiveInfoDao.getCountByDepartmentId(year, user.getDepartmentId(), idcard);
	}

	public List<ArchiveInfo> findByDepartmentId(Integer year, User user, String idcard, int firstResult, int maxResult) {
		return archiveInfoDao.findByDepartmentId(year, user.getDepartmentId(), idcard, firstResult, maxResult);
	}
}

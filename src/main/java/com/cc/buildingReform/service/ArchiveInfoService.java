package com.cc.buildingReform.service;

import java.util.List;

import com.cc.buildingReform.form.ArchiveInfo;
import com.cc.buildingReform.form.User;

public interface ArchiveInfoService {
	public ArchiveInfo findById(Integer id);
	
	public int getCountByDepartmentId(Integer year, User user, String idcard);
	
	public List<ArchiveInfo> findByDepartmentId(Integer year, User user, String idcard, int firstResult, int maxResult);
}
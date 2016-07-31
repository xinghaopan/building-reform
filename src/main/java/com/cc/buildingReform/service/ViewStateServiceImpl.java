package com.cc.buildingReform.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.buildingReform.dao.DepartmentDAO;
import com.cc.buildingReform.dao.ViewStateDAO;
import com.cc.buildingReform.form.Department;
import com.cc.buildingReform.form.ViewState;

@Service
@Transactional
public class ViewStateServiceImpl implements ViewStateService {

	@Autowired
	private ViewStateDAO viewStateDAO;

	@Autowired
	private DepartmentDAO departmentDAO;

	public List<ViewState> findByFatherId(Integer year, String fatherId) {
		List<Department> sonList = departmentDAO.findByFatherId(fatherId);
		if (sonList != null && !sonList.isEmpty()) {
			List<Object[]> list = viewStateDAO.findByFatherId(year, fatherId, sonList.get(0).getId().length());
			List<ViewState> l = new ArrayList<>();
			
			for (int i = 0; i < list.size(); i ++) {
				ViewState viewState = new ViewState();
				viewState.setId(String.valueOf(list.get(i)[0]));
				viewState.setYear(Integer.valueOf(String.valueOf(list.get(i)[1])));
				viewState.setBeginNum(Integer.valueOf(String.valueOf(list.get(i)[2])));
				viewState.setEndNum(Integer.valueOf(String.valueOf(list.get(i)[3])));
				viewState.setAcceptanceNum(Integer.valueOf(String.valueOf(list.get(i)[4])));
				viewState.setFundSendNum(Integer.valueOf(String.valueOf(list.get(i)[5])));
				viewState.setSum(Integer.valueOf(String.valueOf(list.get(i)[6])));
				
				for (int j = 0; j < sonList.size(); j ++) {
					if (viewState.getId().equals(sonList.get(j).getId())) {
						viewState.setName(sonList.get(j).getName());
					}
				}
				
				l.add(viewState);
			}
			return l;
		} else {
			return null;
		}
	}
}

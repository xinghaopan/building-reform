package com.cc.buildingReform.service;

import java.util.List;

import com.cc.buildingReform.form.ViewState;

public interface ViewStateService {

	public List<ViewState> findByFatherId(Integer year, String fatherId);
}
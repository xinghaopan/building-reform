package com.cc.buildingReform.service;

import java.util.List;

import com.cc.buildingReform.form.Dic;


public interface DicService {

	public void save(Dic dic);

	public void remove(Integer id);
	
	public Dic findById(Integer id);

	public List<Dic> findAll();
	
	public List<Dic> findByKeyId(Integer keyId);
}
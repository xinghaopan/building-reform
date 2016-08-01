package com.cc.buildingReform.service;

import java.util.List;

import com.cc.buildingReform.form.Feedback;


public interface FeedbackService {

	public void save(Feedback feedback);

	public void remove(Integer id);
	
	public Feedback findById(Integer id);

	public List<Feedback> findAll();
	
	public int getCount();
	
	public List<Feedback> findAll(int firstResult, int maxResult);
	
	public int getCountByUserId(Integer userId);
	
	public List<Feedback> findByUserId(Integer userId, int firstResult, int maxResult);
}
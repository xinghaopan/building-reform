package com.cc.buildingReform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.buildingReform.dao.FeedbackDAO;
import com.cc.buildingReform.form.Feedback;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackDAO feedbackDAO;

	public void save(Feedback feedback) {
		feedbackDAO.saveOrUpdate(feedback);
	}

	public void remove(Integer id) {
		feedbackDAO.delete(id);
	}
	
	public Feedback findById(Integer id) {
		return feedbackDAO.get(id);
	}

	public List<Feedback> findAll() {
		return feedbackDAO.findAll();
	}
	
	public int getCount() {
		return feedbackDAO.getCount();
	}
	
	public List<Feedback> findAll(int firstResult, int maxResult) {
		return feedbackDAO.findAll(firstResult, maxResult);
	}
	
	public int getCountByUserId(Integer userId) {
		return feedbackDAO.getCountByUserId(userId);
	}
	
	public List<Feedback> findByUserId(Integer userId, int firstResult, int maxResult) {
		return feedbackDAO.findByUserId(userId, firstResult, maxResult);
	}
}

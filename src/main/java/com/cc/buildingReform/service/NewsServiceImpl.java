package com.cc.buildingReform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.buildingReform.dao.NewsDAO;
import com.cc.buildingReform.form.News;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDAO newsDAO;
	
	public void save(News news) {
		newsDAO.saveOrUpdate(news);
	}

	public void remove(Integer id) {
		newsDAO.delete(id);
	}
	
	public News findById(Integer id, Integer audit) {
		return newsDAO.findById(id, audit);
	}

	public List searchSQL(String sql) {
		return newsDAO.searchSQL(sql);
	}

	public int excuteSQL(String sql) {
		return newsDAO.excuteSQL(sql);
	}

	public List<News> findAll() {
		return newsDAO.findAll();
	}
	
	public int getCount() {
		return newsDAO.getCount();
	}
	
	public List<News> findAll(int firstResult, int maxResult) {
		return newsDAO.findAll(firstResult, maxResult);
	}
	
	public List<News> findByMid(Integer mid, Integer audit) {
		return newsDAO.findByMid(mid, audit);
	}
	
	public int getCountByMid(Integer mid, Integer audit) {
		return newsDAO.getCountByMid(mid, audit);
	}
	
	public List<News> findByMid(Integer mid, Integer audit, int firstResult, int maxResult) {
		return newsDAO.findByMid(mid, audit, firstResult, maxResult);
	}
	
	public List<News> findByMid(Integer mid, Integer audit, String title, String content, String author) {
		return newsDAO.findByMid(mid, audit, title, content, author);
	}
	
	public int getCountByMid(Integer mid, Integer audit, String title, String content, String author) {
		return newsDAO.getCountByMid(mid, audit, title, content, author);
	}
	
	public List<News> findByMid(Integer mid, Integer audit, String title, String content, String author, int firstResult, int maxResult) {
		return newsDAO.findByMid(mid, audit, title, content, author, firstResult, maxResult);
	}
	
	public List<News> findBySearch(String text, Integer audit) {
		return newsDAO.findBySearch(text, audit);
	}
	
	public int getCountBySearch(String text, Integer audit) {
		return newsDAO.getCountBySearch(text, audit);
	}
	
	public List<News> findBySearch(String text, Integer audit, int firstResult, int maxResult) {
		return newsDAO.findBySearch(text, audit, firstResult, maxResult);
	}
	
	public List<News> findByMid(List<Integer> mid, Integer audit, String title, String content, String author) {
		return newsDAO.findByMid(mid, audit, title, content, author);
	}
	
	public int getCountByMid(List<Integer> mid, Integer audit, String title, String content, String author) {
		return newsDAO.getCountByMid(mid, audit, title, content, author);
	}
	
	public List<News> findByMid(List<Integer> mid, Integer audit, String title, String content, String author, int firstResult, int maxResult) {
		return newsDAO.findByMid(mid, audit, title, content, author, firstResult, maxResult);
	}
	
	public List<?> statistic(List<Integer> list) {
		return newsDAO.statistic(list);
	}
}

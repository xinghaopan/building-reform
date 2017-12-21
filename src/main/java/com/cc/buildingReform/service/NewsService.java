package com.cc.buildingReform.service;

import java.util.List;

import com.cc.buildingReform.form.News;

public interface NewsService {

	public void save(News news);

	public void remove(Integer id);
	
	public News findById(Integer id, Integer audit);

	List searchSQL(String sql);

	int excuteSQL(String sql);

	public List<News> findAll();

	public int getCount();
	
	public List<News> findAll(int firstResult, int maxResult);
	
	public List<News> findByMid(Integer mid, Integer audit);
	
	public int getCountByMid(Integer mid, Integer audit);
	
	public List<News> findByMid(Integer mid, Integer audit, int firstResult, int maxResult);
	
	public List<News> findByMid(Integer mid, Integer audit, String title, String content, String author);
	
	public int getCountByMid(Integer mid, Integer audit, String title, String content, String author);
	
	public List<News> findByMid(Integer mid, Integer audit, String title, String content, String author, int firstResult, int maxResult);
	
	public List<News> findBySearch(String text, Integer audit);
	
	public int getCountBySearch(String text, Integer audit);
	
	public List<News> findBySearch(String text, Integer audit, int firstResult, int maxResult);
	
	public List<News> findByMid(List<Integer> mid, Integer audit, String title, String content, String author);
	
	public int getCountByMid(List<Integer> mid, Integer audit, String title, String content, String author);
	
	public List<News> findByMid(List<Integer> mid, Integer audit, String title, String content, String author, int firstResult, int maxResult);
	
	public List<?> statistic(List<Integer> list);
}
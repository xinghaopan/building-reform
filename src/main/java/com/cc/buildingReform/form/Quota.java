package com.cc.buildingReform.form;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BR_QUOTA")
public class Quota implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static Integer getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QUOTA_ID", unique = true, nullable = false)
	private Integer id;

	@Column(name = "QUOTA_YEAR")
	private Integer year;

	@Column(name = "QUOTA_DEPARTMENT_ID")
	private String departmentId;

	@Column(name = "QUOTA_DEPARTMENT_NAME")
	private String departmentName;

	@Column(name = "QUOTA_NUM")
	private Integer num;
	
	@Column(name = "QUOTA_REST_NUM")
	private Integer restNum;

	@Column(name = "QUOTA_DISTRIBUTE_DEPARTMENT_ID")
	private String distributeDepartmentId;
	
	@Column(name = "QUOTA_DATE")
	private Date date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getRestNum() {
		return restNum;
	}

	public void setRestNum(Integer restNum) {
		this.restNum = restNum;
	}

	public String getDistributeDepartmentId() {
		return distributeDepartmentId;
	}

	public void setDistributeDepartmentId(String distributeDepartmentId) {
		this.distributeDepartmentId = distributeDepartmentId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}

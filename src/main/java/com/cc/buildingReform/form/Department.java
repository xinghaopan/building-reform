package com.cc.buildingReform.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BR_DEPARTMENT")
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DEPARTMENT_ID", unique = true, nullable = false)
	private String id;

	@Column(name = "DEPARTMENT_FATHER_ID")
	private String fatherId;

	@Column(name = "DEPARTMENT_QUOTA_MANAGE_ID")
	private String quotaManageId;

	@Column(name = "DEPARTMENT_NAME")
	private String name;

	@Column(name = "DEPARTMENT_FULL_NAME")
	private String fullName;

	@Column(name = "DEPARTMENT_IS_WORK")
	private Integer isWork;

	@Column(name = "DEPARTMENT_IS_STATISTICS	")
	private Integer isStatistics;

	@Column(name = "DEPARTMENT_ORDER")
	private Integer order;

	@Column(name = "DEPARTMENT_LENGTH")
	private Integer length;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFatherId() {
		return fatherId;
	}

	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}

	public String getQuotaManageId() {
		return quotaManageId;
	}

	public void setQuotaManageId(String quotaManageId) {
		this.quotaManageId = quotaManageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getIsWork() {
		return isWork;
	}

	public void setIsWork(Integer isWork) {
		this.isWork = isWork;
	}

	public Integer getIsStatistics() {
		return isStatistics;
	}

	public void setIsStatistics(Integer isStatistics) {
		this.isStatistics = isStatistics;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
}

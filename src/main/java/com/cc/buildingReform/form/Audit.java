package com.cc.buildingReform.form;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BR_AUDIT")
public class Audit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AUDIT_ID", unique = true, nullable = false)
	private Integer id;

	@Column(name = "AUDIT_INFO_ID")
	private Integer infoId;

	@Column(name = "AUDIT_NAME")
	private String name;

	@Column(name = "AUDIT_STATE")
	private Integer state;

	@Column(name = "AUDIT_CONTENT")
	private String content;

	@Column(name = "AUDIT_DEPARTMENT_ID")
	private String departmentId;

	@Column(name = "AUDIT_DEPARTMENT_NAME")
	private String departmentName;

	@Column(name = "AUDIT_AUDIT_DEPARTMENT_ID")
	private String auditDepartmentId;

	@Column(name = "AUDIT_AUDIT_DEPARTMENT_NAME")
	private String auditDepartmentName;

	@Column(name = "AUDIT_AUDIT_USER_ID")
	private Integer auditUserId;

	@Column(name = "AUDIT_AUDIT_USER_NAME")
	private String auditUserName;

	@Column(name = "AUDIT_DATE")
	private Date date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getAuditDepartmentId() {
		return auditDepartmentId;
	}

	public void setAuditDepartmentId(String auditDepartmentId) {
		this.auditDepartmentId = auditDepartmentId;
	}

	public String getAuditDepartmentName() {
		return auditDepartmentName;
	}

	public void setAuditDepartmentName(String auditDepartmentName) {
		this.auditDepartmentName = auditDepartmentName;
	}

	public Integer getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

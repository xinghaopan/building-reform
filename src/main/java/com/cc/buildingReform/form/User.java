package com.cc.buildingReform.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "BR_USER")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID", unique = true, nullable = false)
	private Integer id;

	@Column(name = "USER_NAME")
	private String name;

	@Column(name = "USER_TRUE_NAME")
	private String trueName;

	@Column(name = "USER_PASSWORD")
	private String password;

	@OneToOne()
	@JoinColumn(name = "USER_ROLE_ID")
	@Cascade({ CascadeType.REFRESH })
	private Role role;

	@Column(name = "USER_ORDER")
	private Integer order;

	@Column(name = "USER_STATE")
	private Integer state;

	@Column(name = "USER_DEPARTMENT_ID")
	private String departmentId;

	@Column(name = "USER_DEPARTMENT_NAME")
	private String departmentName;
	
	@Column(name = "USER_KEY")
	private String key;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}

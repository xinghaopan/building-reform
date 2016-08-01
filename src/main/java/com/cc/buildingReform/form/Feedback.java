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
@Table(name = "BR_FEEDBACK")
public class Feedback implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FEEDBACK_ID", unique = true, nullable = false)
	private Integer id;

	@Column(name = "FEEDBACK_TITLE")
	private String title;

	@Column(name = "FEEDBACK_ASK_CONTENT")
	private String askContent;

	@Column(name = "FEEDBACK_DEPARTMENT_ID")
	private String departmentId;

	@Column(name = "FEEDBACK_DEPARTMENT_NAME")
	private String departmentName;

	@Column(name = "FEEDBACK_USER_ID")
	private Integer userId;

	@Column(name = "FEEDBACK_USER_NAME")
	private String userName;

	@Column(name = "FEEDBACK_DATE")
	private Date date;

	@Column(name = "FEEDBACK_REPLY_CONTENT")
	private String replyContent;

	@Column(name = "FEEDBACK_REPLY_DEPARTMENT_ID")
	private String replyDepartmentId;

	@Column(name = "FEEDBACK_REPLY_DEPARTMENT_NAME")
	private String replyDepartmentName;

	@Column(name = "FEEDBACK_REPLY_USER_ID")
	private Integer replyUserId;

	@Column(name = "FEEDBACK_REPLY_USER_NAME")
	private String replyUserName;

	@Column(name = "FEEDBACK_REPLY_DATE")
	private Date replyDate;

	@Column(name = "FEEDBACK_STATE")
	private Integer state;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAskContent() {
		return askContent;
	}

	public void setAskContent(String askContent) {
		this.askContent = askContent;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyDepartmentId() {
		return replyDepartmentId;
	}

	public void setReplyDepartmentId(String replyDepartmentId) {
		this.replyDepartmentId = replyDepartmentId;
	}

	public String getReplyDepartmentName() {
		return replyDepartmentName;
	}

	public void setReplyDepartmentName(String replyDepartmentName) {
		this.replyDepartmentName = replyDepartmentName;
	}

	public Integer getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(Integer replyUserId) {
		this.replyUserId = replyUserId;
	}

	public String getReplyUserName() {
		return replyUserName;
	}

	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}

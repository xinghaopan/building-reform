package com.cc.buildingReform.form;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BR_STATISTICS_QUOTA")
public class StatisticsQuota implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SQ_ID", unique = true, nullable = false)
	private String id;

	@Column(name = "SQ_FATHER_ID")
	private String fatherId;

	@Column(name = "SQ_QUOTA_MANAGE_ID")
	private String quotaManageId;

	@Column(name = "SQ_NAME")
	private String name;

	@Column(name = "SQ_FULL_NAME")
	private String fullName;

	@Column(name = "SQ_IS_WORK")
	private Integer isWork;

	@Column(name = "SQ_IS_STATISTICS	")
	private Integer isStatistics;

	@Column(name = "SQ_ORDER")
	private Integer order;

	@Column(name = "SQ_LENGTH")
	private Integer length;

	@Column(name = "SQ_YEAR")
	private Integer year;

	@Column(name = "SQ_QUOTA_ID")
	private Integer quotaId;

	@Column(name = "SQ_BEGIN_NUM")
	private Integer beginNum;

	@Column(name = "SQ_END_NUM")
	private Integer endNum;
	
	@Column(name = "SQ_ACCEPTANCE_NUM")
	private Integer acceptanceNum;
	
	@Column(name = "SQ_FUND_SEND_NUM")
	private Integer fundSendNum;
	
	@Column(name = "SQ_NUM")
	private Integer num;
	
	@Column(name = "SQ_REST_NUM")
	private Integer restNum;
	
	@Column(name = "SQ_DATE")
	private Date date;

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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getQuotaId() {
		return quotaId;
	}

	public void setQuotaId(Integer quotaId) {
		this.quotaId = quotaId;
	}

	public Integer getBeginNum() {
		return beginNum;
	}

	public void setBeginNum(Integer beginNum) {
		this.beginNum = beginNum;
	}

	public Integer getEndNum() {
		return endNum;
	}

	public void setEndNum(Integer endNum) {
		this.endNum = endNum;
	}

	public Integer getAcceptanceNum() {
		return acceptanceNum;
	}

	public void setAcceptanceNum(Integer acceptanceNum) {
		this.acceptanceNum = acceptanceNum;
	}

	public Integer getFundSendNum() {
		return fundSendNum;
	}

	public void setFundSendNum(Integer fundSendNum) {
		this.fundSendNum = fundSendNum;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}

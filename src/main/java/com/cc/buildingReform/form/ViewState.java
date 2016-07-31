package com.cc.buildingReform.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BR_VIEW_STATE")
public class ViewState implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STATE_ID", unique = true, nullable = false)
	private String id;

	@Column(name = "STATE_NAME")
	private String name;

	@Column(name = "STATE_YEAR")
	private Integer year;

	@Column(name = "STATE_BEGIN_NUM")
	private Integer beginNum;

	@Column(name = "STATE_END_NUM")
	private Integer endNum;
	
	@Column(name = "STATE_ACCEPTANCE_NUM")
	private Integer acceptanceNum;
	
	@Column(name = "STATE_FUND_SEND_NUM")
	private Integer fundSendNum;
	
	@Column(name = "STATE_SUM")
	private Integer sum;

	@Column(name = "STATE_NUM")
	private Integer num;
	
	@Column(name = "STATE_REST_NUM")
	private Integer restNum;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
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

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
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
}

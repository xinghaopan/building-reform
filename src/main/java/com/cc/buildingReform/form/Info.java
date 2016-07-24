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
@Table(name = "BR_INFO")
public class Info implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final Integer STATE_EDIT = 0;
	// 状态为待审核机构的编码长度
	public static final Integer STATE_SUBMIT_TO_TOWN = 80;	
	public static final Integer STATE_SUBMIT_TO_COUNTY = 60;
	public static final Integer STATE_SUBMIT_TO_CITY = 40;
	public static final Integer STATE_SUBMIT_TO_PROVINCE = 20;
	
	public static final Integer STATE_OVER = 10;
	public static final Integer STATE_AUDIT_RETURN = -1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INFO_ID", unique = true, nullable = false)
	private Integer id;

	@Column(name = "INFO_PERSON_NAME")
	private String personName;

	@Column(name = "INFO_PERSON_SEX")
	private Integer personSex;

	@Column(name = "INFO_PERSON_ID")
	private String personId;

	@Column(name = "INFO_PERSON_NATION")
	private String personNation;

	@Column(name = "INFO_PERSON_NUM")
	private Integer personNum;

	@Column(name = "INFO_PERSON_TEL")
	private String personTel;

	@Column(name = "INFO_HOUSE_AGE")
	private String houseAge;

	@Column(name = "INFO_HOUSE_OLD_SIZE1")
	private Double houseOldSize1;

	@Column(name = "INFO_HOUSE_OLD_SIZE2")
	private Double houseOldSize2;

	@Column(name = "INFO_HOUSE_NEW_SIZE1")
	private Double houseNewSize1;

	@Column(name = "INFO_HOUSE_NEW_SIZE2")
	private Double houseNewSize2;

	@Column(name = "INFO_HOUSE_OLD_TYPE")
	private Integer houseOldType;

	@Column(name = "INFO_HOUSE_OLD_TYPE_NAME")
	private String houseOldTypeName;

	@Column(name = "INFO_TOILET_OLD_TYPE")
	private Integer toiletOldType;

	@Column(name = "INFO_TOILET_OLD_TYPE_NAME")
	private String toiletOldTypeName;

	@Column(name = "INFO_TOILET_NEW_TYPE")
	private Integer toiletNewType;

	@Column(name = "INFO_TOILET_NEW_TYPE_NAME")
	private String toiletNewTypeName;

	@Column(name = "INFO_BUILD_MODE")
	private Integer buildMode;

	@Column(name = "INFO_BUILD_MODE_NAME")
	private String buildModeName;

	@Column(name = "INFO_REBUILD_MODE")
	private Integer rebuildMode;

	@Column(name = "INFO_REBUILD_MODE_NAME")
	private String rebuildModeName;

	@Column(name = "INFO_PLAN_YEAR")
	private Integer planYear;

	@Column(name = "INFO_REBUILD_RATE")
	private Integer rebuildRate;

	@Column(name = "INFO_REBUILD_RATE_NAME")
	private String rebuildRateName;

	@Column(name = "INFO_REBUILD_BEGIN_DATE")
	private Date rebuildBeginDate;

	@Column(name = "INFO_REBUILD_END_DATE")
	private Date rebuildEndDate;
	
	@Column(name = "INFO_IS_ACCEPTANCE")
	private Integer isAcceptance;

	@Column(name = "INFO_GRANT_TYPE")
	private Integer grantType;

	@Column(name = "INFO_GRANT_TYPE_NAME")
	private String grantTypeName;
	
	@Column(name = "INFO_SUM_FUND")
	private Double sumFund;

	@Column(name = "INFO_GRANT_PROVINCE_FUND")
	private Double grantProvinceFund;

	@Column(name = "INFO_GRANT_COUNTIES_FUND")
	private Double grantCountiesFund;
	
	@Column(name = "INFO_PERSON_SELF_FUND")
	private Double personSelfFund;

	@Column(name = "INFO_PERSON_IMAGE")
	private String personImage;

	@Column(name = "INFO_HOUSE_IN_OLD_IMAGE")
	private String houseInOldImage;

	@Column(name = "INFO_HOUSE_IN_NEW_IMAGE")
	private String houseInNewImage;

	@Column(name = "INFO_HOUSE_OUT_OLD_IMAGE")
	private String houseOutOldImage;

	@Column(name = "INIFO_HOUSE_OUT_NEW_IMAGE")
	private String houseOutNewImage;

	@Column(name = "INFO_ACCEPTANCE_IMAGE")
	private String acceptanceImage;

	@Column(name = "INFO_AUDIT_DEPARTMENT_ID")
	private String auditDepartmentId;

	@Column(name = "INFO_DEPARTMENT_ID")
	private String departmentId;

	@Column(name = "INFO_DEPARTMENT_NAME")
	private String departmentName;

	@Column(name = "INFO_USER_ID")
	private Integer userId;
	
	@Column(name = "INFO_USER_NAME")
	private String userName;
	
	@Column(name = "INFO_DATE")
	private Date date;

	@Column(name = "INFO_STATE")
	private Integer state;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Integer getPersonSex() {
		return personSex;
	}

	public void setPersonSex(Integer personSex) {
		this.personSex = personSex;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPersonNation() {
		return personNation;
	}

	public void setPersonNation(String personNation) {
		this.personNation = personNation;
	}

	public Integer getPersonNum() {
		return personNum;
	}

	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}

	public String getPersonTel() {
		return personTel;
	}

	public void setPersonTel(String personTel) {
		this.personTel = personTel;
	}

	public String getHouseAge() {
		return houseAge;
	}

	public void setHouseAge(String houseAge) {
		this.houseAge = houseAge;
	}

	public Double getHouseOldSize1() {
		return houseOldSize1;
	}

	public void setHouseOldSize1(Double houseOldSize1) {
		this.houseOldSize1 = houseOldSize1;
	}

	public Double getHouseOldSize2() {
		return houseOldSize2;
	}

	public void setHouseOldSize2(Double houseOldSize2) {
		this.houseOldSize2 = houseOldSize2;
	}

	public Double getHouseNewSize1() {
		return houseNewSize1;
	}

	public void setHouseNewSize1(Double houseNewSize1) {
		this.houseNewSize1 = houseNewSize1;
	}

	public Double getHouseNewSize2() {
		return houseNewSize2;
	}

	public void setHouseNewSize2(Double houseNewSize2) {
		this.houseNewSize2 = houseNewSize2;
	}

	public Integer getHouseOldType() {
		return houseOldType;
	}

	public void setHouseOldType(Integer houseOldType) {
		this.houseOldType = houseOldType;
	}

	public String getHouseOldTypeName() {
		return houseOldTypeName;
	}

	public void setHouseOldTypeName(String houseOldTypeName) {
		this.houseOldTypeName = houseOldTypeName;
	}

	public Integer getToiletOldType() {
		return toiletOldType;
	}

	public void setToiletOldType(Integer toiletOldType) {
		this.toiletOldType = toiletOldType;
	}

	public String getToiletOldTypeName() {
		return toiletOldTypeName;
	}

	public void setToiletOldTypeName(String toiletOldTypeName) {
		this.toiletOldTypeName = toiletOldTypeName;
	}

	public Integer getToiletNewType() {
		return toiletNewType;
	}

	public void setToiletNewType(Integer toiletNewType) {
		this.toiletNewType = toiletNewType;
	}

	public String getToiletNewTypeName() {
		return toiletNewTypeName;
	}

	public void setToiletNewTypeName(String toiletNewTypeName) {
		this.toiletNewTypeName = toiletNewTypeName;
	}

	public Integer getBuildMode() {
		return buildMode;
	}

	public void setBuildMode(Integer buildMode) {
		this.buildMode = buildMode;
	}

	public String getBuildModeName() {
		return buildModeName;
	}

	public void setBuildModeName(String buildModeName) {
		this.buildModeName = buildModeName;
	}

	public Integer getRebuildMode() {
		return rebuildMode;
	}

	public void setRebuildMode(Integer rebuildMode) {
		this.rebuildMode = rebuildMode;
	}

	public String getRebuildModeName() {
		return rebuildModeName;
	}

	public void setRebuildModeName(String rebuildModeName) {
		this.rebuildModeName = rebuildModeName;
	}

	public Integer getPlanYear() {
		return planYear;
	}

	public void setPlanYear(Integer planYear) {
		this.planYear = planYear;
	}

	public Integer getRebuildRate() {
		return rebuildRate;
	}

	public void setRebuildRate(Integer rebuildRate) {
		this.rebuildRate = rebuildRate;
	}

	public String getRebuildRateName() {
		return rebuildRateName;
	}

	public void setRebuildRateName(String rebuildRateName) {
		this.rebuildRateName = rebuildRateName;
	}

	public Date getRebuildBeginDate() {
		return rebuildBeginDate;
	}

	public void setRebuildBeginDate(Date rebuildBeginDate) {
		this.rebuildBeginDate = rebuildBeginDate;
	}

	public Date getRebuildEndDate() {
		return rebuildEndDate;
	}

	public void setRebuildEndDate(Date rebuildEndDate) {
		this.rebuildEndDate = rebuildEndDate;
	}

	public Integer getIsAcceptance() {
		return isAcceptance;
	}

	public void setIsAcceptance(Integer isAcceptance) {
		this.isAcceptance = isAcceptance;
	}

	public Integer getGrantType() {
		return grantType;
	}

	public void setGrantType(Integer grantType) {
		this.grantType = grantType;
	}

	public String getGrantTypeName() {
		return grantTypeName;
	}

	public void setGrantTypeName(String grantTypeName) {
		this.grantTypeName = grantTypeName;
	}

	public Double getSumFund() {
		return sumFund;
	}

	public void setSumFund(Double sumFund) {
		this.sumFund = sumFund;
	}

	public Double getGrantProvinceFund() {
		return grantProvinceFund;
	}

	public void setGrantProvinceFund(Double grantProvinceFund) {
		this.grantProvinceFund = grantProvinceFund;
	}

	public Double getGrantCountiesFund() {
		return grantCountiesFund;
	}

	public void setGrantCountiesFund(Double grantCountiesFund) {
		this.grantCountiesFund = grantCountiesFund;
	}

	public Double getPersonSelfFund() {
		return personSelfFund;
	}

	public void setPersonSelfFund(Double personSelfFund) {
		this.personSelfFund = personSelfFund;
	}

	public String getPersonImage() {
		return personImage;
	}

	public void setPersonImage(String personImage) {
		this.personImage = personImage;
	}

	public String getHouseInOldImage() {
		return houseInOldImage;
	}

	public void setHouseInOldImage(String houseInOldImage) {
		this.houseInOldImage = houseInOldImage;
	}

	public String getHouseInNewImage() {
		return houseInNewImage;
	}

	public void setHouseInNewImage(String houseInNewImage) {
		this.houseInNewImage = houseInNewImage;
	}

	public String getHouseOutOldImage() {
		return houseOutOldImage;
	}

	public void setHouseOutOldImage(String houseOutOldImage) {
		this.houseOutOldImage = houseOutOldImage;
	}

	public String getHouseOutNewImage() {
		return houseOutNewImage;
	}

	public void setHouseOutNewImage(String houseOutNewImage) {
		this.houseOutNewImage = houseOutNewImage;
	}

	public String getAcceptanceImage() {
		return acceptanceImage;
	}

	public void setAcceptanceImage(String acceptanceImage) {
		this.acceptanceImage = acceptanceImage;
	}

	public String getAuditDepartmentId() {
		return auditDepartmentId;
	}

	public void setAuditDepartmentId(String auditDepartmentId) {
		this.auditDepartmentId = auditDepartmentId;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public static Integer getStateEdit() {
		return STATE_EDIT;
	}

	public static Integer getStateSubmitToTown() {
		return STATE_SUBMIT_TO_TOWN;
	}

	public static Integer getStateSubmitToCounty() {
		return STATE_SUBMIT_TO_COUNTY;
	}

	public static Integer getStateSubmitToProvince() {
		return STATE_SUBMIT_TO_PROVINCE;
	}

	public static Integer getStateOver() {
		return STATE_OVER;
	}
}

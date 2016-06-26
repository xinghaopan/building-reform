package com.cc.buildingReform.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BR_MENU")
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MENU_ID", unique = true, nullable = false)
	private Integer id;

	@Column(name = "MENU_FATHER_ID")
	private Integer fatherId;

	@Column(name = "MENU_FRONT_NAME")
	private String frontName;

	@Column(name = "MENU_FRONT_SUB_NAME")
	private String frontSubName;

	@Column(name = "MENU_BACK_NAME")
	private String backName;

	@Column(name = "MENU_FRONT_LINK")
	private String frontLink;

	@Column(name = "MENU_BACK_LINK")
	private String backLink;

	@Column(name = "MENU_BIG_IMAGE")
	private String bigImage;

	@Column(name = "MENU_MIDDLE_IMAGE")
	private String middleImage;

	@Column(name = "MENU_SMALL_IMAGE")
	private String smallImage;

	@Column(name = "MENU_ISAUDIT")
	private Integer isaudit;

	@Column(name = "MENU_TYPE")
	private Integer type;

	@Column(name = "MENU_HAS_TITLE")
	private Integer hasTitle;

	@Column(name = "MENU_ORDER")
	private Integer order;

	@Column(name = "MENU_IS_SUBJECT")
	private Integer isSubject;

	@Column(name = "MENU_IS_NAVIGATION")
	private Integer isNavigation;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFatherId() {
		if (fatherId == null) {
			return -1;
		}
		else {
			return fatherId;
		}
	}

	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}

	public String getFrontName() {
		return frontName;
	}

	public void setFrontName(String frontName) {
		this.frontName = frontName;
	}

	public String getBackName() {
		return backName;
	}

	public void setBackName(String backName) {
		this.backName = backName;
	}

	public String getFrontLink() {
		return frontLink;
	}

	public void setFrontLink(String frontLink) {
		this.frontLink = frontLink;
	}

	public String getBackLink() {
		return backLink;
	}

	public void setBackLink(String backLink) {
		this.backLink = backLink;
	}

	public String getBigImage() {
		return bigImage;
	}

	public void setBigImage(String bigImage) {
		this.bigImage = bigImage;
	}

	public String getMiddleImage() {
		return middleImage;
	}

	public void setMiddleImage(String middleImage) {
		this.middleImage = middleImage;
	}

	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

	public Integer getIsaudit() {
		return isaudit;
	}

	public void setIsaudit(Integer isaudit) {
		this.isaudit = isaudit;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getHasTitle() {
		return hasTitle;
	}

	public void setHasTitle(Integer hasTitle) {
		this.hasTitle = hasTitle;
	}

	public Integer getOrder() {
		if (order == null) {
			return -1;
		}
		else {
			return order;
		}
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getIsSubject() {
		if (isSubject == null) {
			return -1;
		}
		else {
			return isSubject;
		}
	}

	public void setIsSubject(Integer isSubject) {
		this.isSubject = isSubject;
	}

	public Integer getIsNavigation() {
		if (isNavigation == null) {
			return -1;
		}
		else {
			return isNavigation;
		}
	}

	public void setIsNavigation(Integer isNavigation) {
		this.isNavigation = isNavigation;
	}

	public String getFrontSubName() {
		return frontSubName;
	}

	public void setFrontSubName(String frontSubName) {
		this.frontSubName = frontSubName;
	}

}

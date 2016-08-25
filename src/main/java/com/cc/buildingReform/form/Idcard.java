package com.cc.buildingReform.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BR_IDCARD")
public class Idcard implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "IDCARD_ID", unique = true, nullable = false)
	private Integer id;

	@Column(name = "IDCARD_IDCARD")
	private String idcard;

	public Idcard() {
		
	}
	
	public Idcard(Info info) {
		this.id = info.getId();
		this.idcard = info.getPersonId();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
}

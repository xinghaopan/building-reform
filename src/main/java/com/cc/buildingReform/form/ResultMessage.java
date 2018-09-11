package com.cc.buildingReform.form;

import net.sf.json.JSONObject;

import java.io.Serializable;

public class ResultMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean success = false;
	private boolean logged = false;
	private JSONObject data = new JSONObject();
	private String message = "";
	private String detail = "";

	public boolean isSuccess() {
		return success;
	}

	public ResultMessage setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public boolean isLogged() {
		return logged;
	}

	public ResultMessage setLogged(boolean logged) {
		this.logged = logged;
		return this;
	}

	public JSONObject getData() {
		return data;
	}

	public ResultMessage setData(JSONObject data) {
		this.data = data;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ResultMessage setMessage(String message) {
		this.message = message;
		return this;
	}

	public String getDetail() {
		return detail;
	}

	public ResultMessage setDetail(String detail) {
		this.detail = detail;
		return this;
	}
}

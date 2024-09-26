package com.nagarro.model;

public class ResponseJson {
	private String message;
	private Object data;
	
	public ResponseJson() { 
		this.message = "default message";
		this.data = null;
	}
	public ResponseJson(String msg) {
		this(msg, null);
	}
	public ResponseJson(Object dd) {
		this("default message", dd);
	}
	public ResponseJson(String msg, Object dd) {
		super();
		this.message = msg;
		this.data = dd;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}


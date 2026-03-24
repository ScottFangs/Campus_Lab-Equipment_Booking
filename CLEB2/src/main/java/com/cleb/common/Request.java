package com.cleb.common;


import java.io.Serializable;

public class Request implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String action;
	private Object payload;
	
	public Request(String action, Object payload) {
		this.action = action;
		this.payload = payload;
	}
	
	public Request() {
		this.action = "?";
		this.payload = null;
		
	}
	
	public Request(Request r) {
		this.action = r.action;
		this.payload = r.payload;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Request [action=" + action + ", payload=" + payload + "]";
	}
	
	
}

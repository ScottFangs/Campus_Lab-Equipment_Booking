package com.cleb.common;

import java.io.Serializable;

public class Response implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String status;
	private Object data;
	
	public Response(String status, Object data) {
		this.status = status;
		this.data = data;
	}
	
	
	public Response() {
		this.status = "?";
		this.data = null;
	}
	
	public Response(Response r) {
		this.status = r.status;
		this.data = r.data;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Response [status=" + status + ", data=" + data + "]";
	}
}

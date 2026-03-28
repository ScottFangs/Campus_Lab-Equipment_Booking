package com.cleb.common;

import java.io.Serializable;
import java.util.UUID;

public class Response implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private UUID correlationId;
	private String status;
	private Object data;
	
	public Response(UUID correlationId, String status, Object data) {
		this.correlationId = correlationId;
		this.status = status;
		this.data = data;
		
	}
	
	
	public Response() {
		this.correlationId = UUID.randomUUID();
		this.status = "?";
		this.data = null;
	}
	
	public Response(Response r) {
		this.correlationId = r.correlationId;
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
	
	public UUID getCorrelationId() {
        return correlationId;
    }


	@Override
	public String toString() {
		return "Response [status=" + status + ", data=" + data + "]";
	}
}

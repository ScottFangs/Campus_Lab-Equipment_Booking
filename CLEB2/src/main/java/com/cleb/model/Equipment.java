package com.cleb.model;

import java.io.Serializable;

public class Equipment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String equipmentId;
	private String description;
	private String status;
	private Lab lab;
	
	
	public Equipment(String equipmentId, String description, String status, Lab lab) {
		this.equipmentId = equipmentId;
		this.description = description;
		this.status = status;
		this.lab = lab;
	}
	
	public Equipment() {
		this.equipmentId = "?";
		this.description = "?";
		this.status = "AVAILABLE";
		this.lab = null;
	}
	
	public Equipment(Equipment e) {
		this.equipmentId = e.equipmentId;
		this.description = e.description;
		this.status = e.status;
		this.lab = e.lab;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Lab getLab() {
		return lab;
	}

	public void setLab(Lab lab) {
		this.lab = lab;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Equipment [equipmentId=" + equipmentId + ", description=" + description + ", status=" + status
				+ ", lab=" + lab + "]";
	}
	
	
}

/*
package com.cleb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.Element;


public class Lab implements Serializable{
	private static final long serialVersionUID = 1L;
    
    private int labId;
    private String name;
    private int totalSeats;
    private String campus;
    private List<Seat> seats = new ArrayList<>();
    private List<Element> equipmentList = new ArrayList<>();
    
    
    public Lab(int labId, String name, int totalSeats, String campus) {
        this.labId = labId;
        this.name = name;
        this.totalSeats = totalSeats;
        this.campus = campus;
        
        // Ago create the seats dem automatically
        for (int i = 1; i <= totalSeats; i++) {
            seats.add(new Seat(i, true, this));
        }
        
    }
    
    public Lab() {
    	this.labId = 0;
    	this.name = "?";
    	this.totalSeats = 0;
    	this.campus = "?";
    }
    
    public Lab(Lab l) {
    	this.labId = l.labId;
        this.name = l.name;
        this.totalSeats = l.totalSeats;
        this.campus = l.campus;
        this.seats = new ArrayList<>(l.seats);
        this.equipmentList = new ArrayList<>(l.equipmentList);
    }

	public int getLabId() {
		return labId;
	}

	public void setLabId(int labId) {
		this.labId = labId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public List<Element> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<Element> equipmentList) {
		this.equipmentList = equipmentList;
	}
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Lab [labId=" + labId + ", name=" + name + ", totalSeats=" + totalSeats + ", campus=" + campus
				+ ", seats=" + seats + ", equipmentList=" + equipmentList + "]";
	}
    
	
	
}
*/


package com.cleb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Lab implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int labId;
    private String name;
    private int totalSeats;
    private String campus;
    private List<Seat> seats = new ArrayList<>();
    private List<Equipment> equipmentList = new ArrayList<>();
    
    public Lab(int labId, String name, int totalSeats, String campus) {
    	this.labId = labId;
    	this.name = name;
    	this.totalSeats = totalSeats;
    	this.campus = campus;
        
        // Ago create the seats dem automatically
        for (int i = 1; i <= totalSeats; i++) {
            seats.add(new Seat(i, true, this));
        }
        
    }

    // 2. DEFAULT Constructor (required for Hibernate + Serialization)
    public Lab() {
    	this.labId = 0;
    	this.name = "?";
    	this.totalSeats = 0;
    	this.campus = "?";
    }

    // 3. COPY Constructor (consistent with all your other classes)
    public Lab(Lab l) {
    	this.labId = l.labId;
        this.name = l.name;
        this.totalSeats = l.totalSeats;
        this.campus = l.campus;
        this.seats = new ArrayList<>(l.seats);
        this.equipmentList = new ArrayList<>(l.equipmentList);
    }

    // === Methods your ModelTest needs ===
    public String getName() {
    	return name;
    }
    public int getTotalSeats() {
    	return totalSeats;
    }
    public List<Seat> getSeats() {
    	return seats;
    }
    public void addEquipment(Equipment e) {
    	equipmentList.add(e);
    }

    // Extra getters (good to have)
    public int getLabId() {
    	return labId;
    }
    public String getCampus() {
    	return campus;
    }
    public List<Equipment> getEquipmentList() {
    	return equipmentList;
    }
    
}


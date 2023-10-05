package com.exam.ExamSeatingArrangement.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ClassRoom {
	@Id
	private String roomnum;
	private int capacity;
	private int benches;
	
	public String getRoomnum() {
		return roomnum;
	}
	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getBenches() {
		return benches;
	}
	public void setBenches(int benches) {
		this.benches = benches;
	}
	
	

}

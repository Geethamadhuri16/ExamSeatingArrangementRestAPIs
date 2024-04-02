package com.exam.ExamSeatingArrangement.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ClassRoom {
	@Id
	private String roomnum;
	private int capacityOfEachRow;
	private int capacityOfEachColumn;
	//private int benches;
	private int rowsInClass;
	private int columnsInClass;
	public ClassRoom() {
		this.capacityOfEachRow=this.columnsInClass*2;
		this.capacityOfEachColumn=this.columnsInClass*2;
		//this.benches=this.rowsInClass*this.columnsInClass;
	}
	public String getRoomnum() {
		return roomnum;
	}
	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}
	
//	public int getBenches() {
//		return benches;
//	}
//	public void setBenches(int benches) {
//		this.benches = benches;
//	}
	public int getCapacityOfEachRow() {
		return capacityOfEachRow;
	}
	public void setCapacityOfEachRow(int capacityOfEachRow) {
		this.capacityOfEachRow = capacityOfEachRow;
	}
	public int getCapacityOfEachColumn() {
		return capacityOfEachColumn;
	}
	public void setCapacityOfEachColumn(int capacityOfEachColumn) {
		this.capacityOfEachColumn = capacityOfEachColumn;
	}
	public int getRowsInClass() {
		return rowsInClass;
	}
	public void setRowsInClass(int rowsInClass) {
		this.rowsInClass = rowsInClass;
	}
	public int getColumnsInClass() {
		return columnsInClass;
	}
	public void setColumnsInClass(int columnsInClass) {
		this.columnsInClass = columnsInClass;
	}
	
	

}

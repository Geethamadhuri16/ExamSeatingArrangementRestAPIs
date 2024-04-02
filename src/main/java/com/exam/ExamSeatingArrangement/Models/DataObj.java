package com.exam.ExamSeatingArrangement.Models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class DataObj {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@OneToMany
	private List<Arrangement> arrangements;
	private int rowsNum;
	private int columnsNum;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Arrangement> getArrangements() {
		return arrangements;
	}
	public void setArrangements(List<Arrangement> arrangements) {
		this.arrangements = arrangements;
	}
	public int getRowsNum() {
		return rowsNum;
	}
	public void setRowsNum(int rowsNum) {
		this.rowsNum = rowsNum;
	}
	public int getColumnsNum() {
		return columnsNum;
	}
	public void setColumnsNum(int columnsNum) {
		this.columnsNum = columnsNum;
	}
	
	
	
	

}

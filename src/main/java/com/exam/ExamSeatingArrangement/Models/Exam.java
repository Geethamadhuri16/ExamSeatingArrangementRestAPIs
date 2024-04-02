package com.exam.ExamSeatingArrangement.Models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Exam {
	@Id
	private String id;
	private String name;
	private String sem;
	private String branch;
	public Exam() {
		String id= UUID.randomUUID().toString();
		this.setId(id);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSem() {
		return sem;
	}
	public void setSem(String sem) {
		this.sem = sem;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	

}

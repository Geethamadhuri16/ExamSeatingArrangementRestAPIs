package com.exam.ExamSeatingArrangement.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Arrangement {
	@Id
	private String enroll;
	private String name;
	private int sem;
	private String branch;
	private String exam;
	private String classroom;
	private int bench;
	public String getEnroll() {
		return enroll;
	}
	@Override
	public String toString() {
		return "Arrangement [enroll=" + enroll + ", name=" + name + ", sem=" + sem + ", branch=" + branch + ", exam="
				+ exam + ", classroom=" + classroom + ", bench=" + bench + "]";
	}
	public void setEnroll(String enroll) {
		this.enroll = enroll;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getSem() {
		return sem;
	}
	public void setSem(int sem) {
		this.sem = sem;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getExam() {
		return exam;
	}
	public void setExam(String exam) {
		this.exam = exam;
	}
	public String getClassroom() {
		return classroom;
	}
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	public int getBench() {
		return bench;
	}
	public void setBench(int bench) {
		this.bench = bench;
	}
	
	
	

}

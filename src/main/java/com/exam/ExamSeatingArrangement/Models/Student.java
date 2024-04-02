package com.exam.ExamSeatingArrangement.Models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", enroll=" + enroll + ", mail=" + mail + ", examroll="
				+ examroll + ", classroll=" + classroll + ", branch=" + branch + ", sem=" + sem + ", password="
				+ password + "]";
	}
	@Id
	private String id;
	private String name;
	private String enroll;
	private String mail;
	private int examroll;
	private int classroll;
	private String branch;
	private int sem;
	private String password;
	public Student() {
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
	public String getEnroll() {
		return enroll;
	}
	public void setEnroll(String enroll) {
		this.enroll = enroll;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public int getExamroll() {
		return examroll;
	}
	public void setExamroll(int examroll) {
		this.examroll = examroll;
	}
	public int getClassroll() {
		return classroll;
	}
	public void setClassroll(int classroll) {
		this.classroll = classroll;
	}
	public int getSem() {
		return sem;
	}
	public void setSem(int sem) {
		this.sem = sem;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}

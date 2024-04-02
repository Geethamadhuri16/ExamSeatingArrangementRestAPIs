package com.exam.ExamSeatingArrangement.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.ExamSeatingArrangement.Models.Admin;

public interface AdminDao extends JpaRepository<Admin, String> {
	public Admin findByMail(String mail);

}

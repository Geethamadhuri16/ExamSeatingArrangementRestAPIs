package com.exam.ExamSeatingArrangement.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.ExamSeatingArrangement.Models.Teacher;

public interface TeacherDao extends JpaRepository<Teacher, String> {
	public Teacher findByMail(String mail);
	public List<Teacher> findByBranch(String branch);

}


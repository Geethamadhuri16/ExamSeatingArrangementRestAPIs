package com.exam.ExamSeatingArrangement.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.exam.ExamSeatingArrangement.Models.Exam;

public interface ExamDao extends JpaRepository<Exam, String> {
	@Query("SELECT e FROM Exam e WHERE e.branch = :branch AND e.sem = :sem")
	Exam findBySemandBranch(@Param("branch") String branch, @Param("sem") String sem);

	

}

package com.exam.ExamSeatingArrangement.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exam.ExamSeatingArrangement.Models.Arrangement;

public interface ArrangementDao extends JpaRepository<Arrangement, String> {
	
	public List<Arrangement> findByClassroom(String room);
	@Query("FROM Arrangement WHERE branch = :branch AND sem = :sem")
	public List<Arrangement> findByBranchandSem(@Param("branch") String branch, @Param("sem") int sem);


	

}

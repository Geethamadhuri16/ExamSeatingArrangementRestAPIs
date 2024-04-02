package com.exam.ExamSeatingArrangement.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exam.ExamSeatingArrangement.Models.Invigilation;

public interface InvigilationDao extends JpaRepository<Invigilation,Long> {
	@Query("FROM Invigilation WHERE examname = :examname AND branch = :branch")
	public List<Invigilation> findByExamnameandBranch(@Param("examname")String examname,@Param("branch")String branch);
	public Invigilation findByTeachersContaining(String teacherid);

}

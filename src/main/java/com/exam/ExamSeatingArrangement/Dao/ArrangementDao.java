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
	public List<Arrangement> findByBranch(String branch);
	@Query("FROM Arrangement WHERE branch = :branch AND classroom = :classroom")
	public List<Arrangement> findByBranchandRoom(@Param("branch") String branch, @Param("classroom") String classroom);
	@Query("FROM Arrangement WHERE branch = :branch AND classroom = :classroom AND rowNum = :rowNum")
	public List<Arrangement> findByBranchandRoomandRow(@Param("branch") String branch, @Param("classroom") String classroom,@Param("rowNum") String rowNum);
	@Query("FROM Arrangement WHERE branch = :branch AND classroom = :classroom AND columnNum = :columnNum")
	public List<Arrangement> findByBranchandRoomandColumn(@Param("branch") String branch, @Param("classroom") String classroom,@Param("columnNum") String columnNum);
	
	


	

}

package com.exam.ExamSeatingArrangement.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exam.ExamSeatingArrangement.Models.ClassRoom;

public interface ClassRoomDao extends JpaRepository<ClassRoom, String> {
	
	public ClassRoom findByRoomnum(String roomnum);

	
	
	

}

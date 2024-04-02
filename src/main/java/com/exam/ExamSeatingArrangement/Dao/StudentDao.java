package com.exam.ExamSeatingArrangement.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exam.ExamSeatingArrangement.Models.Student;

public interface StudentDao extends JpaRepository<Student, String> {
	public Student findByMail(String mail);
	@Query("SELECT COUNT(*) FROM Student WHERE sem = :sem")
	public int getCountBySem(@Param("sem") int sem);

	public List<Student> findBySem(int sem);
	
	
    @Query("SELECT s FROM Student s WHERE s.branch = :branch AND s.sem = :sem ORDER BY s.examroll")
	
	public List<Student> findBySemandBranch(@Param("sem")int sem,@Param("branch")String branch);
	
	@Query("SELECT COUNT(*) FROM Student WHERE sem = :sem AND branch = :branch")
	public int getCountBySemAndBranch(@Param("sem") int sem, @Param("branch") String branch);

	
	

	
	


}

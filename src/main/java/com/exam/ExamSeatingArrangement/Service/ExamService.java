package com.exam.ExamSeatingArrangement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exam.ExamSeatingArrangement.Dao.ExamDao;
import com.exam.ExamSeatingArrangement.Models.Exam;

@Service
public class ExamService {
	@Autowired
	private ExamDao repo;
	public ResponseEntity<String> addExam(Exam e) {
		try {
			repo.save(e);
			return new ResponseEntity<>(e.getId(),HttpStatus.ACCEPTED);
		} catch (Exception e1) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public ResponseEntity<List<Exam>> getallExams(){
		try {
			List<Exam> e=repo.findAll();
			if(e!=null) {
				return new ResponseEntity<>(e,HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	public ResponseEntity<String> delExam(String id) {
		try {
			Exam e=repo.findById(id).orElse(null);
			if(e!=null) {
				repo.deleteById(id);
				return new ResponseEntity<>(id,HttpStatus.NO_CONTENT);
				
			}else {
				return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
	}
	
	public ResponseEntity<List<Exam>> bysem(String sem){
		try {
			List<Exam> exam=repo.findBySem(sem);
			if(exam==null) {
				
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(exam,HttpStatus.NO_CONTENT);
				
			}
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
	}
	
	public ResponseEntity<List<Exam>> bysemandBranch(String sem,String branch){
		try {
			List<Exam> exam=repo.findBySemandBranch(branch, sem);
			if(exam==null) {
				
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(exam,HttpStatus.NO_CONTENT);
				
			}
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
	}
	
	

}

package com.exam.ExamSeatingArrangement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exam.ExamSeatingArrangement.Dao.StudentDao;
import com.exam.ExamSeatingArrangement.Models.Student;
@Service
public class StudentServicec {
	@Autowired
	private StudentDao repo;
	public ResponseEntity<Student> validate(String mail,String password){
		Student s=repo.findByMail(mail);
		if(s.getPassword().equals(password)){
			return new ResponseEntity<>(s,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	public ResponseEntity<String> register(Student s) {
		try {
			if(repo.findByMail(s.getMail())==null) {
				System.out.println("vachanayya");
				repo.save(s);
				return new ResponseEntity<>(s.getId(),HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null,HttpStatus.ALREADY_REPORTED);
				
			}
		} catch (Exception e) {
			System.out.println();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public ResponseEntity<List<Student>> getAllStudents(){
		try {
			List<Student> s=repo.findAll();
			if(s!=null) {
				return new ResponseEntity<>(s,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<List<Student>> getAllStudentsBySem(int sem){
		try {
			List<Student> s=repo.findBySem(sem);
			if(s!=null) {
			return new ResponseEntity<>(s,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	

}

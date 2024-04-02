package com.exam.ExamSeatingArrangement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exam.ExamSeatingArrangement.Dao.TeacherDao;
import com.exam.ExamSeatingArrangement.Models.Teacher;

@Service
public class TeacherService {
	@Autowired
	private TeacherDao repo;
	
	public ResponseEntity<String> register(Teacher t){
		try {
			repo.save(t);
			return new ResponseEntity<>(t.getTeacherid(),HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	public ResponseEntity<Boolean> login(String mail,String pass){
		try {
			Teacher t=repo.findByMail(mail) ;
			if(t.getPass().equals(pass)) {
				return new ResponseEntity<>(true,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(false,HttpStatus.EXPECTATION_FAILED);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<List<Teacher>> getallTeachers(){
		try {
			List<Teacher> t=repo.findAll();
			if(t!=null) {
				return new ResponseEntity<>(t,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<List<Teacher>> getTeachersByBranch(String branch){
		try {
			List<Teacher> t=repo.findByBranch(branch);
			if(t!=null) {
				return new ResponseEntity<>(t,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Teacher> getTeacher(String teacherId){
		try {
			Teacher t=repo.findById(teacherId).orElse(null);
			if(t!=null) {
				return new ResponseEntity<>(t,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}

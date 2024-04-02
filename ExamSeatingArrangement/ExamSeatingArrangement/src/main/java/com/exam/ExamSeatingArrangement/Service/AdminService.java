package com.exam.ExamSeatingArrangement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exam.ExamSeatingArrangement.Dao.AdminDao;
import com.exam.ExamSeatingArrangement.Models.Admin;
import com.exam.ExamSeatingArrangement.Models.Student;


@Service
public class AdminService {
	@Autowired
	private AdminDao repo;
	
	public ResponseEntity<Boolean> validate(String mail,String password){
		try {
			Admin a =repo.findByMail(mail);
			if(a.getPassword().equals(password)){
				return new ResponseEntity<>(true,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(false,HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public ResponseEntity<String> register(Admin a) {
		try {
			if(repo.findByMail(a.getMail())==null) {
				repo.save(a);
				return new ResponseEntity<>(a.getId(),HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null,HttpStatus.ALREADY_REPORTED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	
	

}

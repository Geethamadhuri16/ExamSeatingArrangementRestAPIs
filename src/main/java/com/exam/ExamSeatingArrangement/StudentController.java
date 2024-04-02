package com.exam.ExamSeatingArrangement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.ExamSeatingArrangement.Models.Student;
import com.exam.ExamSeatingArrangement.Service.StudentServicec;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class StudentController {
	@Autowired
	private StudentServicec service;
	
	@RequestMapping("/login/{mail}/{password}")
	public ResponseEntity<Student> login(@PathVariable("mail")String mail,@PathVariable("password") String pass){
		return service.validate(mail, pass);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Student stud){
		 return service.register(stud);
		
	}
	
	@GetMapping("/getAllStudents")
	public ResponseEntity<List<Student>>  getAll(){
		return service.getAllStudents();
		
	}
	
	@GetMapping("/getAllStudentsBySem/{sem}")
	public ResponseEntity<List<Student>>  getAllBySem(@PathVariable ("sem") int sem){
		return service.getAllStudentsBySem(sem);
		
	}
	
	
	
	
	
	
	
	

}

package com.exam.ExamSeatingArrangement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.ExamSeatingArrangement.Models.Exam;
import com.exam.ExamSeatingArrangement.Service.ExamService;

@RestController 
public class ExamController {
	@Autowired
	private ExamService service;
	@RequestMapping("/addExam")
	public ResponseEntity<String> addExam(@RequestBody Exam e) {
		return service.addExam(e);
		
	}
	
	@RequestMapping("/delExam/{id}")
	public ResponseEntity<String> delExam(@PathVariable("id")String id) {
		return service.delExam(id);
	}
	
	@RequestMapping("/getallexams")
	public ResponseEntity<List<Exam>> getAllExams(){
		return service.getallExams();
	}

}

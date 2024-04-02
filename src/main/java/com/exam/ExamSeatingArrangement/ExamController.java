package com.exam.ExamSeatingArrangement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.ExamSeatingArrangement.Models.Exam;
import com.exam.ExamSeatingArrangement.Service.ExamService;

@RestController 
@CrossOrigin(origins="http://localhost:3000")
public class ExamController {
	@Autowired
	private ExamService service;
	@RequestMapping("/addExam")
	public ResponseEntity<String> addExam(@RequestBody Exam e) {
		return service.addExam(e);
		
	}
	
	@DeleteMapping("/delExam/{id}")
	public ResponseEntity<String> delExam(@PathVariable("id")String id) {
		return service.delExam(id);
	}
	
	@RequestMapping("/getallexams")
	public ResponseEntity<List<Exam>> getAllExams(){
		return service.getallExams();
	}
	
	@RequestMapping("/bysem/{sem}")
	public ResponseEntity<List<Exam>> getbysem(@PathVariable("sem") String sem){
		return service.bysem(sem);
	}
	
	@RequestMapping("/bysemandBrnach/{sem}/{branch}")
	public ResponseEntity<List<Exam>> getbysem(@PathVariable("sem") String sem,@PathVariable("branch") String branch){
		return service.bysemandBranch(sem, branch);
	}

}

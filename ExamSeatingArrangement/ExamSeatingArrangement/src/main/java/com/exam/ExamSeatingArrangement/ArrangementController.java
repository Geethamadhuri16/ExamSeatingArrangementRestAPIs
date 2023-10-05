package com.exam.ExamSeatingArrangement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.ExamSeatingArrangement.Models.Arrangement;
import com.exam.ExamSeatingArrangement.Service.ArrangementService;
 
@RestController
public class ArrangementController {
	@Autowired
	private ArrangementService service;
	
	@GetMapping("/getSeating/{enroll}")
	public ResponseEntity<Arrangement> getSeating(@PathVariable("enroll") String enroll){
		return service.getSeating(enroll);
		
	}
	
	@GetMapping("/getByClassroom/{ClassRoom}")
	public ResponseEntity<List<Arrangement>> getByClassroom(@PathVariable ("ClassRoom") String ClassRoom){
		return service.getByClassroom(ClassRoom);
		
	}
	
	@GetMapping("/getByBranchAndSem/{branch}/{sem}")
	public ResponseEntity<List<Arrangement>> getBySemAndBranch(@PathVariable ("branch") String branch,@PathVariable ("sem") int sem){
		return service.getByBranchandSem(branch, sem);
	}
	
	@RequestMapping("/setArrangement/{room1}/{room2}/{room3}/{sem1}/{sem2}/{examname}/{branch}")
	public void setArrangement(@PathVariable ("room1") String room1,@PathVariable ("room2") String room2,@PathVariable ("room3") String room3,@PathVariable ("sem1") int sem1,@PathVariable ("sem2") int sem2,@PathVariable ("examname") String examname,@PathVariable ("branch") String branch) {
		service.setArrangementForCT(room1, room2, room3, sem1, sem2, examname,branch);
	}

}

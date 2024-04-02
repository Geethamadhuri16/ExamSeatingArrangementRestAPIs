package com.exam.ExamSeatingArrangement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.ExamSeatingArrangement.Models.Arrangement;
import com.exam.ExamSeatingArrangement.Models.DataObj;
import com.exam.ExamSeatingArrangement.Service.ArrangementService;
 
@RestController
@CrossOrigin(origins="http://localhost:3000")
public class ArrangementController {
	@Autowired
	private ArrangementService service;
	
	@GetMapping("/getSeating/{enroll}")
	public ResponseEntity<Arrangement> getSeating(@PathVariable("enroll") String enroll){
		return service.getSeating(enroll);
		
	}
	
	@GetMapping("/getByClassroom/{ClassRoom}")
	public ResponseEntity<DataObj> getByClassroom(@PathVariable ("ClassRoom") String ClassRoom){
		return service.getByClassroom(ClassRoom);
		
	}
	
	@GetMapping("/getByBranchAndSem/{branch}/{sem}")
	public ResponseEntity<List<Arrangement>> getBySemAndBranch(@PathVariable ("branch") String branch,@PathVariable ("sem") int sem){
		return service.getByBranchandSem(branch, sem);
	}
	
	@GetMapping("/getByBranch/{branch}")
	public ResponseEntity<List<Arrangement>> getByBranch(@PathVariable ("branch") String branch){
		return service.getByBranch(branch);
	}
	
	@GetMapping("/getByBranch/{branch}/{ClassRoom}")
	public ResponseEntity<Integer> getByBranchAndClassroom(@PathVariable ("branch") String branch,@PathVariable ("ClassRoom") String ClassRoom){
		return service.getByBranchandClassroom(branch, ClassRoom);
	}
	
//	@GetMapping("/getByBranch/{brnach}/{ClassRoom}/{row}")
//	public ResponseEntity<List<Arrangement>> getByBranchAndClassroomAndRow(@PathVariable ("branch") String branch,@PathVariable ("ClassRoom") String ClassRoom,@PathVariable ("row") String row){
//		return service.getByBranchClassRoomRownum(branch, ClassRoom, row);
//	}
//	
//	@GetMapping("/getByBranch/{brnach}/{ClassRoom}/{column}")
//	public ResponseEntity<List<Arrangement>> getByBranchAndClassroomAndColumn(@PathVariable ("branch") String branch,@PathVariable ("ClassRoom") String ClassRoom,@PathVariable ("column") String column){
//		return service.getByBranchClassRoomRownum(branch, ClassRoom, column);
//	}
	
	
	
	@GetMapping("/getArrangement")
	public ResponseEntity<List<Arrangement>> getArrangements(){
		return service.getArrangements();
		
	}
	
	 @PostMapping("/arrangement/{room1}/{room2}/{room3}/{sem1}/{sem2}/{examName}/{branch1}/{branch2}/{rows1}/{columns1}/{rows2}/{columns2}/{rows3}/{columns3}")
	    public void setArrangementForCT(
	            @PathVariable String room1,
	            @PathVariable String room2,
	            @PathVariable String room3,
	            @PathVariable int sem1,
	            @PathVariable int sem2,
	            @PathVariable String examName,
	            @PathVariable String branch1,
	            @PathVariable String branch2,
	            @PathVariable int rows1,
	            @PathVariable int columns1,
	            @PathVariable int rows2,
	            @PathVariable int columns2,
	            @PathVariable int rows3,
	            @PathVariable int columns3) {
	        service.setArrangementForCT(room1, room2, room3, sem1, sem2, examName, branch1,branch2, rows1, columns1, rows2, columns2, rows3, columns3);
	    }

}

package com.exam.ExamSeatingArrangement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.ExamSeatingArrangement.Models.ClassRoom;
import com.exam.ExamSeatingArrangement.Service.ClassroomService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class ClassRoomController {
	@Autowired
	private ClassroomService service;
	@RequestMapping("/addClassRoom")
	public ResponseEntity<String> addClassRoom(@RequestBody ClassRoom c) {
		return service.addclassroom(c);
		
	}
	@RequestMapping("/delClassRoom/{Roomnum}")
	public ResponseEntity<String> delClassRoom(@PathVariable("Roomnum") String Roomnum) {
		return service.delClassRoom(Roomnum);
	}
	
	@RequestMapping("/getAllClassRooms")
	public ResponseEntity<List<ClassRoom>> getAllClassRooms(){
		return service.getAllClassrooms();
	}
	
	@RequestMapping("/editClassRoom/{roomnum}")
	public ResponseEntity<String> editClassroom(@PathVariable("Roomnum") String Roomnum,@RequestBody ClassRoom c){
		return service.editClassroom(c, Roomnum);
	}
	
	@RequestMapping("/getAllClassRoomByRoomNum")
	public ResponseEntity<ClassRoom> getAllClassRoomByRoomNum(String roonum){
		return service.getClassrooms(roonum);
	}
	

}

package com.exam.ExamSeatingArrangement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exam.ExamSeatingArrangement.Dao.ClassRoomDao;
import com.exam.ExamSeatingArrangement.Models.ClassRoom;

@Service
public class ClassroomService {
	@Autowired
	private ClassRoomDao repo;
	public ResponseEntity<String> addclassroom(ClassRoom c) {
		try {
			repo.save(c);
			return new ResponseEntity<>(c.getRoomnum(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public ResponseEntity<List<ClassRoom>> getAllClassrooms(){
		try {
			List<ClassRoom> c=repo.findAll();
			if(c!=null) {
				return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public ResponseEntity<String> delClassRoom(String Roomnum) {
		try {
			repo.deleteById(Roomnum);
			return new ResponseEntity<>(Roomnum,HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<String> editClassroom(ClassRoom room,String roomnum){
		try {
			ClassRoom c=repo.findByRoomnum(roomnum);
			//c.setBenches(room.getBenches());
			//c.setCapacity(room.getCapacity());
			repo.save(c);
			return new ResponseEntity<>("edited",HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public ResponseEntity<ClassRoom> getClassrooms(String roomnum){
		try {
			ClassRoom c=repo.findByRoomnum(roomnum);
			if(c!=null) {
				return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	

}

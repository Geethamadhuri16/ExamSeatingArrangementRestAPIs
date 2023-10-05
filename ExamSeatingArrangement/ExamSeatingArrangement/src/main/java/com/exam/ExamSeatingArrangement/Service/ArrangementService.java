package com.exam.ExamSeatingArrangement.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exam.ExamSeatingArrangement.Dao.ArrangementDao;
import com.exam.ExamSeatingArrangement.Dao.ClassRoomDao;
import com.exam.ExamSeatingArrangement.Dao.StudentDao;
import com.exam.ExamSeatingArrangement.Models.Arrangement;
import com.exam.ExamSeatingArrangement.Models.Student;

import jakarta.transaction.Transactional;
@Service
public class ArrangementService {
	@Autowired
	private ArrangementDao repo;

	@Autowired
	private ClassRoomDao crepo;

	@Autowired
	private StudentDao srepo;

	public ResponseEntity<Arrangement> getSeating(String enroll){
		try {
			Arrangement a=repo.findById(enroll).orElse(null);
			if(a!=null) {
				return new ResponseEntity<>(a,HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

	}


	public ResponseEntity<List<Arrangement>> getByClassroom(String room){
		try {
			List<Arrangement> a=repo.findByClassroom(room);
			if(a!=null) {
				return new ResponseEntity<>(a,HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Arrangement>> getByBranchandSem(String branch,int sem){
		try {
			List<Arrangement> a=repo.findByBranchandSem(branch, sem);
			if(a!=null) {
				return new ResponseEntity<>(a,HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	public void setArrangementForCT(String room1, String room2, String room3, int sem1, int sem2, String examName,String branch) {
		List<Student> s1=srepo.findBySem(sem1);
		List<Student> s2=srepo.findBySem(sem2);
		int benchnumroom1=crepo.findByRoomnum(room1).getBenches();
		int benchnumroom2=crepo.findByRoomnum(room2).getBenches();
		int benchnumroom3=crepo.findByRoomnum(room3).getBenches();
		System.out.println(benchnumroom1);
		System.out.println(benchnumroom2);
		System.out.println(benchnumroom3);
		
		int sem1studindex=0;
		int sem2studindex=0;
		for(int i=1;i<=benchnumroom1;i++) {
			if(sem1studindex<s1.size()) {
				Arrangement a1=new Arrangement();
				Student studsem1=s1.get(sem1studindex);
				a1.setEnroll(studsem1.getEnroll());
				a1.setExam(examName);
				a1.setName(studsem1.getName());
				a1.setSem(sem1);
				
				a1.setBench(i);
				a1.setClassroom(room1);
				a1.setBranch(branch);
				repo.save(a1);
				sem1studindex++;
			}
			if(sem2studindex<s2.size()) {
				Arrangement a1=new Arrangement();
				Student studsem2=s2.get(sem2studindex);
				a1.setEnroll(studsem2.getEnroll());
				a1.setExam(examName);
				a1.setName(studsem2.getName());
				a1.setSem(sem2);
				
				a1.setBench(i);
				a1.setClassroom(room1);
				a1.setBranch(branch);
				repo.save(a1);
				sem2studindex++;
			}
			
			
			
		}
		
		
		for(int i=1;i<=benchnumroom2;i++) {
			if(sem1studindex<s1.size()) {
				Arrangement a1=new Arrangement();
				Student studsem1=s1.get(sem1studindex);
				a1.setEnroll(studsem1.getEnroll());
				a1.setExam(examName);
				a1.setName(studsem1.getName());
				a1.setSem(sem1);
				
				a1.setBench(i);
				a1.setClassroom(room2);
				a1.setBranch(branch);
				repo.save(a1);
				sem1studindex++;
			}
			if(sem2studindex<s2.size()) {
				Arrangement a1=new Arrangement();
				Student studsem2=s2.get(sem2studindex);
				a1.setEnroll(studsem2.getEnroll());
				a1.setExam(examName);
				a1.setName(studsem2.getName());
				a1.setSem(sem2);
				
				a1.setBench(i);
				a1.setClassroom(room2);
				a1.setBranch(branch);
				repo.save(a1);
				sem2studindex++;
			}
			
			
			
		}
		
		
		for(int i=1;i<=benchnumroom3;i++) {
			if(sem1studindex<s1.size()) {
				Arrangement a1=new Arrangement();
				Student studsem1=s1.get(sem1studindex);
				a1.setEnroll(studsem1.getEnroll());
				a1.setExam(examName);
				a1.setName(studsem1.getName());
				a1.setSem(sem1);
				
				a1.setBench(i);
				a1.setClassroom(room3);
				a1.setBranch(branch);
				repo.save(a1);
				sem1studindex++;
			}
			if(sem2studindex<s2.size()) {
				Arrangement a1=new Arrangement();
				Student studsem2=s2.get(sem2studindex);
				a1.setEnroll(studsem2.getEnroll());
				a1.setExam(examName);
				a1.setName(studsem2.getName());
				a1.setSem(sem2);
				
				a1.setBench(i);
				a1.setClassroom(room3);
				a1.setBranch(branch);
				
				repo.save(a1);
				
				sem2studindex++;
			}
			
			
			
		}
		
	


	}
}

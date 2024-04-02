package com.exam.ExamSeatingArrangement.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.exam.ExamSeatingArrangement.Dao.InvigilationDao;
import com.exam.ExamSeatingArrangement.Dao.TeacherDao;
import com.exam.ExamSeatingArrangement.Models.Invigilation;
import com.exam.ExamSeatingArrangement.Models.Teacher;

import jakarta.mail.internet.MimeMessage;

@Service
public class InvigilationService {
	
	@Autowired
	private TeacherDao repo;
	
	@Autowired
	private InvigilationDao Irepo;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
    private String fromEmail;
	
	public ResponseEntity<String> setInvigilation(String examName,String branch,String room1,String room2,String room3){
		try {
			Irepo.deleteAll();
			List<Teacher> t=repo.findByBranch(branch);
			
			int index=0;
			String roomnum=null;
			for(int i=0;i<3;i++) {
				Invigilation in=new Invigilation();
				List<String> tnames=new ArrayList<>();
				for(int j=0;j<2;j++) {
					Teacher teacher=t.get(index);
					tnames.add(teacher.getTname());
					if(i==0) {
						roomnum=room1;
						in.setClassromm(room1);
						
					}else if(i==1) {
						roomnum=room2;
						in.setClassromm(room2);
						
					}else {
						roomnum=room3;
						in.setClassromm(room3);
					}
					try {
						sendEmail(teacher.getMail(), teacher.getTname(),examName,roomnum);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
					index++;
				}
				in.setTeachers(tnames);
				in.setExamname(examName);
				in.setBranch(branch);
		
				
				Irepo.save(in);
				
			}
			return new ResponseEntity<>("Done",HttpStatus.OK);
			
			
			
		}catch(Exception e) {
			return new ResponseEntity<>("failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	public ResponseEntity<List<Invigilation>> getInvigilation(){
		try {
			List<Invigilation> i=Irepo.findAll();
			if(i!=null) {
				return new ResponseEntity<>(i,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public ResponseEntity<Invigilation> getInvigilationbyteacherId(String tid){
		try {
			
			Invigilation i=Irepo.findByTeachersContaining(tid);
			if(i!=null) {
				return new ResponseEntity<>(i,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	 public void sendEmail(String tEmail, String tname,String examname,String roomnum) {
	        
	        
	        try {
	        	MimeMessage mimeMessage = javaMailSender.createMimeMessage();

	            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

	            mimeMessageHelper.setFrom(fromEmail);
	            mimeMessageHelper.setTo(tEmail);
	            mimeMessageHelper.setSubject("Invigilation duty for Examname: "+examname);
	            mimeMessageHelper.setText("Dear "+tname + " your duty for "+examname + "is"+"classroom: "+roomnum);

	            
	            javaMailSender.send(mimeMessage);

	           

	        } catch (Exception e) {
	        	System.out.println(e);
	            throw new RuntimeException(e);
	        }
	        
	        
	    }

}

package com.exam.ExamSeatingArrangement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.exam.ExamSeatingArrangement.Dao.ArrangementDao;
import com.exam.ExamSeatingArrangement.Dao.ClassRoomDao;
import com.exam.ExamSeatingArrangement.Dao.StudentDao;
import com.exam.ExamSeatingArrangement.Models.Arrangement;
import com.exam.ExamSeatingArrangement.Models.ClassRoom;
import com.exam.ExamSeatingArrangement.Models.DataObj;
import com.exam.ExamSeatingArrangement.Models.Student;

import jakarta.mail.internet.MimeMessage;
@Service
public class ArrangementService {
	@Autowired
	private ArrangementDao repo;

	@Autowired
	private ClassRoomDao crepo;

	@Autowired
	private StudentDao srepo;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
    private String fromEmail;

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


	public ResponseEntity<DataObj> getByClassroom(String room){
		try {
			List<Arrangement> a=repo.findByClassroom(room);
			if(a!=null) {
				DataObj o=new DataObj();
				o.setArrangements(a);
				ClassRoom c=crepo.findByRoomnum(room);
				o.setRowsNum(c.getRowsInClass());
				o.setColumnsNum(c.getColumnsInClass());
				return new ResponseEntity<>(o,HttpStatus.ACCEPTED);
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


	public ResponseEntity<List<Arrangement>> getArrangements(){
		return new ResponseEntity<>(repo.findAll(),HttpStatus.OK);
	}
	
//	public void setRowsAndColumns(int rows,int columns,String room){
//		List<Arrangement> arrangements=repo.findByClassroom(room);
//		int a=1;
//		int columnnum=1;
//		for(int i=1;i<=rows;i++) {
//			for(int j=1;j<=columns*2 && !arrangements.isEmpty();j++) {
//				Arrangement a1=arrangements.get(a);
//				a1.setRowNum(i);
//				a1.setColumnNum(columnnum);
//				a++;
//				if(j%2==0) {
//					columnnum++;
//				}
//				
//				
//			}
//			
//		}
//		
//		
//		
//	}



	public void setArrangementForCT(String room1, String room2, String room3, int sem1, int sem2, String examName, String branch1,String branch2, int rows1, int columns1, int rows2, int columns2, int rows3, int columns3) {

	    repo.deleteAll();
	    ClassRoom c1=crepo.findByRoomnum(room1);
	    ClassRoom c2=crepo.findByRoomnum(room2);
	    ClassRoom c3=crepo.findByRoomnum(room3);
	    c1.setRowsInClass(rows1);
	    c1.setColumnsInClass(columns1);
	    c2.setRowsInClass(rows2);
	    c2.setColumnsInClass(columns2);
	    c3.setRowsInClass(rows3);
	    c3.setColumnsInClass(columns3);
	    
	    List<Student> s1 = srepo.findBySemandBranch(sem1, branch1);
	    System.out.println(s1);

	    List<Student> s2 = srepo.findBySemandBranch(sem2, branch2);
	    System.out.println(s2);

//	    int benchnumroom1 = crepo.findByRoomnum(room1).getBenches();
//	    int benchnumroom2 = crepo.findByRoomnum(room2).getBenches();
//	    int benchnumroom3 = crepo.findByRoomnum(room3).getBenches();

	    int sem1studindex = 0;
	    int sem2studindex = 0;

	    // Allocate for room 1
	    for (int i = 1; i <= rows1; i++) {
	        for (int j = 1; j <= columns1; j++) {
	            if (sem1studindex < s1.size() && sem2studindex < s2.size()) {
	                try {
	                    Arrangement a1 = new Arrangement();
	                    Student studsem1 = s1.get(sem1studindex);
	                    a1.setRollnum(studsem1.getExamroll());
	                    a1.setExam(examName);
	                    a1.setName(studsem1.getName());
	                    a1.setSem(sem1);
	                   // a1.setBench(i); // Assuming 'i' represents the row number
	                    a1.setRowNum(i); // Set the row
	                    a1.setColumnNum(j); // Set the column
	                    a1.setClassroom(room1);
	                    a1.setBranch(branch1);
	              //      sendEmail(studsem1.getMail(), studsem1.getName(), examName, room1, i, j); // Send email with row and column
	                    repo.save(a1);
	                    sem1studindex++;
	                } catch (Exception e) {
	                    // Handle exception
	                }

	                try {
	                    Arrangement a2 = new Arrangement();
	                    Student studsem2 = s2.get(sem2studindex);
	                    a2.setRollnum(studsem2.getExamroll());
	                    a2.setExam(examName);
	                    a2.setName(studsem2.getName());
	                    a2.setSem(sem2);
	                 //   a2.setBench(i); // Assuming 'i' represents the row number
	                    a2.setRowNum(i); // Set the row
	                    a2.setColumnNum(j); // Set the column
	                    a2.setClassroom(room1);
	                    a2.setBranch(branch2);
	               //     sendEmail(studsem2.getMail(), studsem2.getName(), examName, room1, i, j); // Send email with row and column
	                    repo.save(a2);
	                    sem2studindex++;
	                } catch (Exception e) {
	                    // Handle exception
	                }
	            }else if(sem1studindex < s1.size()) {
	            	try {
	                    Arrangement a1 = new Arrangement();
	                    Student studsem1 = s1.get(sem1studindex);
	                    a1.setRollnum(studsem1.getExamroll());
	                    a1.setExam(examName);
	                    a1.setName(studsem1.getName());
	                    a1.setSem(sem1);
	                   // a1.setBench(i); // Assuming 'i' represents the row number
	                    a1.setRowNum(i); // Set the row
	                    a1.setColumnNum(j); // Set the column
	                    a1.setClassroom(room1);
	                    a1.setBranch(branch1);
	              //      sendEmail(studsem1.getMail(), studsem1.getName(), examName, room1, i, j); // Send email with row and column
	                    repo.save(a1);
	                    sem1studindex++;
	                } catch (Exception e) {
	                    // Handle exception
	                }
	            	
	            }else if(sem2studindex < s2.size()) {
	            	 try {
		                    Arrangement a2 = new Arrangement();
		                    Student studsem2 = s2.get(sem2studindex);
		                    a2.setRollnum(studsem2.getExamroll());
		                    a2.setExam(examName);
		                    a2.setName(studsem2.getName());
		                    a2.setSem(sem2);
		                 //   a2.setBench(i); // Assuming 'i' represents the row number
		                    a2.setRowNum(i); // Set the row
		                    a2.setColumnNum(j); // Set the column
		                    a2.setClassroom(room1);
		                    a2.setBranch(branch2);
		               //     sendEmail(studsem2.getMail(), studsem2.getName(), examName, room1, i, j); // Send email with row and column
		                    repo.save(a2);
		                    sem2studindex++;
		                } catch (Exception e) {
		                    // Handle exception
		                }
	            }
	        }
	    }
	   

	    // Allocate for room 2
	    for (int i = 1; i <= rows2; i++) {
	        for (int j = 1; j <= columns2; j++) {
	            if (sem1studindex < s1.size() && sem2studindex < s2.size()) {
	                try {
	                    Arrangement a1 = new Arrangement();
	                    Student studsem1 = s1.get(sem1studindex);
	                    a1.setRollnum(studsem1.getExamroll());
	                    a1.setExam(examName);
	                    a1.setName(studsem1.getName());
	                    a1.setSem(sem1);
	                  //  a1.setBench(i); // Assuming 'i' represents the row number
	                    a1.setRowNum(i); // Set the row
	                    a1.setColumnNum(j); // Set the column
	                    a1.setClassroom(room2);
	                    a1.setBranch(branch1);
	                  //  sendEmail(studsem1.getMail(), studsem1.getName(), examName, room2, i, j); // Send email with row and column
	                    repo.save(a1);
	                    sem1studindex++;
	                } catch (Exception e) {
	                    // Handle exception
	                }

	                try {
	                    Arrangement a2 = new Arrangement();
	                    Student studsem2 = s2.get(sem2studindex);
	                    a2.setRollnum(studsem2.getExamroll());;
	                    a2.setExam(examName);
	                    a2.setName(studsem2.getName());
	                    a2.setSem(sem2);
	                 //   a2.setBench(i); // Assuming 'i' represents the row number
	                    a2.setRowNum(i); // Set the row
	                    a2.setColumnNum(j); // Set the column
	                    a2.setClassroom(room2);
	                    a2.setBranch(branch2);
	                 //   sendEmail(studsem2.getMail(), studsem2.getName(), examName, room2, i, j); // Send email with row and column
	                    repo.save(a2);
	                    sem2studindex++;
	                } catch (Exception e) {
	                    // Handle exception
	                }
	            }else if(sem1studindex < s1.size()) {
	            	 try {
		                    Arrangement a1 = new Arrangement();
		                    Student studsem1 = s1.get(sem1studindex);
		                    a1.setRollnum(studsem1.getExamroll());
		                    a1.setExam(examName);
		                    a1.setName(studsem1.getName());
		                    a1.setSem(sem1);
		                  //  a1.setBench(i); // Assuming 'i' represents the row number
		                    a1.setRowNum(i); // Set the row
		                    a1.setColumnNum(j); // Set the column
		                    a1.setClassroom(room2);
		                    a1.setBranch(branch1);
		                  //  sendEmail(studsem1.getMail(), studsem1.getName(), examName, room2, i, j); // Send email with row and column
		                    repo.save(a1);
		                    sem1studindex++;
		                } catch (Exception e) {
		                    // Handle exception
		                }
	            	
	            }else if(sem2studindex < s2.size()) {
	            	try {
	                    Arrangement a2 = new Arrangement();
	                    Student studsem2 = s2.get(sem2studindex);
	                    a2.setRollnum(studsem2.getExamroll());;
	                    a2.setExam(examName);
	                    a2.setName(studsem2.getName());
	                    a2.setSem(sem2);
	                 //   a2.setBench(i); // Assuming 'i' represents the row number
	                    a2.setRowNum(i); // Set the row
	                    a2.setColumnNum(j); // Set the column
	                    a2.setClassroom(room2);
	                    a2.setBranch(branch2);
	                 //   sendEmail(studsem2.getMail(), studsem2.getName(), examName, room2, i, j); // Send email with row and column
	                    repo.save(a2);
	                    sem2studindex++;
	                } catch (Exception e) {
	                    // Handle exception
	                }
	            }
	        }
	    }

	    // Allocate for room 3
	    for (int i = 1; i <= rows3; i++) {
	        for (int j = 1; j <= columns3; j++) {
	            if (sem1studindex < s1.size() && sem2studindex < s2.size()) {
	                try {
	                    Arrangement a1 = new Arrangement();
	                    Student studsem1 = s1.get(sem1studindex);
	                    a1.setRollnum(studsem1.getExamroll());
	                    a1.setExam(examName);
	                    a1.setName(studsem1.getName());
	                    a1.setSem(sem1);
	                 //   a1.setBench(i); // Assuming 'i' represents the row number
	                    a1.setRowNum(i); // Set the row
	                    a1.setColumnNum(j); // Set the column
	                    a1.setClassroom(room3);
	                    a1.setBranch(branch1);
	                  //  sendEmail(studsem1.getMail(), studsem1.getName(), examName, room3, i, j); // Send email with row and column
	                    repo.save(a1);
	                    sem1studindex++;
	                } catch (Exception e) {
	                    // Handle exception
	                }

	                try {
	                    Arrangement a2 = new Arrangement();
	                    Student studsem2 = s2.get(sem2studindex);
	                    a2.setRollnum(studsem2.getExamroll());
	                    a2.setExam(examName);
	                    a2.setName(studsem2.getName());
	                    a2.setSem(sem2);
	               //     a2.setBench(i); // Assuming 'i' represents the row number
	                    a2.setRowNum(i); // Set the row
	                    a2.setColumnNum(j); // Set the column
	                    a2.setClassroom(room3);
	                    a2.setBranch(branch2);
	                //    sendEmail(studsem2.getMail(), studsem2.getName(), examName, room3, i, j); // Send email with row and column
	                    repo.save(a2);
	                    sem2studindex++;
	                } catch (Exception e) {
	                    // Handle exception
	                }
	            }else if(sem1studindex < s1.size()) {
	            	 try {
		                    Arrangement a1 = new Arrangement();
		                    Student studsem1 = s1.get(sem1studindex);
		                    a1.setRollnum(studsem1.getExamroll());
		                    a1.setExam(examName);
		                    a1.setName(studsem1.getName());
		                    a1.setSem(sem1);
		                 //   a1.setBench(i); // Assuming 'i' represents the row number
		                    a1.setRowNum(i); // Set the row
		                    a1.setColumnNum(j); // Set the column
		                    a1.setClassroom(room3);
		                    a1.setBranch(branch1);
		                  //  sendEmail(studsem1.getMail(), studsem1.getName(), examName, room3, i, j); // Send email with row and column
		                    repo.save(a1);
		                    sem1studindex++;
		                } catch (Exception e) {
		                    // Handle exception
		                }
	            }else if(sem2studindex < s2.size()) {
	            	try {
	                    Arrangement a2 = new Arrangement();
	                    Student studsem2 = s2.get(sem2studindex);
	                    a2.setRollnum(studsem2.getExamroll());
	                    a2.setExam(examName);
	                    a2.setName(studsem2.getName());
	                    a2.setSem(sem2);
	               //     a2.setBench(i); // Assuming 'i' represents the row number
	                    a2.setRowNum(i); // Set the row
	                    a2.setColumnNum(j); // Set the column
	                    a2.setClassroom(room3);
	                    a2.setBranch(branch2);
	                //    sendEmail(studsem2.getMail(), studsem2.getName(), examName, room3, i, j); // Send email with row and column
	                    repo.save(a2);
	                    sem2studindex++;
	                } catch (Exception e) {
	                    // Handle exception
	                }
	            }
	        }
	    }
	}


	public ResponseEntity<List<Arrangement>> getByBranch(String branch){
		try {
			List<Arrangement> seating=repo.findByBranch(branch);
			if(seating==null) {
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(seating,HttpStatus.OK);
				
			}
			
		}catch(Exception e) {
			
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Integer> getByBranchandClassroom(String branch,String classroom){
		try {
			List<Arrangement> seating=repo.findByBranchandRoom(branch, classroom);
			if(seating==null) {
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(seating.size(),HttpStatus.OK);
				
			}
			
		}catch(Exception e) {
			
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<List<Arrangement>> getByBranchClassRoomRownum(String branch,String classRoom,String rowNum){
		try {
			List<Arrangement> seating=repo.findByBranchandRoomandRow(branch, classRoom, rowNum);
			if(seating==null) {
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(seating,HttpStatus.OK);
				
			}
			
		}catch(Exception e) {
			
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<List<Arrangement>> getByBranchClassRoomColumnnum(String branch,String classRoom,String columnNum){
		try {
			List<Arrangement> seating=repo.findByBranchandRoomandRow(branch, classRoom, columnNum);
			if(seating==null) {
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(seating,HttpStatus.OK);
				
			}
			
		}catch(Exception e) {
			
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public void sendEmail(String studentEmail, String studName, String examName, String roomNum, int benchNum) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(studentEmail);
            mimeMessageHelper.setSubject("Seating Arrangement for Exam: " + examName);
            mimeMessageHelper.setText("Dear " + studName + ", your seating arrangement for " + examName +
                    " is classroom: " + roomNum + ", bench number: " + benchNum);
            javaMailSender.send(mimeMessage);
            System.out.println("Email sent to " + studName);
        } catch (Exception e) {
            System.out.println("Mail server connection failed. Attempting reconnection...");
            
        }
    }
	
	
	
	
	
	
	
	
}

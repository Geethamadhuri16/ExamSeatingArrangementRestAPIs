package com.exam.ExamSeatingArrangement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.ExamSeatingArrangement.Models.Admin;
import com.exam.ExamSeatingArrangement.Service.AdminService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class AdminController {
	@Autowired
	private AdminService service;
	
	@RequestMapping("/alogin/{mail}/{pass}")
	public ResponseEntity<Boolean> alogin(@PathVariable("mail") String mail,@PathVariable("pass") String password) {
		return service.validate(mail, password);
		
		
	}
	
	@RequestMapping(path="/aregister",consumes="application/json",produces="application/xml")
	public ResponseEntity<String> register(@ RequestBody Admin a) {
		return service.register(a);
		
	}

}

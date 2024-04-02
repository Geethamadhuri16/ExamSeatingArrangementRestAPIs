package com.exam.ExamSeatingArrangement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exam.ExamSeatingArrangement.Models.Invigilation;
import com.exam.ExamSeatingArrangement.Service.InvigilationService;

@RestController
@RequestMapping("/invigilation")
@CrossOrigin(origins="http://localhost:3000")
public class InvigilationController {

    @Autowired
    private InvigilationService invigilationService;

    @PostMapping("/setInvigilation/{ename}/{branch}/{room1}/{room2}/{room3}")
    public ResponseEntity<String> setInvigilation(
    		@PathVariable String ename,
            @PathVariable String branch,
            @PathVariable String room1,
            @PathVariable String room2,
            @PathVariable String room3) {
    	
        return invigilationService.setInvigilation( ename, branch, room1, room2, room3);
    }

    @GetMapping("/getInvigilation")
    public ResponseEntity<List<Invigilation>> getInvigilation(
    		) {
        return invigilationService.getInvigilation();
    }

    @GetMapping("/getByTeacherId")
    public ResponseEntity<?> getInvigilationByTeacherId(
            @RequestParam String teacherId) {
        return invigilationService.getInvigilationbyteacherId(teacherId);
    }
}

package com.exam.ExamSeatingArrangement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exam.ExamSeatingArrangement.Models.Teacher;
import com.exam.ExamSeatingArrangement.Service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/register")
    public ResponseEntity<String> registerTeacher(@RequestBody Teacher teacher) {
        return teacherService.register(teacher);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginTeacher(@RequestParam String mail, @RequestParam String pass) {
        return teacherService.login(mail, pass);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return teacherService.getallTeachers();
    }

    @GetMapping("/byBranch")
    public ResponseEntity<List<Teacher>> getTeachersByBranch(@RequestParam String branch) {
        return teacherService.getTeachersByBranch(branch);
    }

    @GetMapping("/teacherId")
    public ResponseEntity<Teacher> getTeacherById(@RequestParam String teacherId) {
        return teacherService.getTeacher(teacherId);
    }
}

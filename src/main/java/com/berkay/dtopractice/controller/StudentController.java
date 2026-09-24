package com.berkay.dtopractice.controller;

import ch.qos.logback.core.model.Model;
import com.berkay.dtopractice.entity.Student;
import com.berkay.dtopractice.entity.dto.StudentRequest;
import com.berkay.dtopractice.entity.dto.StudentResponse;
import com.berkay.dtopractice.mapper.StudentMapper;
import com.berkay.dtopractice.repository.StudentRepository;
import com.berkay.dtopractice.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> saveStudent(@RequestBody StudentRequest studentRequest) {

StudentResponse studentResponse = studentService.createStudent(studentRequest);
return ResponseEntity.status(HttpStatus.CREATED).body(studentResponse);

    }


}

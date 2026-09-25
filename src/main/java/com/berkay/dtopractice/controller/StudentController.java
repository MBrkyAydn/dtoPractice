package com.berkay.dtopractice.controller;

import com.berkay.dtopractice.dto.StudentRequest;
import com.berkay.dtopractice.dto.StudentResponse;
import com.berkay.dtopractice.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> responses = studentService.getallStudents();
        if (!responses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(responses);

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responses);


    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Integer id, @RequestBody StudentRequest studentRequest) {
        StudentResponse response = studentService.updateStudent(id, studentRequest);
        if (response != null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


}

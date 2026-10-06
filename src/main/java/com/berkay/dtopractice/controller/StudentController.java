package com.berkay.dtopractice.controller;

import com.berkay.dtopractice.dto.StudentRequest;
import com.berkay.dtopractice.dto.StudentResponse;
import com.berkay.dtopractice.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<List<StudentResponse>> getAllStu() {
        List<StudentResponse> responses = studentService.getAllStu();
        if (!responses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(responses);

        }
        return ResponseEntity.notFound().build();


    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Integer id, @RequestBody StudentRequest studentRequest) {
        StudentResponse response = studentService.updateStudent(id, studentRequest);
        if (response != null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Integer id) {

        StudentResponse response = studentService.findById(id);
        if (response != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }
        return ResponseEntity.notFound().build();

    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentResponse> patchStudent(@PathVariable Integer id, @RequestBody StudentRequest studentRequest) {
        StudentResponse response = studentService.updateParty(id, studentRequest);
        if (response != null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();


    }

    @PostMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<StudentResponse> addCourseToStudent(
            @PathVariable Integer studentId,
            @PathVariable Integer courseId) {

        StudentResponse response =
                studentService.addCourseToStudent(studentId, courseId);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{studentid}/courses/{courseid}")
    public ResponseEntity<StudentResponse> deleteCourseFromStudent(@PathVariable Integer studentid, @PathVariable Integer courseid) {

        StudentResponse response = studentService.deleteCourseFromStudent(studentid, courseid);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping
    public ResponseEntity<Page<StudentResponse>> getAllStudents(Pageable pageable) {
        return ResponseEntity.ok(studentService.getallStudents(pageable));
        /// GET /students?page=0&size=10
    }
    @GetMapping("/department/{department}")
    public ResponseEntity<List<StudentResponse>> getStudentsByDepartment(
            @PathVariable String department) {

        return ResponseEntity.ok(
                studentService.getStudentsByDepartment(department)
        );
    }
    @GetMapping("/department-jpql/{department}")
    public ResponseEntity<List<StudentResponse>> getStudentsByDepartmentJpql(
            @PathVariable String department) {

        return ResponseEntity.ok(
                studentService.getStudentsByDepartmentJpql(department)
        );
    }
}
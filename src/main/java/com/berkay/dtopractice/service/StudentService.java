package com.berkay.dtopractice.service;

import com.berkay.dtopractice.entity.Course;
import com.berkay.dtopractice.entity.Student;
import com.berkay.dtopractice.dto.StudentRequest;
import com.berkay.dtopractice.dto.StudentResponse;
import com.berkay.dtopractice.mapper.StudentMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.berkay.dtopractice.repository.CourseRepository;
import com.berkay.dtopractice.repository.StudentRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class StudentService {
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentMapper studentMapper, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public StudentResponse createStudent(StudentRequest studentRequest) {

        Student student = studentMapper.toEntity(studentRequest);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toResponse(savedStudent);
// studentmapperdeki toEntity metoduna gelen client verisini gönderir. bu işlem elimizde Student student olarak tutulur.
        //studentRepositry save metoduna bu student metodu gönderilir ve bunada savedStudent denir.
        // geriye studentmapper deki respone metoduna savedStudent gönderilir.

        // İnput > createStudent > student = studentMapper.toEntity(studentRequest); >to entitye gider
        // student> savedStudent= studentRepository.save(student); > repositoryde kayıt edilir ve geriye döndürülen >>studentMapper.toResponse(savedStudent);

    }

    public List<StudentResponse> getAllStu() {
        List<Student> students =studentRepository.findAll();
        return students.stream().map(studentMapper::toResponse).toList();
    }

    public Page<StudentResponse> getallStudents(Pageable pageable) {

        Page<Student> students = studentRepository.findAll(pageable);
        return students.map(studentMapper::toResponse);
    }

    public StudentResponse updateStudent(Integer id, StudentRequest studentRequest) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("student not found"));
        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setDepartment(studentRequest.getDepartment());

        Student update = studentRepository.save(student);
        return studentMapper.toResponse(update);


    }

    public StudentResponse findById(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("student not found"));
        return studentMapper.toResponse(student);

    }

    public StudentResponse updateParty(Integer id, StudentRequest studentRequest) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("student not found"));

        if (studentRequest.getName() != null) {
            student.setName(studentRequest.getName());
        }
        if (studentRequest.getEmail() != null) {
            student.setEmail(studentRequest.getEmail());

        }
        if (studentRequest.getDepartment() != null) {
            student.setDepartment(studentRequest.getDepartment());
        }
        Student update = studentRepository.save(student);
        return studentMapper.toResponse(update);
    }

    public void deleteStudent(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("student not found"));
        studentRepository.delete(student);

    }

    public StudentResponse addCourseToStudent(Integer studentId, Integer courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        student.getCourses().add(course);

        Student updatedStudent = studentRepository.save(student);

        return studentMapper.toResponse(updatedStudent);
    }

    public StudentResponse deleteCourseFromStudent(Integer studentId, Integer courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        student.getCourses().remove(course);
        Student updatedStudent = studentRepository.save(student);
        return studentMapper.toResponse(updatedStudent);


    }
    public List<StudentResponse> getStudentsByDepartment(String department) {
        return studentRepository.findByDepartment(department)
                .stream()
                .map(studentMapper::toResponse)
                .toList();
    }
    public List<StudentResponse> getStudentsByDepartmentJpql(String department) {
        return studentRepository.findByDepartmentJpql(department)
                .stream()
                .map(studentMapper::toResponse)
                .toList();
    }
}

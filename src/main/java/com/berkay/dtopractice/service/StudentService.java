package com.berkay.dtopractice.service;

import com.berkay.dtopractice.entity.Student;
import com.berkay.dtopractice.entity.dto.StudentRequest;
import com.berkay.dtopractice.entity.dto.StudentResponse;
import com.berkay.dtopractice.mapper.StudentMapper;

import com.berkay.dtopractice.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    public StudentService(StudentMapper studentMapper, StudentRepository studentRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;

    }
    public StudentResponse createStudent(StudentRequest  studentRequest) {
        Student student = studentMapper.toEntity(studentRequest);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toResponse(savedStudent);
// studentmapperdeki toEntity metoduna gelen client verisini gönderir. bu işlem elimizde Student student olarak tutulur.
        //studentRepositry save metoduna bu student metodu gönderilir ve bunada savedStudent denir.
        // geriye studentmapper deki respone metoduna savedStudent gönderilir.

        // İnput > createStudent > student = studentMapper.toEntity(studentRequest); >to entitye gider
        // student> savedStudent= studentRepository.save(student); > repositoryde kayıt edilir ve geriye döndürülen >>studentMapper.toResponse(savedStudent);

    }
public List<StudentResponse> getallStudents(){
    List<Student> students =studentRepository.findAll();
    return students.stream().map(studentMapper::toResponse).toList();
}
}

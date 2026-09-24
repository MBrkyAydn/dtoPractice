package com.berkay.dtopractice.mapper;

import com.berkay.dtopractice.entity.Student;
import com.berkay.dtopractice.entity.dto.StudentRequest;
import com.berkay.dtopractice.entity.dto.StudentResponse;
import org.springframework.stereotype.Component;

@Component // sınıfı spring BEan olarak yönetsin
/// Spring otomatik olarak Mapper nesnesini Service'e verir.

public class StudentMapper {

    public Student toEntity(StudentRequest studentRequest) {
        return Student.builder()
                .name(studentRequest.getName())
                .email(studentRequest.getEmail())
                .department(studentRequest.getDepartment())
                .build();

    } /// clientten gelen veriler önce StudentRequest içine gelir.

 public StudentResponse toResponse(Student student) {
     return StudentResponse.builder()
             .id(student.getId())
             .name(student.getName())
             .email(student.getEmail())
             .department(student.getDepartment())
             .build();

 } // Studenten gelen veriyi to responone olarak tutar.

}

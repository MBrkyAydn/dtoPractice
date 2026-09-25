package com.berkay.dtopractice.mapper;


import com.berkay.dtopractice.dto.CourseRequest;
import com.berkay.dtopractice.dto.CourseResponse;
import com.berkay.dtopractice.dto.StudentRequest;
import com.berkay.dtopractice.entity.Course;
import com.berkay.dtopractice.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequest courseRequest) {
        return Course.builder()
                .title(courseRequest.getTitle())
                .description(courseRequest.getDescription())
                .build();
    }
public CourseResponse toResponse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
}
}

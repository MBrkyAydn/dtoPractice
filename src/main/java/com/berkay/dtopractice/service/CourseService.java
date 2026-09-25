package com.berkay.dtopractice.service;

import com.berkay.dtopractice.dto.CourseRequest;
import com.berkay.dtopractice.dto.CourseResponse;
import com.berkay.dtopractice.entity.Course;
import com.berkay.dtopractice.mapper.CourseMapper;
import com.berkay.dtopractice.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CourseService {
    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;

    public CourseService(CourseMapper courseMapper, CourseRepository courseRepository) {
        this.courseMapper = courseMapper;
        this.courseRepository = courseRepository;
    }

    public CourseResponse createCourse(CourseRequest courseRequest) {
        Course course = courseMapper.toEntity(courseRequest);
        Course saved = courseRepository.save(course);
        return courseMapper.toResponse(saved);
    }

    public CourseResponse updateCourse(Integer id, CourseRequest courseRequest) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("course not found"));
        course.setTitle(courseRequest.getTitle());
        course.setDescription(courseRequest.getDescription());
        Course update = courseRepository.save(course);
        return courseMapper.toResponse(update);

    }

    public void deleteCourse(Integer id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("course not found"));
        courseRepository.delete(course);

    }

    public CourseResponse findCourseById(Integer id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("course not found"));
        return courseMapper.toResponse(course);

    }

    public List<CourseResponse> findallCourse() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(courseMapper::toResponse).toList();
    }

    public CourseResponse updateCoursePart(Integer id, CourseRequest courseRequest) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("course not found"));
        if (course.getTitle() != null) {
            course.setTitle(courseRequest.getTitle());
        }
        if (course.getDescription() != null) {
            course.setDescription(courseRequest.getDescription());
        }
        Course update =courseRepository.save(course);
        return courseMapper.toResponse(update);

    }
}

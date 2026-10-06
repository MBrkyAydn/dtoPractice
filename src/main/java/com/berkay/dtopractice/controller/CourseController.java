package com.berkay.dtopractice.controller;

import com.berkay.dtopractice.dto.CourseRequest;
import com.berkay.dtopractice.dto.CourseResponse;
import com.berkay.dtopractice.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getCourses() {
        List<CourseResponse> courses = courseService.findallCourse();
        if (!courses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(courses);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Integer id) {
        if (id == null) {
            return ResponseEntity.notFound().build();
        }
        CourseResponse course = courseService.findCourseById(id);
        return ResponseEntity.status(HttpStatus.OK).body(course);
    }

    @PostMapping
    public ResponseEntity<CourseResponse> addCourse(@RequestBody CourseRequest courseRequest) {
        CourseResponse course = courseService.createCourse(courseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@RequestBody CourseRequest courseRequest, @PathVariable Integer id) {
        CourseResponse course = courseService.updateCourse(id, courseRequest);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(course);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CourseResponse> patchCourse(@RequestBody CourseRequest courseRequest, @PathVariable Integer id) {
        CourseResponse course = courseService.updateCoursePart(id, courseRequest);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable Integer id) {
       courseService.deleteCourse(id);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

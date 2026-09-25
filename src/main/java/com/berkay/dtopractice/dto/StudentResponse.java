package com.berkay.dtopractice.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    private Integer id;
    private String name;
    private String email;
    private String department;
    private List<CourseResponse> courses;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}

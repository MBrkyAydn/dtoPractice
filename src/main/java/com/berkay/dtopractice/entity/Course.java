package com.berkay.dtopractice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;

    @Builder.Default
    @ManyToMany(mappedBy = "courses",fetch = FetchType.LAZY)
    private List<Student> students =  new ArrayList<>();




}

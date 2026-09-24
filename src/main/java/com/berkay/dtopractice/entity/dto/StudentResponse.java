package com.berkay.dtopractice.entity.dto;

import lombok.*;

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
}

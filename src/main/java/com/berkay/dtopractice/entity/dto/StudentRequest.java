package com.berkay.dtopractice.entity.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest { //kullanıcının girecekleri // id clıent göndermeyecek.
    private String name;
    private String email;
    private String department;
}

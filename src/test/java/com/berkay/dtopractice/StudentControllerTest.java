package com.berkay.dtopractice;

import com.berkay.dtopractice.controller.StudentController;
import com.berkay.dtopractice.dto.StudentRequest;
import com.berkay.dtopractice.dto.StudentResponse;
import com.berkay.dtopractice.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(StudentController.class) //Yalnızca MVC/Controller katmanını test etmek için gerekli Spring yapısını yükler.
public class StudentControllerTest {
@Autowired
    private MockMvc mockMvc; //Gerçek sunucuyu başlatmadan HTTP isteği göndermemizi sağlar.
@MockitoBean //Spring test ortamındaki StudentService bağımlılığını mock ile değiştirir.
private StudentService studentService;

    @Test
    void ogrenciListesiGetirTesti() throws Exception {

        when(studentService.getAllStu())
                .thenReturn(List.of());

        mockMvc.perform(get("/student"))
                .andExpect(status().isNotFound());
    }


    @Test
    void ogrenciListesiDoluysaOkDonmeli() throws Exception {

        StudentResponse response = new StudentResponse();

        when(studentService.getAllStu())
                .thenReturn(List.of(response));

        mockMvc.perform(get("/student"))
                .andExpect(status().isOk());
    }

    @Test
    void ogrenciOlusturmaTesti() throws Exception {

        StudentRequest request = new StudentRequest();
        StudentResponse response = new StudentResponse();

        when(studentService.createStudent(any(StudentRequest.class)))

                .thenReturn(response);

        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());

        verify(studentService).createStudent(any(StudentRequest.class));
    }

    @Test
    void idIleOgrenciBulunursaOkDonmeli() throws Exception {

        StudentResponse response = new StudentResponse();

        when(studentService.findById(1))
                .thenReturn(response);

        mockMvc.perform(get("/student/1"))
                .andExpect(status().isFound());
    }

    @Test
    void ogrenciBulunamazsaNotFoundDonmeli() throws Exception {

        when(studentService.findById(999))
                .thenReturn(null);

        mockMvc.perform(get("/student/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void tokenOlmadanOgrenciyeErisilememeli() throws Exception {

        mockMvc.perform(get("/student"))
                .andExpect(status().isUnauthorized());
    }

}

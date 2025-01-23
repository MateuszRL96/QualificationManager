package com.qulificationRecomendation.qulificationRecomendation.Controllers;

import com.qulificationRecomendation.qulificationRecomendation.DTO.AddQualificationRequest;
import com.qulificationRecomendation.qulificationRecomendation.Services.UserQualificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserQualificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserQualificationService userQualificationService;

//    @Test
//    public void testAddQualificationToUser() throws Exception {
//        AddQualificationRequest request = new AddQualificationRequest();
//        request.setUserId(1L);
//        request.setQualificationId(1L);
//        request.setLevel(3);
//
//        mockMvc.perform(post("/api/user-qualifications/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"userId\":1,\"qualificationId\":1,\"level\":5}"))
//                .andExpect(status().isOk());
//    }
}
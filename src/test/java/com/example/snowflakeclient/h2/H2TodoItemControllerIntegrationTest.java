package com.example.snowflakeclient.h2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class H2TodoItemControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void h2JPA_backed_controller_works() throws Exception {
        // Create
        mockMvc.perform(
                        post("/api/todos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"title\":\"first\"}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("first"))
                .andExpect(jsonPath("$.id").isNumber());

        // List
        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("first"));
    }
}


package com.itschool.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itschool.springbootdeveloper.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class MockMvcTest<T, ID> {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    JpaRepository<T, ID> baseRepository;

    @BeforeEach
    public void setMockMvcSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        baseRepository.deleteAll();
    }
}

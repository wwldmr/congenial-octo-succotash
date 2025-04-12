package com.example.demo;


import com.example.demo.controller.EmployeeController;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setFirstName("Ivan");
        employeeDto.setLastName("Ivanov");
        employeeDto.setEmail("123@qwe.com");
    }

    @Test
    public void createEmployee_ShouldReturnCreatedEmployee() throws Exception {
        when(employeeService.createEmployee(any(EmployeeDto.class)))
                .thenReturn(employeeDto);

        mockMvc.perform(post("/api/employees/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(employeeDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(employeeDto.getLastName())))
                .andExpect(jsonPath("$.email", is(employeeDto.getEmail())));
    }

    @Test
    public void getEmployeeById_ShouldReturnEmployee() throws Exception {
        Long employeeId = 1L;
        when(employeeService.getEmployeeById(employeeId))
                .thenReturn(employeeDto);

        mockMvc.perform(get("/api/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(employeeDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(employeeDto.getLastName())))
                .andExpect(jsonPath("$.email", is(employeeDto.getEmail())));
    }
}
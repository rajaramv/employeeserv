package com.paypal.bfs.test.employeeserv.controller;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.exception.DuplicateEmployeeException;
import com.paypal.bfs.test.employeeserv.api.exception.EmployeeNotFoundExecption;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.DuplicateFormatFlagsException;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeResource.class)
public class EmployeeRestResourceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService service;

    static String toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(object);
    }



    @Test
    public void getEmployeeDetailsTest () throws Exception{
        Employee employeeWSO = new Employee();
        employeeWSO.setId("1");
        employeeWSO.setFirstName("Rajaram");
        employeeWSO.setLastName("Vishwa");
        //employeeWSO.setDateOfBirth( LocalDate.of(1990,12,10));
        //Should have used builder :(
        Address add = new Address();
        add.setAddressLine1("333");
        add.setCity("BLR");
        add.setCountry("IN");
        add.setState("KA");
        add.setZipCode(56010);
        employeeWSO.setAddress(add);
        given(service.byId(Mockito.any())).willReturn(Optional.of(employeeWSO));

        String body = toJson(employeeWSO);
        mvc.perform(get("/v1/bfs/employees/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.firstName", is("Rajaram")));

    }

    @Test
    public void whenGetInvalidEmployee_thenReturnEmployeeNotFound() throws Exception{
        given(service.byId(Mockito.any())).willThrow(new EmployeeNotFoundExecption("Not found"));
        mvc.perform(get("/v1/bfs/employees/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andExpect(jsonPath("$.message", is("Not found")));

    }

    @Test
    public void whenPostEmployee_thenCreateEmployeeTest () throws Exception{
        Employee employeeWSO = new Employee();
        employeeWSO.setId("1");
        employeeWSO.setFirstName("Rajaram");
        employeeWSO.setLastName("Vishwa");
        //employeeWSO.setDateOfBirth( LocalDate.of(1990,12,10));
        //Should have used builder :(
        Address add = new Address();
        add.setAddressLine1("333");
        add.setCity("BLR");
        add.setCountry("IN");
        add.setState("KA");
        add.setZipCode(56010);
        employeeWSO.setAddress(add);
        given(service.create(Mockito.any())).willReturn(employeeWSO);

        String body = toJson(employeeWSO);
        mvc.perform(post("/v1/bfs/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isCreated()).andExpect(header().exists(HttpHeaders.LOCATION));

    }

    @Test
    public void whenPostDuplicateEmployee_thenFailCreateEmployee() throws Exception{
        Employee employeeWSO = new Employee();
        employeeWSO.setId("1");
        employeeWSO.setFirstName("Rajaram");
        employeeWSO.setLastName("Vishwa");
        //employeeWSO.setDateOfBirth( LocalDate.of(1990,12,10));
        //Should have used builder :(
        Address add = new Address();
        add.setAddressLine1("333");
        add.setCity("BLR");
        add.setCountry("IN");
        add.setState("KA");
        add.setZipCode(56010);
        employeeWSO.setAddress(add);
        given(service.create(Mockito.any())).willThrow(new DuplicateEmployeeException("Employee Details already exists:" ));
        String body = toJson(employeeWSO);
        mvc.perform(post("/v1/bfs/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest()).andExpect(header().doesNotExist(HttpHeaders.LOCATION)).andExpect(jsonPath("$.message", is("Employee Details already exists:")));

    }

    @Test
    public void whenPostInvalidAddress_thenFailCreateEmployee() throws Exception{
        Employee employeeWSO = new Employee();
        employeeWSO.setId("1");
        employeeWSO.setFirstName("Rajaram");
        employeeWSO.setLastName("Vishwa");
        String body = toJson(employeeWSO);
        mvc.perform(post("/v1/bfs/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest()).andExpect(header().doesNotExist(HttpHeaders.LOCATION)).andExpect(jsonPath("$.details", is("Address can not be null")));

    }

    @Test
    public void whenPostInvalidFirstName_thenFailCreateEmployee() throws Exception{
        Employee employeeWSO = new Employee();
        employeeWSO.setId("1");
        employeeWSO.setLastName("Vishwa");
        Address add = new Address();
        add.setAddressLine1("333");
        add.setCity("BLR");
        add.setCountry("IN");
        add.setState("KA");
        add.setZipCode(56010);
        employeeWSO.setAddress(add);
        String body = toJson(employeeWSO);
        mvc.perform(post("/v1/bfs/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest()).andExpect(header().doesNotExist(HttpHeaders.LOCATION)).andExpect(jsonPath("$.details", is("First name should have atleast 2 characters and can not exceed 255 characters")));

    }




}

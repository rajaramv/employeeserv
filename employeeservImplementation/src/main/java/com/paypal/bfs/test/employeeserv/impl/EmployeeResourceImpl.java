package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    private final EmployeeService employeeService;

    public EmployeeResourceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {

        //Find by ID
        Optional<Employee> employee = employeeService.byId(id);
        return  new ResponseEntity<>(employee.get(),HttpStatus.OK);

    }

    @Override
    public ResponseEntity createEmployee(@Valid  Employee employeeRequest) {
        //Save the entity using service
        Employee savedemployee =  employeeService.create(employeeRequest);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedemployee.getId()).toUri();
        return ResponseEntity.created(location).build();

    }



}

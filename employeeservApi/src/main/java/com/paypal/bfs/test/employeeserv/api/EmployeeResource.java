package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Interface for employee resource operations.
 */
public interface EmployeeResource {

    /**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    @RequestMapping("/v1/bfs/employees/{id}")
    ResponseEntity<Employee> employeeGetById(@PathVariable("id") String id);

    // ----------------------------------------------------------
    // TODO - add a new operation for creating employee resource.
    // ----------------------------------------------------------

    /**
     * Creates an Employee entity, throws validations error based on rules.
     * @param employee Employee object
     * @return Location when successfully created. If validation error happens, return an error response @{@link com.paypal.bfs.test.employeeserv.api.exception.handler.ExceptionResponse}
     */
    @PostMapping(value = "/v1/bfs/employees", consumes = APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity createEmployee(@Valid @RequestBody final Employee employee);

}

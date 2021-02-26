package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.api.exception.DuplicateEmployeeException;
import com.paypal.bfs.test.employeeserv.api.exception.EmployeeNotFoundExecption;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.dao.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {


    private EmployeeRepository employeeRepository;


    private EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    /**
     * Returns if the enployee details exist
     * @param id Identifier of the employee object
     * @return Employee details
     * @throws  @{@link EmployeeNotFoundExecption} if not found
     */
    public Optional<Employee> byId(String id){

        Optional<EmployeeEntity> entity = employeeRepository.findById(id);
        if(!entity.isPresent()){
            throw new EmployeeNotFoundExecption("Employee details not found for identifier: "+ id);
        }
        Employee result  = employeeMapper.fromEntity(entity.get());
        return Optional.ofNullable(result);
    }

    /**
     * Creates a new employee entity if the enployee details does not exist
     * @param employeeRequest  employee object
     * @return Employee details
     * @throws  @{@link DuplicateEmployeeException} if details are found
     */
    public Employee create(Employee employeeRequest){
        if(null != employeeRequest.getId() ){
            Optional<EmployeeEntity> employee = employeeRepository.findById(employeeRequest.getId());
            if(employee.isPresent()){
                throw new DuplicateEmployeeException("Employee Details already exists: " + employeeRequest.getId());
            }
        }
        EmployeeEntity employeeEntity = employeeRepository.save(employeeMapper.toEntity(employeeRequest));
        return employeeMapper.fromEntity(employeeEntity);
    }

}

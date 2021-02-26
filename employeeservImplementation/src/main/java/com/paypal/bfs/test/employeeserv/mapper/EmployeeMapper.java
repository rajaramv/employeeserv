package com.paypal.bfs.test.employeeserv.mapper;


import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.AddressEntity;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeEntity toEntity(Employee employee){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setDateOfBirth(employee.getDateOfBirth());
        AddressEntity address = new AddressEntity();
        address.setAddressLine1(employee.getAddress().getAddressLine1());
        address.setAddressLine2(employee.getAddress().getAddressLine2());
        address.setCity(employee.getAddress().getCity());
        address.setState(employee.getAddress().getState());
        address.setZipCode(employee.getAddress().getZipCode());
        address.setCountry(employee.getAddress().getCountry());
        employeeEntity.setAddress(address);
        return employeeEntity;
    }

    public Employee fromEntity(EmployeeEntity employeeEntity){
        Employee employee = new Employee();
        employee.setId(employeeEntity.getId());
        employee.setFirstName(employeeEntity.getFirstName());
        employee.setLastName(employeeEntity.getLastName());
        employee.setDateOfBirth(employeeEntity.getDateOfBirth());
        Address address = new Address();
        address.setAddressLine1(employeeEntity.getAddress().getAddressLine1());
        address.setAddressLine2(employeeEntity.getAddress().getAddressLine2());
        address.setCity(employeeEntity.getAddress().getCity());
        address.setState(employeeEntity.getAddress().getState());
        address.setZipCode(employeeEntity.getAddress().getZipCode());
        address.setCountry(employeeEntity.getAddress().getCountry());
        employee.setAddress(address);
        return employee;

    }

}

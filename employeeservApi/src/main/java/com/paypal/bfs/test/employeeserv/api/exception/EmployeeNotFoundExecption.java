package com.paypal.bfs.test.employeeserv.api.exception;

public class EmployeeNotFoundExecption extends RuntimeException
{
    public EmployeeNotFoundExecption(String message)
    {
        super(message);
    }
}
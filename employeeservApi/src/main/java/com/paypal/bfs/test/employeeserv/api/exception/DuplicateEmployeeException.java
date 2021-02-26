package com.paypal.bfs.test.employeeserv.api.exception;

public class DuplicateEmployeeException extends RuntimeException
{
    public DuplicateEmployeeException(String message)
    {
        super(message);
    }
}
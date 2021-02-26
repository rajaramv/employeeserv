package com.paypal.bfs.test.employeeserv.api.exception.handler;
import java.util.Date;

import com.paypal.bfs.test.employeeserv.api.exception.DuplicateEmployeeException;
import com.paypal.bfs.test.employeeserv.api.exception.EmployeeNotFoundExecption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    /**
     * Handles all the generic exceptions
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request)
    {
        logger.error(ex.getMessage());
        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(EmployeeNotFoundExecption.class)
    public final ResponseEntity<Object> handleUserNotFoundExceptions(EmployeeNotFoundExecption ex, WebRequest request)
    {
        logger.error(ex.getMessage());
        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DuplicateEmployeeException.class)
    public final ResponseEntity<Object> handleUserNotFoundExceptions(DuplicateEmployeeException ex, WebRequest request)
    {
        logger.error(ex.getMessage());
        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        logger.error(ex.getMessage());
        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), "Field Validation Failed", ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}

package com.ghtk.onlinebiddingproject.exceptions;

import com.ghtk.onlinebiddingproject.models.responses.CommonResponse;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(BadRequestException e) {
        e.printStackTrace();
        List<String> errors = new ArrayList<>();
        errors.add(e.getLocalizedMessage());
        CommonResponse errorResponse = new CommonResponse(false, "Bad request!", null, errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handlerNotFoundException(NotFoundException e) {
        e.printStackTrace();
        List<String> errors = new ArrayList<>();
        errors.add(e.getLocalizedMessage());
        CommonResponse errorResponse = new CommonResponse(false, "Not found!", null, errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<Object> handlerAccessDeniedException(AccessDeniedException e, WebRequest request) {
        e.printStackTrace();
        List<String> errors = new ArrayList<>();
        errors.add(e.getLocalizedMessage());
        CommonResponse errorResponse = new CommonResponse(false, "Access denied!", null, errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<Object> handlerBadCredentialsException(BadCredentialsException e, WebRequest request) {
        e.printStackTrace();
        List<String> errors = new ArrayList<>();
        errors.add(e.getLocalizedMessage());
        CommonResponse errorResponse = new CommonResponse(false, "Bad credentials! Tài khoản hoặc mật khẩu không đúng!", null, errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<Object> handlerSQLException(RuntimeException e, WebRequest request) {
        e.printStackTrace();
        List<String> errors = new ArrayList<>();
        errors.add(e.getLocalizedMessage());
        CommonResponse errorResponse = new CommonResponse(false, "Bad request, some properties can not be null!", null, errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        e.printStackTrace();
        List<String> errors = new ArrayList<>();
        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }
        CommonResponse errorResponse = new CommonResponse(false, "Data validation failed!", null, errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        e.printStackTrace();
        List<String> errors = new ArrayList<>();
        errors.add(e.getLocalizedMessage());
        CommonResponse errorResponse = new CommonResponse(false, "Bad request!", null, errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleTypeMismatch(TypeMismatchException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        e.printStackTrace();
        List<String> errors = new ArrayList<>();
        errors.add(e.getLocalizedMessage());
        CommonResponse errorResponse = new CommonResponse(false, "Bad request!", null, errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /*
     * Global Exception Handler
     * */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllExceptions(Exception e) {
        e.printStackTrace();
        List<String> errors = new ArrayList<>();
        errors.add(e.getLocalizedMessage());
        CommonResponse errorResponse = new CommonResponse(false, "Error occured! :(", null, errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

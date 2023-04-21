package com.pre015.server.advice;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

//    @ExceptionHandler
//    public ResponseEntity handleMethodArgumentNotValidException(
//            MethodArgumentNotValidException e) {
//        e.getBindingResult()
//    }
}

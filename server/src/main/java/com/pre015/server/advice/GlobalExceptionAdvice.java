package com.pre015.server.advice;


import com.pre015.server.exception.BusinessLogicException;
import com.pre015.server.exception.ExceptionCode;
import com.pre015.server.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;


@Slf4j
@RestControllerAdvice         // Controller클래스 에서 발생하는 RequestBody 유효성 검증에 대한 에러 처리
public class GlobalExceptionAdvice {

    // Method Argument Not Valid Error : Controller/Service @Valid @Validated 유효성 검증 실패 오류, 예외 처리
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException", e);
        final ErrorResponse errorResponse = ErrorResponse.of(e.getBindingResult());
        return errorResponse;
    }

    // ConstraintViolation Error : JPA @Valid @Validated 유효성 검증에 실패 오류, 예외 처리
    @ExceptionHandler @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        log.error("ConstraintViolationException", e);
        final ErrorResponse errorResponse = ErrorResponse.of(e.getConstraintViolations());
        return errorResponse;
    }

    // Bind Error : @Valid, @Validated 에서 binding error 발생 시
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        log.error("handleBindException", e);
        final ErrorResponse errorResponse = ErrorResponse.of(ExceptionCode.INVALID_INPUT_VALUE);
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ExceptionCode.INVALID_INPUT_VALUE.getStatus()));
    }

    // MethodArgumentTypeMismatch Error : Method 매개변수 타입 불일치 오류, 예외처리
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("handleMethodArgumentTypeMismatchException", e);
        final ErrorResponse errorResponse = ErrorResponse.of(ExceptionCode.INVALID_INPUT_VALUE);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // HttpRequestMethodNotSupported Error : 지원하지 않는 HTTP Request 오류, 예외처리
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        final ErrorResponse errorResponse = ErrorResponse.of(ExceptionCode.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    // AccessDenied Error : 인가 거부 오류, 예외 처리
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.error("handleAccessDeniedException", e);
        final ErrorResponse errorResponse = ErrorResponse.of(ExceptionCode.HANDLE_ACCESS_DENIED);
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ExceptionCode.HANDLE_ACCESS_DENIED.getStatus()));
    }

    // BusienessLogic Error : BusinessLogicException 에 등록 된 오류, 예외 처리
    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity handleBusinessLogicException(BusinessLogicException e) {
        System.out.println(e.getExceptionCode().getStatus());
        System.out.println(e.getMessage());

        log.error("handleBusinessLogicException", e);

        final ErrorResponse errorResponse = ErrorResponse.of(e.getExceptionCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatus()));
    }

    // Exception Error : 위 외의 모든 예외 처리
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handleEntityNotFoundException", e);
        final ErrorResponse errorResponse = ErrorResponse.of(ExceptionCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


package com.pre015.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/errors")
public class ExceptionCodeController {

    @GetMapping
    public ResponseEntity<ExceptionCodeView> getErrorCodes() {

        Map<String, String> errorCodes = Arrays.stream(ExceptionCode.values())
                .collect(Collectors.toMap(ExceptionCode::getCode, ExceptionCode::getDescription));

        return new ResponseEntity<>(new ExceptionCodeView(errorCodes), HttpStatus.OK);
    }

}
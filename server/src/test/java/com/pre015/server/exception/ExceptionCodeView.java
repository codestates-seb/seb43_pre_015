package com.pre015.server.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ExceptionCodeView {
    private Map<String, String> exceptionCodes;
}
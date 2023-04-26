package com.pre015.server.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    INVALID_INPUT_VALUE(400,  "INVALID INPUT VALUE"),
    INVALID_INPUT_ATK(400, "INVALID INPUT ACCESS_TOKEN"),

    HANDLE_ACCESS_DENIED(403,"HANDLE ACCESS DENIED"),

    MEMBER_NOT_FOUND(404, "MEMBER NOT FOUND"),
    MEMBER_EXISTS(409, "MEMBER EXISTS"),
    MEMBER_RESIGNED(422, "MEMBER RESIGNED"),

    QUESTION_NOT_FOUND(404, "QUESTION NOT FOUND"),
    QUESTION_ALREADY_SOLVED(404, "QUESTION ALREADY HAS AN ACCEPTED ANSWER"),
    ANSWER_NOT_FOUND(404, "ANSWER NOT FOUND"),
    COMMENT_NOT_FOUND(404, "COMMENT NOT FOUND"),

    /* 405 : */
    METHOD_NOT_ALLOWED(405, "METHOD NOT ALLOWED"),

    /* 500 : */
    INTERNAL_SERVER_ERROR(500,"INTERNAL SERVER ERROR");


    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int statusCode, String message){
        this.status = statusCode;
        this.message = message;
    }
}


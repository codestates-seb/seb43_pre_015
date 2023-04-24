package com.pre015.server.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {


    INVALID_INPUT_VALUE(400,  "INVALID INPUT VALUE"),
    INVALID_INPUT_ATK(400, "INVALID INPUT ACCESS_TOKEN"),

    HANDLE_ACCESS_DENIED(403,"HANDLE ACCESS DENIED"),

    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    MEMBER_RESIGNED(422, "Member resigned"),


    QUESTION_NOT_FOUND(404, "QUESTION NOT FOUND"),
    ANSWER_NOT_FOUND(404, "ANSWER NOT FOUND"),
    COMMENT_NOT_FOUND(404, "COMMENT NOT FOUND"),

    /* 405 : */
    METHOD_NOT_ALLOWED(405, "METHOD NOT ALLOWED"),


    /* 500 : */
    INTERNAL_SERVER_ERROR(500,"INTERNAL SERVER ERROR");



    @Getter
    private int status;
    private String message;

    ExceptionCode(int statusCode, String message){
        this.status = statusCode;
        this.message = message;
    }
}


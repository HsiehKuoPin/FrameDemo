package com.benjamin.http.exception;

public class HttpException extends RuntimeException {

    public int exCode;

    public String exMessage;

    public HttpException(int code, String message) {
        super(message);
        this.exCode = code;
        this.exMessage = message;
    }

    public HttpException(Throwable cause) {
        this(0, cause);
    }

    public HttpException(int code, Throwable cause) {
        super(cause);
        this.exCode = code;
        this.exMessage = cause == null ? "null" : cause.toString();
    }

    @Override
    public String getMessage() {
        return exMessage;
    }

    @Override
    public String toString() {
        return "HttpException{" +
                "exCode=" + exCode +
                ", exMessage='" + exMessage + '\'' +
                "} ";
    }
}

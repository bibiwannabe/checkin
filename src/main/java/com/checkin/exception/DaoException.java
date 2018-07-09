package com.checkin.exception;


public class DaoException extends RuntimeException {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private int code;
    private String message;


    public DaoException(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public DaoException(String message, Throwable cause, int code, String message1) {
        super(message, cause);
        this.code = code;
        this.message = message1;
    }
}

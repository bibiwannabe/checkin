package com.checkin.common;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private int code;

    private String message = "";

    private T data;

    public static class Builder<T> {
        private static final String SUCCESS_MESSAGE = "SUCCESS";
        private static final String FAIL_MESSAGE = "FAIL";

        private Result<T> result;

        public Builder(){
            result = new Result<T>();
        }

        public Builder(T data){
            result = new Result<T>();
            result.setData(data);
        }

        public Builder<T> setCode(int code){
            result.setCode(code);
            if (Code.erroMessage.containsKey(code)){
                result.setMessage(Code.erroMessage.get(code));
            }
            return this;
        }

        public Builder<T> setData(T data){
            result.setData(data);
            return this;
        }

        public Builder<T> setMessage(String message){
            result.setMessage(message);
            return this;
        }

        public Result<T> build(){
            return result;
        }

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}

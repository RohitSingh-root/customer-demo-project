package com.example.customer.exception;

public class InvalidDataException extends RuntimeException {

    private String msg;
    
    public InvalidDataException(String msg){
        this.msg= msg;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}

package com.nine96kibs.nine96kibsandroid;

public class CommonResult {

    private static final int SUCCESS = 200;

    private static final int FAILED = 500;

    private int code;

    private String message;

    private Object data;

    public CommonResult success(Object data){
        this.code = SUCCESS;
        this.message = "OK";
        this.data = data;
        return this;
    }

    public CommonResult failed(String message){
        this.code = FAILED;
        this.message = message;
        return this;
    }

    public CommonResult success(){
        this.code = SUCCESS;
        this.message = "OK";
        return this;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

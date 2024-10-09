package com.shool.demo.entities;

import org.springframework.stereotype.Component;

@Component
public class BadRequest {

    private String errorCode;
    private String errorMessage;
    private String field;

    public BadRequest(){

    }
    public void setErrorCode(String errorCode){
        this.errorCode =errorCode;
    }

    public String getErrorCode(){
        return errorCode;
    }

    public void setErrorMessage(String errorMessage){
        this.errorMessage =errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public void setField(String field){
        this.field =field;
    }

    public String getField(){
        return field;
    }
}

package com.agri.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by hyc on 2017/4/13.
 */
@XmlRootElement
public class State {
    private int status;
    private String errorMessage;
    public State(){}
    public State(int status,String errorMessage){
        this.status = status;
        this.errorMessage = errorMessage;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

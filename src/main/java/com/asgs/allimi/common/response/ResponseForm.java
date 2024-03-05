package com.asgs.allimi.common.response;

import lombok.Data;

@Data
public class ResponseForm<T> {

    private StatusResponse statusResponse = new StatusResponse(ResultCode.OK);
    private final T data;

    public ResponseForm(){
        this.data = null;
    }

    public ResponseForm(T data){ // success
        this.data = data;
    }

    public ResponseForm(T data, ResultCode resultCode){
        this.data = data;
        this.statusResponse = new StatusResponse(resultCode);
    }

    public ResponseForm(ResultCode resultCode){ // failure
        this();
        this.statusResponse = new StatusResponse(resultCode);
    }

    public ResponseForm(String resultCode, String resultMessage){
        this();
        this.statusResponse = new StatusResponse(resultCode, resultMessage);
    }
}

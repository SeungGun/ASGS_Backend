package com.asgs.allimi.common.exception;

import com.asgs.allimi.common.response.ResultCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomClientException extends RuntimeException{

    private final HttpStatus httpStatus;
    private final ResultCode resultCode;
    private final String resultMessage;

    public CustomClientException(HttpStatus httpStatus, ResultCode resultCode){
        this(httpStatus, resultCode, resultCode.getMessage());
    }

    public CustomClientException(HttpStatus httpStatus, ResultCode resultCode, String resultMessage){
        super(resultMessage);
        this.httpStatus = httpStatus;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }
}

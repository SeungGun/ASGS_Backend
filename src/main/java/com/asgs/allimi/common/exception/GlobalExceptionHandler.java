package com.asgs.allimi.common.exception;

import com.asgs.allimi.common.response.ResponseForm;
import com.asgs.allimi.common.response.ResultCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResponseForm<Void>> handleInternalErrorException(Exception e, HttpServletRequest request){
        log.error("[서버 에러] at {}", request.getRequestURI(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseForm<>(ResultCode.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(CustomClientException.class)
    protected ResponseEntity<ResponseForm<Void>> handleCustomClientException(CustomClientException e, HttpServletRequest request){
        log.warn("[클라이언트 에러] at {}", request.getRequestURI(), e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(new ResponseForm<>(e.getResultCode()));
    }
}

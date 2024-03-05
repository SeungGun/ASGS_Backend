package com.asgs.allimi.common.response;

import lombok.Getter;

@Getter
public enum ResultCode {

    // 정상 처리
    OK("GS0000", "요청 정상"),
    CREATED("GS0001", "데이터 정상 생성 완료"),

    // 서버 에러
    INTERNAL_SERVER_ERROR("GS1000", "서버 내부 에러 발생"),
    ;
    private final String code;
    private final String message;

    ResultCode(String code, String message){
        this.code = code;
        this.message = message;
    }
}

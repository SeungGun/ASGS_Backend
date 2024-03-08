package com.asgs.allimi.common.response;

import lombok.Getter;

@Getter
public enum ResultCode {

    // 정상 처리
    OK("GS0000", "요청 정상"),
    CREATED("GS0001", "데이터 정상 생성 완료"),

    // 서버 에러
    INTERNAL_SERVER_ERROR("GS1000", "서버 내부 에러 발생"),
    INVALID_INPUT("GS1001", "요청 입력 값이 유효하지 않습니다."),

    // 메뉴 클라이언트 에러
    INVALID_INPUT_STOCK_QUANTITY("GS2000", "유효한 재고 수량이 아닙니다."),
    INVALID_INPUT_MENU_PRICE("GS2001", "유효하지 않은 상품 가격입니다."),
    INVALID_INPUT_DISCOUNT("GS2002", "유효하지 않은 상품 할인률입니다."),
    NOT_EXIT_MENU("GS2003", "존재하지 읺는 메뉴입니다.");
    private final String code;
    private final String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

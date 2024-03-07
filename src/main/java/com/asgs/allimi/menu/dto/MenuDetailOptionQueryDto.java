package com.asgs.allimi.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MenuDetailOptionQueryDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DetailOptions{
        private Long detailOptionId;
        private String choice;
        private int price;
    }
}

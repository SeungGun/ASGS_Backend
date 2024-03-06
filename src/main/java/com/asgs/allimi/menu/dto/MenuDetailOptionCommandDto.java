package com.asgs.allimi.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MenuDetailOptionCommandDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Create {
        private String choice;
        private int price;
    }
}

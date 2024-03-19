package com.asgs.allimi.menu.dto;

import com.asgs.allimi.menu.domain.MenuDetailOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MenuDetailOptionRequest {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Create {
        private String choice;
        private int price;

        public MenuDetailOption toEntity() {
            return MenuDetailOption.builder()
                    .choice(choice)
                    .price(price)
                    .build();
        }
    }
}

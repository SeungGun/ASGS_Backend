package com.asgs.allimi.menu.dto;

import com.asgs.allimi.menu.domain.MenuDetailOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MenuDetailOptionResponse {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailOptions {
        private Long detailOptionId;
        private String choice;
        private int price;

        @Builder
        public DetailOptions(MenuDetailOption menuDetailOption) {
            this.detailOptionId = menuDetailOption.getId();
            this.choice = menuDetailOption.getChoice();
            this.price = menuDetailOption.getPrice();
        }
    }
}

package com.asgs.allimi.menu.dto;

import com.asgs.allimi.menu.domain.Menu;
import com.asgs.allimi.menu.domain.MenuCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class MenuDetailResponse {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Detail {
        private Long menuId;
        private String name;
        private String description;
        private MenuCategory category;
        private int stockQuantity;
        private int originalPrice;
        private int currentPrice;
        private int discount;
        private boolean isAbleBook;
        private List<String> images;
        private List<MenuOptionResponse.Detail> options;

        @Builder
        public Detail(Menu menu) {
            this.menuId = menu.getId();
            this.name = menu.getName();
            this.description = menu.getDescription();
            this.category = menu.getCategory();
            this.originalPrice = menu.getPrice();
            this.stockQuantity = menu.getStockQuantity();
            this.discount = menu.getDiscount();
            this.isAbleBook = menu.isAbleBook();
        }
    }


}

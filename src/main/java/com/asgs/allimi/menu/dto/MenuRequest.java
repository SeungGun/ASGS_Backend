package com.asgs.allimi.menu.dto;

import com.asgs.allimi.menu.domain.Menu;
import com.asgs.allimi.menu.domain.MenuCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class MenuRequest {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Create {
        @NotBlank
        private String name;
        private String description;
        @NotNull
        private MenuCategory menuCategory;
        private int price;
        private int stockQuantity;
        private int discount;
        @JsonProperty("isAbleBook")
        private boolean isAbleBook;
        private boolean onSale;
        private List<MenuOptionRequest.Create> options;

        public Menu toEntity() {
            return Menu.builder()
                    .name(this.getName())
                    .description(this.getDescription())
                    .price(this.getPrice())
                    .stockQuantity(this.getStockQuantity())
                    .discount(this.getDiscount())
                    .category(this.getMenuCategory())
                    .isAbleBook(this.isAbleBook())
                    .build();
        }
    }

}

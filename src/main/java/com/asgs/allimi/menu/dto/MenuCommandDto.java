package com.asgs.allimi.menu.dto;

import com.asgs.allimi.menu.domain.MenuCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class MenuCommandDto {

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
        private List<MenuOptionCommandDto.Create> options;
    }

}

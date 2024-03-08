package com.asgs.allimi.menu.dto;

import com.asgs.allimi.menu.domain.MenuCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class MenuQueryDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Detail{
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
        private List<MenuOptionQueryDto.Detail> options;
    }




}

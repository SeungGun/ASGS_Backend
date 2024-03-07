package com.asgs.allimi.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class MenuOptionQueryDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Detail {
        private Long optionId;
        private String title;
        private List<MenuDetailOptionQueryDto.DetailOptions> detailOptions;
    }
}

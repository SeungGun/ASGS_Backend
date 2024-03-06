package com.asgs.allimi.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class MenuOptionCommandDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Create {
        private String title;
        private List<MenuDetailOptionCommandDto.Create> detailOptions;
    }
}

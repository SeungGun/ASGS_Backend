package com.asgs.allimi.menu.dto;

import com.asgs.allimi.menu.domain.MenuOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class MenuOptionRequest {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Create {
        private String title;
        private List<MenuDetailOptionRequest.Create> detailOptions;

        public MenuOption toEntity() {
            return MenuOption.builder()
                    .title(this.title)
                    .menuDetailOptions(this.detailOptions.stream()
                            .map(MenuDetailOptionRequest.Create::toEntity)
                            .toList())
                    .build();
        }
    }
}

package com.asgs.allimi.menu.dto;

import com.asgs.allimi.menu.domain.MenuOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class MenuOptionResponse {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Detail {
        private Long optionId;
        private String title;
        private List<MenuDetailOptionResponse.DetailOptions> detailOptions;

        @Builder
        public Detail(MenuOption menuOption) {
            this.optionId = menuOption.getId();
            this.title = menuOption.getTitle();
        }
    }
}

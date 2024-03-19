package com.asgs.allimi.menu.service;

import com.asgs.allimi.menu.domain.MenuDetailOption;
import com.asgs.allimi.menu.domain.MenuOption;
import com.asgs.allimi.menu.dto.MenuDetailOptionResponse;
import com.asgs.allimi.menu.dto.MenuOptionRequest;
import com.asgs.allimi.menu.dto.MenuOptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuOptionQueryService {

    public List<MenuOption> convertMenuOption(List<MenuOptionRequest.Create> options) {
        return options.stream()
                .map(MenuOptionRequest.Create::toEntity)
                .toList();
    }

    public List<MenuOptionResponse.Detail> getOptions(List<MenuOption> menuOptions) {
        return menuOptions.stream()
                .map(this::getOption)
                .toList();
    }

    public MenuOptionResponse.Detail getOption(MenuOption menuOption) {
        MenuOptionResponse.Detail detail = new MenuOptionResponse.Detail(menuOption);
        detail.setDetailOptions(getDetailOptions(menuOption.getMenuDetailOptions()));
        return detail;
    }

    public List<MenuDetailOptionResponse.DetailOptions> getDetailOptions(List<MenuDetailOption> menuDetailOptions) {
        return menuDetailOptions.stream()
                .map(MenuDetailOptionResponse.DetailOptions::new)
                .toList();
    }
}

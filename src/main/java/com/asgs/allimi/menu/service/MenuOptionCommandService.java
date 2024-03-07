package com.asgs.allimi.menu.service;

import com.asgs.allimi.menu.domain.MenuDetailOption;
import com.asgs.allimi.menu.domain.MenuOption;
import com.asgs.allimi.menu.dto.MenuOptionCommandDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuOptionCommandService {

    public List<MenuOption> convertMenuOption(List<MenuOptionCommandDto.Create> options){
        return options.stream()
                .map(option -> {
                    List<MenuDetailOption> detailOptions = option.getDetailOptions().stream()
                            .map(detail -> MenuDetailOption.builder()
                                    .price(detail.getPrice())
                                    .choice(detail.getChoice())
                                    .build())
                            .toList();
                    return MenuOption.builder()
                            .title(option.getTitle())
                            .menuDetailOptions(detailOptions)
                            .build();
                }).toList();
    }
}

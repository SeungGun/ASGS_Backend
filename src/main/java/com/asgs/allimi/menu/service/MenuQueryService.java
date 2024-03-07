package com.asgs.allimi.menu.service;

import com.asgs.allimi.common.exception.CustomClientException;
import com.asgs.allimi.menu.domain.Menu;
import com.asgs.allimi.menu.domain.MenuImage;
import com.asgs.allimi.menu.dto.MenuDetailOptionQueryDto;
import com.asgs.allimi.menu.dto.MenuOptionQueryDto;
import com.asgs.allimi.menu.dto.MenuQueryDto;
import com.asgs.allimi.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.asgs.allimi.common.response.ResultCode.*;

@Service
@RequiredArgsConstructor
public class MenuQueryService {
    private final MenuRepository menuRepository;

    public MenuQueryDto.Detail getDetailMenu(Long menuId) {
        Menu menu = getMenuByIdElseThrow(menuId);

        MenuQueryDto.Detail response = MenuQueryDto.Detail.builder()
                .menuId(menu.getId())
                .name(menu.getName())
                .description(menu.getDescription())
                .category(menu.getCategory())
                .originalPrice(menu.getPrice())
                .stockQuantity(menu.getStockQuantity())
                .discount(menu.getDiscount())
                .isAbleBook(menu.isAbleBook())
                .build();

        response.setCurrentPrice(calculateDiscount(menu.getPrice(), menu.getDiscount()));

        if (menu.getMenuOptions() != null) {
            response.setOptions(
                    menu.getMenuOptions().stream().map(option ->
                            MenuOptionQueryDto.Detail.builder()
                                    .optionId(option.getId())
                                    .title(option.getTitle())
                                    .detailOptions(
                                            option.getMenuDetailOptions()
                                                    .stream().map(detail ->
                                                            MenuDetailOptionQueryDto.DetailOptions.builder()
                                                                    .detailOptionId(detail.getId())
                                                                    .price(detail.getPrice())
                                                                    .choice(detail.getChoice())
                                                                    .build()
                                                    ).toList()
                                    ).build()
                    ).toList()
            );
        }

        response.setImages(
                menu.getMenuImages().stream().map(
                        (MenuImage::getPath)
                ).toList()
        );

        return response;
    }

    private Menu getMenuByIdElseThrow(Long menuId) {
        return menuRepository.findById(menuId)
                .orElseThrow(() -> new CustomClientException(HttpStatus.NOT_FOUND, NOT_EXIT_MENU));
    }

    private int calculateDiscount(int price, int discount) {
        return (int) Math.floor((price * (100 - discount)) / 100.0); // 내림
    }
}

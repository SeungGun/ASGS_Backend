package com.asgs.allimi.menu.service;

import com.asgs.allimi.common.exception.CustomClientException;
import com.asgs.allimi.discount.RateDiscountService;
import com.asgs.allimi.menu.domain.Menu;
import com.asgs.allimi.menu.dto.MenuDetailResponse;
import com.asgs.allimi.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.asgs.allimi.common.response.ResultCode.*;

@Service
@RequiredArgsConstructor
public class MenuQueryService {
    private final MenuRepository menuRepository;
    private final MenuOptionQueryService menuOptionCommandService;
    private final MenuImageService menuImageService;
    private final RateDiscountService discountService;

    public Menu getMenuByIdElseThrow(Long menuId) {
        return menuRepository.findById(menuId)
                .orElseThrow(() -> new CustomClientException(HttpStatus.NOT_FOUND, NOT_EXIT_MENU));
    }

    public MenuDetailResponse.Detail getDetailMenu(Long menuId) {
        Menu menu = getMenuByIdElseThrow(menuId);
        MenuDetailResponse.Detail detailMenuDto = new MenuDetailResponse.Detail(menu);

        detailMenuDto.setCurrentPrice(discountService.calculateDiscountedPrice(
                menu.getPrice(), menu.getDiscount()));

        if (menu.getMenuOptions() != null) {
            detailMenuDto.setOptions(menuOptionCommandService.getOptions(menu.getMenuOptions()));
        }

        detailMenuDto.setImages(menuImageService.getImagePaths(menu.getMenuImages()));
        return detailMenuDto;
    }
}

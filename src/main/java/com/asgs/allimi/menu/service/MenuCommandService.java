package com.asgs.allimi.menu.service;

import com.asgs.allimi.common.exception.CustomClientException;
import com.asgs.allimi.discount.RateDiscountService;
import com.asgs.allimi.menu.domain.Menu;
import com.asgs.allimi.menu.dto.MenuRequest;
import com.asgs.allimi.menu.repository.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.asgs.allimi.common.response.ResultCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuCommandService {
    private final MenuRepository menuRepository;
    private final MenuOptionQueryService menuOptionCommandService;
    private final RateDiscountService discountService;

    public Long createMenu(MenuRequest.Create create) {
        validateInput(create);

        Menu menu = create.toEntity();
        // TODO: 이미지 처리
        if (create.getOptions() != null && !create.getOptions().isEmpty()) {
            menu.updateMenuOptions(menuOptionCommandService.convertMenuOption(create.getOptions()));
        }
        return menuRepository.save(menu).getId();
    }

    private void validateInput(MenuRequest.Create create) {
        if (create.getMenuCategory() == null) {
            throw new CustomClientException(HttpStatus.BAD_REQUEST, INVALID_INPUT);
        }

        if (isInvalidAmount(create.getStockQuantity())) {
            throw new CustomClientException(HttpStatus.BAD_REQUEST, INVALID_INPUT_STOCK_QUANTITY);
        }

        if (isInvalidAmount(create.getPrice())) {
            throw new CustomClientException(HttpStatus.BAD_REQUEST, INVALID_INPUT_MENU_PRICE);
        }

        if (!discountService.isRateInRange(create.getDiscount())) {
            throw new CustomClientException(HttpStatus.BAD_REQUEST, INVALID_INPUT_DISCOUNT);
        }
    }

    private boolean isInvalidAmount(int amount) {
        return amount < 0;
    }
}

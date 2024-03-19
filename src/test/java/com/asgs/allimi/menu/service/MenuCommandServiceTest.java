package com.asgs.allimi.menu.service;

import com.asgs.allimi.common.exception.CustomClientException;
import com.asgs.allimi.common.response.ResultCode;
import com.asgs.allimi.menu.domain.Menu;
import com.asgs.allimi.menu.domain.MenuCategory;
import com.asgs.allimi.menu.dto.MenuRequest;
import com.asgs.allimi.menu.dto.MenuDetailOptionRequest;
import com.asgs.allimi.menu.dto.MenuOptionRequest;
import com.asgs.allimi.menu.repository.MenuRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MenuCommandServiceTest {
    @Autowired
    private MenuCommandService menuCommandService;
    @Autowired
    private MenuRepository menuRepository;

    @Test
    @DisplayName("옵션 필드를 제외한 메뉴 데이터 저장에 성공한다.")
    void createMenu() {
        // given
        MenuRequest.Create create = MenuRequest.Create.builder()
                .name("상품1")
                .description("설명1")
                .price(1000)
                .stockQuantity(15)
                .menuCategory(MenuCategory.BREAD)
                .options(List.of(
                        MenuOptionRequest.Create.builder()
                                .title("옵션1")
                                .detailOptions(
                                        List.of(
                                                MenuDetailOptionRequest.Create.builder()
                                                        .choice("선택지1")
                                                        .price(500)
                                                        .build())
                                ).build()))
                .build();

        // when
        Long menuId = menuCommandService.createMenu(create);
        Menu menu = menuRepository.findById(menuId).get();

        // then
        assertEquals("상품1", menu.getName());
        assertEquals("설명1", menu.getDescription());
        assertEquals(MenuCategory.BREAD, menu.getCategory());
        assertEquals(1000, menu.getPrice());
        assertEquals("옵션1", menu.getMenuOptions().get(0).getTitle());
        assertEquals(500, menu.getMenuOptions().get(0).getMenuDetailOptions().get(0).getPrice());

        // default value
        assertEquals(0, menu.getDiscount());
        assertEquals(0, menu.getSoldCount());
        assertTrue(menu.isOnSale());
        assertFalse(menu.isAbleBook());
    }

    @Test
    @DisplayName("메뉴 가격이 음수가 되면 예외가 발생한다.")
    void createMenuWithInvalidPrice() {
        // given
        MenuRequest.Create create = MenuRequest.Create.builder()
                .name("상품1")
                .description("설명1")
                .price(-1000)
                .stockQuantity(15)
                .menuCategory(MenuCategory.DRINK)
                .build();

        // when, then
        assertThatThrownBy(() -> menuCommandService.createMenu(create))
                .isInstanceOf(CustomClientException.class)
                .hasMessage(ResultCode.INVALID_INPUT_MENU_PRICE.getMessage());
    }

    @Test
    @DisplayName("메뉴 재고가 음수가 되면 예외가 발생한다.")
    void createMenuWithInvalidStockQuantity() {
        // given
        MenuRequest.Create create = MenuRequest.Create.builder()
                .name("상품1")
                .description("설명1")
                .price(1000)
                .stockQuantity(-99)
                .menuCategory(MenuCategory.DRINK)
                .build();

        // when, then
        assertThatThrownBy(() -> menuCommandService.createMenu(create))
                .isInstanceOf(CustomClientException.class)
                .hasMessage(ResultCode.INVALID_INPUT_STOCK_QUANTITY.getMessage());
    }

    @Test
    @DisplayName("메뉴 할인률이 유효 범위 내에 없다면 예외가 발생한다.")
    void createMenuWithInvalidDiscount() {
        // given
        MenuRequest.Create create = MenuRequest.Create.builder()
                .name("상품1")
                .description("설명1")
                .price(3000)
                .stockQuantity(50)
                .discount(110)
                .menuCategory(MenuCategory.DRINK)
                .build();

        // when, then
        assertThatThrownBy(() -> menuCommandService.createMenu(create))
                .isInstanceOf(CustomClientException.class)
                .hasMessage(ResultCode.INVALID_INPUT_DISCOUNT.getMessage());
    }
}
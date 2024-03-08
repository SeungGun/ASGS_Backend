package com.asgs.allimi.menu.service;

import com.asgs.allimi.common.exception.CustomClientException;
import com.asgs.allimi.common.response.ResultCode;
import com.asgs.allimi.menu.domain.Menu;
import com.asgs.allimi.menu.domain.MenuCategory;
import com.asgs.allimi.menu.dto.*;
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
class MenuQueryServiceTest {
    @Autowired
    private MenuQueryService menuQueryService;
    @Autowired
    private MenuCommandService menuCommandService;
    @Autowired
    private MenuRepository menuRepository;

    @Test
    @DisplayName("옵션 없이 저장한 상세 메뉴 조회에 성공한다.")
    void getDetailMenuWithoutOption() {
        // given
        Menu menu = Menu.builder()
                .name("상품1")
                .description("설명1")
                .category(MenuCategory.DRINK)
                .stockQuantity(20)
                .price(2000).build();
        Menu saved = menuRepository.save(menu);

        // when
        MenuQueryDto.Detail detailMenu = menuQueryService.getDetailMenu(saved.getId());

        // then
        assertNull(detailMenu.getOptions());
        assertThat(detailMenu.getImages()).isEmpty();
        assertEquals("상품1", detailMenu.getName());
        assertEquals(MenuCategory.DRINK, detailMenu.getCategory());
        assertThat(detailMenu.getCurrentPrice()).isEqualTo(detailMenu.getOriginalPrice());
    }

    @Test
    @DisplayName("옵션과 저장한 상세 메뉴 조회에 성공한다.")
    void getDetailMenuWithOption() {
        // given
        MenuCommandDto.Create create = MenuCommandDto.Create.builder()
                .name("아메리카노")
                .description("부드러운 아메리카노")
                .price(1000)
                .stockQuantity(15)
                .menuCategory(MenuCategory.MADE_BEVERAGE)
                .options(List.of(
                        MenuOptionCommandDto.Create.builder()
                                .title("수령방식")
                                .detailOptions(
                                        List.of(
                                                MenuDetailOptionCommandDto.Create.builder()
                                                        .choice("일회용컵")
                                                        .price(300)
                                                        .build(),
                                                MenuDetailOptionCommandDto.Create.builder()
                                                        .choice("텀블러")
                                                        .price(-500)
                                                        .build())
                                ).build()))
                .build();
        Long menuId = menuCommandService.createMenu(create);

        // when
        MenuQueryDto.Detail detailMenu = menuQueryService.getDetailMenu(menuId);

        // then
        assertThat(detailMenu.getName())
                .isEqualTo("아메리카노");
        assertThat(detailMenu.getOptions().get(0).getTitle())
                .isEqualTo("수령방식");
        assertThat(detailMenu.getOptions().get(0).getDetailOptions())
                .hasSize(2);
        assertThat(detailMenu.getOptions().get(0).getDetailOptions().stream().map(MenuDetailOptionQueryDto.DetailOptions::getChoice))
                .contains("일회용컵", "텀블러");
    }

    @Test
    @DisplayName("할인이 적용된 메뉴의 현재 가격을 조회한다.")
    void getDetailMenuWithDiscount() {
        // given
        Menu menu = Menu.builder()
                .name("상품1")
                .description("설명1")
                .category(MenuCategory.DRINK)
                .stockQuantity(20)
                .discount(20) // 20%
                .price(2000)
                .build();
        Menu menu2 = Menu.builder()
                .name("상품1")
                .description("설명1")
                .category(MenuCategory.DRINK)
                .stockQuantity(20)
                .discount(24) // 24%
                .price(3600)
                .build();
        Menu menu3 = Menu.builder()
                .name("상품1")
                .description("설명1")
                .category(MenuCategory.DRINK)
                .stockQuantity(20)
                .discount(17) // 27%
                .price(3350)
                .build();

        Menu saved = menuRepository.save(menu);
        Menu saved2 = menuRepository.save(menu2);
        Menu saved3 = menuRepository.save(menu3);

        // when
        MenuQueryDto.Detail detailMenu = menuQueryService.getDetailMenu(saved.getId());
        MenuQueryDto.Detail detailMenu2 = menuQueryService.getDetailMenu(saved2.getId());
        MenuQueryDto.Detail detailMenu3 = menuQueryService.getDetailMenu(saved3.getId());

        // then
        assertThat(detailMenu.getCurrentPrice()).isEqualTo(1600);
        assertThat(detailMenu2.getCurrentPrice()).isEqualTo(2736);
        assertThat(detailMenu3.getCurrentPrice()).isEqualTo(2780);
    }

    @Test
    @DisplayName("유효하지 않은 메뉴 ID로 상세 메뉴 조회 시 예외가 발생한다.")
    void getDetailMenuWithInvalidMenuId() {
        // given
        Long menuId = -1L;

        // when, then
        assertThatThrownBy(() -> menuQueryService.getDetailMenu(menuId))
                .isInstanceOf(CustomClientException.class)
                .hasMessage(ResultCode.NOT_EXIT_MENU.getMessage());
    }

}
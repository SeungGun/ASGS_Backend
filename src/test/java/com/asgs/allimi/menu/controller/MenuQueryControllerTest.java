package com.asgs.allimi.menu.controller;

import com.asgs.allimi.menu.domain.Menu;
import com.asgs.allimi.menu.domain.MenuCategory;
import com.asgs.allimi.menu.repository.MenuRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class MenuQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MenuRepository menuRepository;
    private static final String BASE_URI = "/api/v1/menu";

    @Test
    void getDetailMenu() throws Exception {
        Menu menu = Menu.builder()
                .name("테스트상품")
                .description("설명입니다.")
                .category(MenuCategory.DRINK)
                .stockQuantity(20)
                .price(2000).build();
        Menu saved = menuRepository.save(menu);

        this.mockMvc.perform(get(BASE_URI + "/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("테스트상품"));
    }
}
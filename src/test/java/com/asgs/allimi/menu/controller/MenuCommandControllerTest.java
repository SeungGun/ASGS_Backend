package com.asgs.allimi.menu.controller;

import com.asgs.allimi.common.response.ResultCode;
import com.asgs.allimi.menu.domain.MenuCategory;
import com.asgs.allimi.menu.dto.MenuCommandDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class MenuCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private static final String BASE_URI = "/api/v1/menu";

    @Test
    @DisplayName("정상 데이터에 대한 메뉴 생성에 성공한다.")
    void createMenu() throws Exception{
        MenuCommandDto.Create create = MenuCommandDto.Create.builder()
                .name("상품1")
                .description("설명1")
                .price(1000)
                .stockQuantity(15)
                .menuCategory(MenuCategory.FOOD)
                .build();

        this.mockMvc.perform(post(BASE_URI)
                .content(convertDtoToJson(create))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    @Test
    @DisplayName("메뉴 생성 입력으로 카테고리 값이 null이면 예외를 발생한다.")
    void createMenuWithInvalidCategory() throws Exception {
        MenuCommandDto.Create create = MenuCommandDto.Create.builder()
                .name("상품1")
                .description("설명1")
                .price(1000)
                .stockQuantity(15)
                .menuCategory(null)
                .build();

        this.mockMvc.perform(post(BASE_URI)
                        .content(convertDtoToJson(create))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusResponse.resultCode")
                        .value(ResultCode.INVALID_INPUT.getCode()));
    }

    private String convertDtoToJson(Object obj) throws JsonProcessingException {
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();

        return objectWriter.writeValueAsString(obj);
    }

}
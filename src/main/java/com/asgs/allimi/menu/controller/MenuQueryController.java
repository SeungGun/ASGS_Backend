package com.asgs.allimi.menu.controller;

import com.asgs.allimi.common.response.ResponseForm;
import com.asgs.allimi.menu.dto.MenuDetailResponse;
import com.asgs.allimi.menu.service.MenuQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu")
public class MenuQueryController {

    private final MenuQueryService menuQueryService;

    @GetMapping("/{menuId}")
    public ResponseForm<MenuDetailResponse.Detail> getDetailMenu(@PathVariable("menuId") Long menuId) {
        return new ResponseForm<>(menuQueryService.getDetailMenu(menuId));
    }
}

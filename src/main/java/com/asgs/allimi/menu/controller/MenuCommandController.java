package com.asgs.allimi.menu.controller;

import com.asgs.allimi.common.response.ResponseForm;
import com.asgs.allimi.menu.dto.MenuCommandDto;
import com.asgs.allimi.menu.service.MenuCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu")
// TODO: 어드민 권한 추가
public class MenuCommandController {

    private final MenuCommandService menuCommandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseForm<Map<String, Long>> createMenu(@RequestBody @Valid MenuCommandDto.Create create) {
        Map<String, Long> response = new HashMap<>();
        response.put("menuId", menuCommandService.createMenu(create));
        return new ResponseForm<>(response);
    }
}

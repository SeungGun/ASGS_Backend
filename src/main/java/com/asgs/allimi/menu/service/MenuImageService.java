package com.asgs.allimi.menu.service;

import com.asgs.allimi.menu.domain.MenuImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuImageService {

    public List<String> getImagePaths(List<MenuImage> menuImages) {
        return menuImages.stream()
                .map(MenuImage::getPath)
                .toList();
    }
}

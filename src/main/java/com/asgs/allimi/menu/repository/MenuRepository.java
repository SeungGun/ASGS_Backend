package com.asgs.allimi.menu.repository;

import com.asgs.allimi.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}

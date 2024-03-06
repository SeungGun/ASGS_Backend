package com.asgs.allimi.menu.domain;

import com.asgs.allimi.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_option_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(nullable = false, length = 20)
    private String title;

    @OneToMany(mappedBy = "menuOption", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuDetailOption> menuDetailOptions = new ArrayList<>();
}

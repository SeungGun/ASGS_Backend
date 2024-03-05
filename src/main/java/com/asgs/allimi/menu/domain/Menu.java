package com.asgs.allimi.menu.domain;

import com.asgs.allimi.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MenuCategory category;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockQuantity;

    private int discount;

    @ColumnDefault("0")
    private int soldCount;

    @ColumnDefault("true")
    @Column(nullable = false)
    private boolean onSale;

    @ColumnDefault("false")
    @Column(nullable = false)
    private boolean isAbleBook;

    @Builder
    public Menu(String name, String description, MenuCategory category, int price, int stockQuantity){
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}

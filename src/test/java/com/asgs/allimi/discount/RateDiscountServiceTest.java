package com.asgs.allimi.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountServiceTest {
    private final DiscountService discountService = new RateDiscountService();

    @Test
    @DisplayName("할인율 서비스 로직 테스트")
    void rateDiscountTest(){
        int price = 3350;
        int rate = 17;
        int discountedPrice = discountService.calculateDiscountedPrice(price, rate);

        Assertions.assertThat(discountedPrice).isEqualTo(2780);
    }

}
package com.asgs.allimi.discount;

import org.springframework.stereotype.Service;

@Service
public class RateDiscountService implements DiscountService {
    private static final double PERCENTAGE = 100.0;
    private static final int MIN_PERCENTAGE = 1;
    private static final int MAX_PERCENTAGE = 100;

    @Override
    public int calculateDiscountedPrice(int price, int discountFactor) {
        if (isRateInRange(discountFactor)) {
            return (int) (price * (1 - discountFactor / PERCENTAGE));
        }
        return 0;
    }

    public boolean isRateInRange(int rate) {
        return rate >= MIN_PERCENTAGE && rate <= MAX_PERCENTAGE;
    }
}

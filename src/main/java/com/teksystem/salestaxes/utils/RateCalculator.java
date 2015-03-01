package com.teksystem.salestaxes.utils;

import java.math.BigDecimal;

public class RateCalculator {
    public static BigDecimal calculateRate(final Double price, final Double rateInPercentage) {
        final BigDecimal multiplied = new BigDecimal(rateInPercentage).divide(new BigDecimal(100.0), CustomFormatter.MATH_CONTEXT);

        return multiplied.multiply(BigDecimal.valueOf(price), CustomFormatter.MATH_CONTEXT);
    }
}
package com.teksystem.salestaxes.units.utils;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.units.utils.CustomFormatter.MATH_CONTEXT;

public class RateCalculator {
    public static BigDecimal calculateRate(final Double price, final Double rateInPercentage) {
        final BigDecimal multiplied = new BigDecimal(rateInPercentage).divide(new BigDecimal(100.0), MATH_CONTEXT);

        return multiplied.multiply(BigDecimal.valueOf(price), MATH_CONTEXT);
    }
}
package com.teksystem.salestaxes.utils;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.utils.CustomFormatter.MATH_CONTEXT;
import static java.math.BigDecimal.valueOf;

public class RateCalculator {
    //TODO: Not very elegant, should be refactored
    public static BigDecimal calculateRate(final Double price, final Double rateInPercentage) {

        final BigDecimal multiplied = new BigDecimal(rateInPercentage).multiply(BigDecimal.valueOf(price), MATH_CONTEXT);

        return multiplied.divide(valueOf(100), MATH_CONTEXT);
    }
}
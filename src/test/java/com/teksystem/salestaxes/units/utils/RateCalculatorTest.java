package com.teksystem.salestaxes.units.utils;

import org.junit.Test;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.utils.RateCalculator.calculateRate;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RateCalculatorTest {

    @Test
    public void ItShouldCalculateRateAndRoundUpNearestFive() {
        assertThat(calculateRate(new BigDecimal(18.8), 10.0).doubleValue(), is((1.88)));
    }

    @Test
    public void ItShouldCalculateRateForZero() {
        assertThat(calculateRate(new BigDecimal(18.8), 0.0).doubleValue(), is(0.0));
    }
}
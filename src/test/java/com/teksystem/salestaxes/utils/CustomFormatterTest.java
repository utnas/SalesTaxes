package com.teksystem.salestaxes.utils;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CustomFormatterTest {

    @Test
    public void testGetCustomDecimalFormat() throws Exception {

    }

    @Test
    public void itShouldFormatABigDecimal() {
        assertThat(CustomFormatter.format(new BigDecimal(23.345)), is(23.34));
    }
}
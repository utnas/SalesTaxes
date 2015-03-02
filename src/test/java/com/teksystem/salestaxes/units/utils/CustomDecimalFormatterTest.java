package com.teksystem.salestaxes.units.utils;

import org.junit.Test;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.utils.CustomDecimalFormatter.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CustomDecimalFormatterTest {

    @Test
    public void itShouldFormatABigDecimal() {
        assertThat(format(new BigDecimal(23.345)), is("23.34"));
    }


    @Test
    public void itShouldFormatADouble() {
        assertThat(format(23.345), is("23.34"));
    }
}
package com.teksystem.salestaxes.units.utils;

import com.teksystem.salestaxes.utils.Pair;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PairTest {

    @Test
    public void isShouldReturnFirstValueOfAPair() throws Exception {
        assertThat(new Pair<Double, Double>(2.3, 12.23).first(), is(2.3));
    }

    @Test
    public void isShouldReturnSecondValueOfAPair() throws Exception {
        assertThat(new Pair<Double, Double>(2.3, 12.23).second(), is(12.23));
    }
}
package com.teksystem.salestaxes.units.model.items;


import com.teksystem.salestaxes.model.items.TaxableItem;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaxableItemTest {

    @Test
    public void itShouldGetPrice() throws NegativeDecimalException {
        assertThat(new TaxableItem("music CD", new BigDecimal(14.99)).getPrice().doubleValue(), is(14.99));
    }

    @Test
    public void itShouldGetName() throws NegativeDecimalException {
        assertThat(new TaxableItem("music CD", new BigDecimal(14.99)).getName(), is("music CD"));
    }
}
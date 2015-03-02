package com.teksystem.salestaxes.units.model.items;

import com.teksystem.salestaxes.model.items.NonTaxableItem;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NonTaxableItemTest {

    @Test
    public void itShouldGetPrice() throws NegativeDecimalException {
        assertThat(new NonTaxableItem("book", new BigDecimal(12.49)).getPrice().doubleValue(), is(12.49));
    }

    @Test
    public void testGetName() throws NegativeDecimalException {
        assertThat(new NonTaxableItem("book", new BigDecimal(12.49)).getName(), is("book"));
    }
}
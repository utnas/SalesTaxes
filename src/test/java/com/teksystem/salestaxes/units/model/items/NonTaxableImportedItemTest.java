package com.teksystem.salestaxes.units.model.items;

import com.teksystem.salestaxes.model.items.NonTaxableImportedItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NonTaxableImportedItemTest {

    @Test
    public void testGetPrice() {
        assertThat(new NonTaxableImportedItem("book", new BigDecimal(12.49)).getPrice().doubleValue(), is(12.49));
    }

    @Test
    public void testGetName() {
        assertThat(new NonTaxableImportedItem("book", new BigDecimal(12.49)).getName(), is("book"));
    }
}
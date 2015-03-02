package com.teksystem.salestaxes.units.model.items;

import com.teksystem.salestaxes.model.items.TaxableImportedItem;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaxableImportedItemTest {


    @Test
    public void itShouldGetName() throws NegativeDecimalException {
        assertThat(new TaxableImportedItem("box of chocolates", new BigDecimal(10.0)).getName(), is("box of chocolates"));
    }

    @Test
    public void itShouldGetPrice() throws NegativeDecimalException {
        assertThat(new TaxableImportedItem("box of chocolates", new BigDecimal(10.0)).getPrice().doubleValue(), is(10.0));
    }
}
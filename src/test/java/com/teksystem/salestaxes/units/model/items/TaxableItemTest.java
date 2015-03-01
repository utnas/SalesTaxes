package com.teksystem.salestaxes.units.model.items;


import com.teksystem.salestaxes.model.items.TaxableItem;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaxableItemTest {

    @Test
    public void itShouldGetPrice() throws NegativeDecimalException {
        assertThat(new TaxableItem("music CD", 14.99).getPrice(), is(14.99));
    }

    @Test
    public void itShouldGetName() throws NegativeDecimalException {
        assertThat(new TaxableItem("music CD", 14.99).getName(), is("music CD"));
    }

    @Test
    public void itShouldAcceptTaxVisitor() throws Exception {
        //TODO I should Mock the class
    }
}
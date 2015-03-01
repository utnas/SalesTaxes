package com.teksystem.salestaxes.units.model.items;

import com.teksystem.salestaxes.model.items.NonTaxableImportedItem;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NonTaxableImportedItemTest {

    @Test
    public void testGetPrice() throws NegativeDecimalException {
        assertThat(new NonTaxableImportedItem("book", 12.49).getPrice(), is(12.49));
    }

    @Test
    public void testGetName() throws NegativeDecimalException {
        assertThat(new NonTaxableImportedItem("book", 12.49).getName(), is("book"));
    }

    @Test
    public void testAccept() {
        //TODO I should mock the class
    }
}
package com.teksystem.salestaxes.units.model.items;

import com.teksystem.salestaxes.model.items.NonTaxableItem;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NonTaxableItemTest {

    @Test
    public void itShouldGetPrice() {
        assertThat(new NonTaxableItem("book", 12.49).getPrice(), is(12.49));
    }

    @Test
    public void testGetName() {
        assertThat(new NonTaxableItem("book", 12.49).getName(), is("book"));
    }

    @Test
    public void testAccept() {
        //TODO I should mock the class
    }
}
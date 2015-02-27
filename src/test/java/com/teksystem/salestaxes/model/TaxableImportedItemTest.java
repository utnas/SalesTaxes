package com.teksystem.salestaxes.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaxableImportedItemTest {


    @Test
    public void itShouldGetName() {
        assertThat(new TaxableImportedItem("box of chocolates", 10.0).getName(), is("box of chocolates"));
    }

    @Test
    public void itShouldGetPrice() {
        assertThat(new TaxableImportedItem("box of chocolates", 10.0).getPrice(), is(10.0));
    }

    @Test
    public void testAccept() {
        //TODO I should mock the class
    }
}
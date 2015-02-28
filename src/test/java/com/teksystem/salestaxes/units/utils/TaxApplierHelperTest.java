package com.teksystem.salestaxes.units.utils;

import com.teksystem.salestaxes.units.context.TaxApplier;
import com.teksystem.salestaxes.units.model.TaxableItem;
import org.junit.Test;

import static com.teksystem.salestaxes.units.utils.TaxApplierHelper.addItemsTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaxApplierHelperTest {

    @Test
    public void itShouldReturnAnEmptyCollection() {
        assertThat(addItemsTo(new TaxApplier(10.0, 5.0)).size(), is(0));
    }

    @Test
    public void itShouldReturnACollectionOfGivenItems() {
        assertThat(addItemsTo(new TaxApplier(10.0, 5.0), new TaxableItem("My Item", 234.99)).size(), is(1));
    }
}
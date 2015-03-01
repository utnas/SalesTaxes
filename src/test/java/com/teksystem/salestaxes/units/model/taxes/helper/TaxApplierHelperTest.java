package com.teksystem.salestaxes.units.model.taxes.helper;

import com.teksystem.salestaxes.context.TaxApplier;
import com.teksystem.salestaxes.model.items.TaxableItem;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitorImpl;
import org.junit.Test;

import static com.teksystem.salestaxes.model.taxes.helper.TaxApplierHelper.addItemsTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaxApplierHelperTest {

    @Test
    public void itShouldReturnAnEmptyCollection() throws NegativeDecimalException {
        assertThat(addItemsTo(new TaxApplier(new TaxVisitorImpl(10.0, 5.0))).size(), is(0));
    }

    @Test
    public void itShouldReturnACollectionOfGivenItems() throws NegativeDecimalException {
        assertThat(addItemsTo(new TaxApplier(new TaxVisitorImpl(10.0, 5.0)), new TaxableItem("My Item", 234.99)).size(), is(1));
    }
}
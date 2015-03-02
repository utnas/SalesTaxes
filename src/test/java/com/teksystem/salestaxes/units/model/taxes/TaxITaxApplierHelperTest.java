package com.teksystem.salestaxes.units.model.taxes;

import com.teksystem.salestaxes.receipt.calculator.tax.TaxITaxApplier;
import com.teksystem.salestaxes.model.items.TaxableItem;
import com.teksystem.salestaxes.model.taxes.TaxVisitorImpl;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import org.junit.Test;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.receipt.calculator.tax.TaxApplierHelper.addItemsTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaxITaxApplierHelperTest {

    @Test
    public void itShouldReturnAnEmptyCollection() throws NegativeDecimalException {
        assertThat(addItemsTo(new TaxITaxApplier(new TaxVisitorImpl(10.0, 5.0))).size(), is(0));
    }

    @Test
    public void itShouldReturnACollectionOfGivenItems() throws NegativeDecimalException {
        final TaxITaxApplier taxApplier = new TaxITaxApplier(new TaxVisitorImpl(10.0, 5.0));
        final TaxableItem taxableItem = new TaxableItem("My Item", new BigDecimal(234.99));

        assertThat(addItemsTo(taxApplier, taxableItem).size(), is(1));
    }
}
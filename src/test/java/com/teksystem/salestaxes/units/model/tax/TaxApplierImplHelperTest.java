package com.teksystem.salestaxes.units.model.tax;

import com.teksystem.salestaxes.model.items.Item;
import com.teksystem.salestaxes.model.items.TaxableItem;
import com.teksystem.salestaxes.receipt.calculator.tax.TaxApplierImpl;
import org.junit.Test;

import static com.teksystem.salestaxes.receipt.calculator.tax.TaxApplierHelper.addItemsTo;
import static com.teksystem.salestaxes.units.model.items.ItemMockHelper.mockItem;
import static com.teksystem.salestaxes.units.receipt.calculator.TestHelper.createTaxApplier;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaxApplierImplHelperTest {

    @Test
    public void itShouldReturnAnEmptyCollection() {
        assertThat(addItemsTo(createTaxApplier(10.0, 5.0)).size(), is(0));
    }

    @Test
    public void itShouldReturnACollectionOfGivenItems() {
        final TaxApplierImpl taxApplier = createTaxApplier(10.0, 5.0);
        final Item taxableItem = mockItem("My Item", (234.99), TaxableItem.class);

        assertThat(addItemsTo(taxApplier, taxableItem).size(), is(1));
    }
}
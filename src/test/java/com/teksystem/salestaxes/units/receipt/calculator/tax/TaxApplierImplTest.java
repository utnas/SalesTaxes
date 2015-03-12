package com.teksystem.salestaxes.units.receipt.calculator.tax;

import com.teksystem.salestaxes.model.items.*;
import com.teksystem.salestaxes.receipt.calculator.tax.TaxApplierImpl;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import org.junit.Test;

import static com.teksystem.salestaxes.receipt.calculator.tax.TaxApplierHelper.addItemsTo;
import static com.teksystem.salestaxes.units.model.items.ItemMockHelper.mockItem;
import static com.teksystem.salestaxes.units.receipt.calculator.TestHelper.createTaxApplier;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaxApplierImplTest {

    @Test
    public void itShouldAddItemsToTaxApplier()  {
        final TaxApplierImpl taxApplier = createTaxApplier(10.0, 5.0);
        addItemsTo(taxApplier,
                mockItem("music box", 20.30, TaxableImportedItem.class),
                mockItem("book", 12.49, NonTaxableImportedItem.class),
                mockItem("book", 12.49, NonTaxableItem.class),
                mockItem("music box", 20.30, TaxableItem.class)
        );
        assertThat(taxApplier.getTaxedItems().size(), is(4));
    }

    @Test
    public void itemListShouldBeEmptyByDefault()  {
        assertThat(createTaxApplier(2.0, 23.0).getTaxedItems().size(), is(0));
    }

    @Test
    public void itShouldClearItemsList()  {
        final Item item = mockItem("music box", 20.30, TaxableItem.class);
        final TaxApplierImpl taxApplier = createTaxApplier(10.0, 5.0);
        taxApplier.applyTaxOn(item);
        taxApplier.clearItemsList();

        assertThat(taxApplier.getTaxedItems().size(), is(0));
    }
}
package com.teksystem.salestaxes.units.context;

import com.teksystem.salestaxes.units.model.NoneTaxableImportedItem;
import com.teksystem.salestaxes.units.model.NoneTaxableItem;
import com.teksystem.salestaxes.units.model.TaxableImportedItem;
import com.teksystem.salestaxes.units.model.TaxableItem;
import org.junit.Test;

import static com.teksystem.salestaxes.units.utils.TaxApplierHelper.addItemsTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaxApplierTest {

    @Test
    public void itShouldAddItemsToTaxApplier() {
        final TaxApplier taxApplier = new TaxApplier(10.0, 5.0);
        addItemsTo(taxApplier,
                new TaxableImportedItem("music box", 20.30),
                new NoneTaxableImportedItem("book", 12.49),
                new NoneTaxableItem("book", 12.49),
                new TaxableItem("music box", 20.30)
        );

        assertThat(taxApplier.getTaxedItems().size(), is(4));
    }

    @Test
    public void itemListShouldBeEmptyByDefault() {
        final TaxApplier taxApplier = new TaxApplier(2.0, 23.0);
        assertThat(taxApplier.getTaxedItems().size(), is(0));
    }

}
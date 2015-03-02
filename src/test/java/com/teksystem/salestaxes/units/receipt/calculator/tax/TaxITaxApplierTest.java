package com.teksystem.salestaxes.units.receipt.calculator.tax;

import com.teksystem.salestaxes.receipt.calculator.tax.TaxITaxApplier;
import com.teksystem.salestaxes.model.items.NonTaxableImportedItem;
import com.teksystem.salestaxes.model.items.NonTaxableItem;
import com.teksystem.salestaxes.model.items.TaxableImportedItem;
import com.teksystem.salestaxes.model.items.TaxableItem;
import com.teksystem.salestaxes.model.tax.TaxVisitorImpl;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import org.junit.Test;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.receipt.calculator.tax.TaxApplierHelper.addItemsTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaxITaxApplierTest {

    @Test
    public void itShouldAddItemsToTaxApplier() throws NegativeDecimalException {
        final TaxITaxApplier taxApplier = new TaxITaxApplier(new TaxVisitorImpl(10.0, 5.0));
        addItemsTo(taxApplier,
                new TaxableImportedItem("music box", new BigDecimal(20.30)),
                new NonTaxableImportedItem("book", new BigDecimal(12.49)),
                new NonTaxableItem("book", new BigDecimal(12.49)),
                new TaxableItem("music box", new BigDecimal(20.30))
        );
        assertThat(taxApplier.getTaxedItems().size(), is(4));
    }

    @Test
    public void itemListShouldBeEmptyByDefault() throws NegativeDecimalException {
        final TaxITaxApplier taxApplier = new TaxITaxApplier(new TaxVisitorImpl(2.0, 23.0));
        assertThat(taxApplier.getTaxedItems().size(), is(0));
    }
}
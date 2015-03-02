package com.teksystem.salestaxes.units.context;

import com.teksystem.salestaxes.context.TaxApplier;
import com.teksystem.salestaxes.model.items.NonTaxableImportedItem;
import com.teksystem.salestaxes.model.items.NonTaxableItem;
import com.teksystem.salestaxes.model.items.TaxableImportedItem;
import com.teksystem.salestaxes.model.items.TaxableItem;
import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitorImpl;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import org.junit.Test;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.model.taxes.helper.TaxApplierHelper.addItemsTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaxApplierTest {

    @Test
    public void itShouldAddItemsToTaxApplier() throws NegativeDecimalException {
        final TaxApplier taxApplier = new TaxApplier(new TaxVisitorImpl(10.0, 5.0));
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
        final TaxApplier taxApplier = new TaxApplier(new TaxVisitorImpl(2.0, 23.0));
        assertThat(taxApplier.getTaxedItems().size(), is(0));
    }
}
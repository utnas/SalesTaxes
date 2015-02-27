package com.teksystem.salestaxes.context;

import com.teksystem.salestaxes.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaxApplierTest {

    @Test
    public void itShouldAddItemsToTaxApplier() {
        final TaxApplier taxApplier = new TaxApplier(0.10, 0.05);
        addAllItems(//
                taxApplier,
                new TaxableItem("music box", 20.30),//
                new NoneTaxableItem("book", 12.49), //
                new TaxableImportedItem("music box", 20.30),//
                new NoneTaxableImportedItem("book", 12.49)
        );

        assertThat(taxApplier.getTaxedItems().size(), is(4));
    }

    @Test
    public void testDisplayTotal() {

    }

    private static Collection addAllItems(final TaxApplier taxApplier, final Item... items) {
        Collection result = new ArrayList();
        for (final Item item : items) {
            taxApplier.addItem(item);
        }
        return Collections.unmodifiableCollection(result);
    }
}
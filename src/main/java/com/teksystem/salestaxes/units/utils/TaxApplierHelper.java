package com.teksystem.salestaxes.units.utils;

import com.teksystem.salestaxes.units.context.TaxApplier;
import com.teksystem.salestaxes.units.model.Item;

import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;

public class TaxApplierHelper {

    public static Collection addItemsTo(final TaxApplier taxApplier, final Item... items) {
        for (final Item item : items) {
            taxApplier.applyTaxOn(item);
        }
        return unmodifiableCollection(taxApplier.getTaxedItems());
    }
}

package com.teksystem.salestaxes.model.taxes.helper;

import com.teksystem.salestaxes.context.TaxApplier;
import com.teksystem.salestaxes.model.items.Item;
import com.teksystem.salestaxes.utils.NegativeDecimalException;

import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;

public class TaxApplierHelper {

    public static Collection addItemsTo(final TaxApplier taxApplier, final Item... items) throws NegativeDecimalException {
        for (final Item item : items) {
            taxApplier.applyTaxOn(item);
        }
        return unmodifiableCollection(taxApplier.getTaxedItems());
    }
}
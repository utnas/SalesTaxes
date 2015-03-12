package com.teksystem.salestaxes.receipt.calculator.tax;

import com.teksystem.salestaxes.model.items.Item;
import com.teksystem.salestaxes.utils.Pair;

import java.math.BigDecimal;
import java.util.Collection;

public interface TaxApplier {

    public void applyTaxOn(final Item item);

    public String formatTaxedItems();

    public void clearItemsList();

    Collection<? extends Pair<Item, BigDecimal>> getTaxedItems();
}

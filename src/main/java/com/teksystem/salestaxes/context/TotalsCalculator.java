package com.teksystem.salestaxes.context;

import com.teksystem.salestaxes.model.items.Item;
import com.teksystem.salestaxes.utils.Pair;

import java.math.BigDecimal;

public class TotalsCalculator {
    private final Iterable<Pair<Item, BigDecimal>> pairOfTaxedItemsTax;

    public TotalsCalculator(final Iterable<Pair<Item, BigDecimal>> pairOfTaxedItemsTax) {
        this.pairOfTaxedItemsTax = pairOfTaxedItemsTax;
    }

    public BigDecimal calculateTotalOfSalesTaxes() {
        BigDecimal result = new BigDecimal(0.0);
        for (final Pair<Item, BigDecimal> taxedItem : pairOfTaxedItemsTax) {
            result = result.add(taxedItem.second());
        }
        return result;
    }

    public BigDecimal calculateTotalOfTaxedItems() {
        BigDecimal result = new BigDecimal(0.0);
        for (final Pair<Item, BigDecimal> taxedItem : pairOfTaxedItemsTax) {
            result = result.add(taxedItem.first().getPrice());
        }
        return result;
    }
}

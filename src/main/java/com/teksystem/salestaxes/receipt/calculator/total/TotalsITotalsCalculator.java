package com.teksystem.salestaxes.receipt.calculator.total;

import com.teksystem.salestaxes.model.items.Item;
import com.teksystem.salestaxes.utils.Pair;

import java.math.BigDecimal;

public class TotalsITotalsCalculator implements ITotalsCalculator {
    private final Iterable<Pair<Item, BigDecimal>> pairOfTaxedItemsTax;

    public TotalsITotalsCalculator(final Iterable<Pair<Item, BigDecimal>> pairOfTaxedItemsTax) {
        this.pairOfTaxedItemsTax = pairOfTaxedItemsTax;
    }

    @Override
    public BigDecimal calculateTotalOfSalesTaxes() {
        BigDecimal result = new BigDecimal(0.0);
        for (final Pair<Item, BigDecimal> taxedItem : pairOfTaxedItemsTax) {
            result = result.add(taxedItem.second());
        }
        return result;
    }

    @Override
    public BigDecimal calculateTotalOfTaxedItems() {
        BigDecimal result = new BigDecimal(0.0);
        for (final Pair<Item, BigDecimal> taxedItem : pairOfTaxedItemsTax) {
            result = result.add(taxedItem.first().getPrice());
        }
        return result;
    }
}

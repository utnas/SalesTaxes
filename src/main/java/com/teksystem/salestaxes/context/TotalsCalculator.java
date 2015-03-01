package com.teksystem.salestaxes.context;

import com.teksystem.salestaxes.model.items.Item;
import com.teksystem.salestaxes.utils.Pair;

public class TotalsCalculator {
    private Iterable<Pair<Item, Double>> taxedItems;

    public TotalsCalculator(final Iterable<Pair<Item, Double>> taxedItems) {
        this.taxedItems = taxedItems;
    }

    public Double calculateTotalOfSalesTaxes() {
        Double result = 0.0;
        for (final Pair<Item, Double> taxedItem : taxedItems) {
            result += taxedItem.second();
        }
        return result;
    }

    public Double calculateTotalOfTaxedItems() {
        Double result = 0.0;
        for (final Pair<Item, Double> taxedItem : taxedItems) {
            result += taxedItem.first().getPrice();
        }
        return result;
    }
}

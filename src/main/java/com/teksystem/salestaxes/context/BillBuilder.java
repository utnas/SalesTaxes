package com.teksystem.salestaxes.context;

import com.sun.tools.javac.util.Pair;
import com.teksystem.salestaxes.model.items.Item;

import static com.teksystem.salestaxes.utils.CustomFormatter.format;
import static java.lang.System.getProperty;

public class BillBuilder {
    private final TaxApplier taxApplier;

    public BillBuilder(final TaxApplier taxApplier) {
        this.taxApplier = taxApplier;
    }

    public final String displayBill() {
        final StringBuilder result = new StringBuilder("");

        for (final Pair<Item, Double> taxedItem : taxApplier.getTaxedItems()) {
            result.append("1 ");
            result.append(taxedItem.fst.getName());
            result.append(": ");
            result.append(format(taxedItem.fst.getPrice()));
            result.append(getProperty("line.separator"));
        }

        result.append("Sales Taxes: ").append(format(taxApplier.calculateTotalOfSalesTaxes()));
        result.append(getProperty("line.separator"));
        result.append("Total: ").append(format(taxApplier.calculateTotalOfTaxedItems()));

        return result.toString().trim();
    }
}

package com.teksystem.salestaxes.context;

import com.sun.tools.javac.util.Pair;
import com.teksystem.salestaxes.model.items.Item;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.utils.CustomFormatter.format;
import static java.lang.System.getProperty;
import static java.math.BigDecimal.valueOf;

public class BillBuilder {
    private Iterable<Pair<Item, Double>> taxedItems;

    public BillBuilder(Iterable<Pair<Item, Double>> taxedItems) {
        this.taxedItems = taxedItems;
    }

    public final String displayBill() {
        final StringBuilder result = new StringBuilder("");
        BigDecimal totalTax = new BigDecimal(0.0);
        BigDecimal total = new BigDecimal(0.0);

        for (final Pair<Item, Double> taxedItem : taxedItems) {
            result.append("1 ");
            result.append(taxedItem.fst.getName());
            result.append(": ");
            result.append(format(taxedItem.fst.getPrice()));
            result.append(getProperty("line.separator"));
            
            totalTax = totalTax.add(valueOf(taxedItem.snd));
            total = total.add(valueOf(taxedItem.fst.getPrice()));
        }
        result.append("Sales Taxes: ").append(format(totalTax));
        result.append(getProperty("line.separator"));
        result.append("Total: ").append(format(total));

        return result.toString().trim();
    }
}

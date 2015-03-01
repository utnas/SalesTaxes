package com.teksystem.salestaxes.units.context;

import com.sun.tools.javac.util.Pair;
import com.teksystem.salestaxes.units.model.Item;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.units.utils.CustomFormatter.format;
import static java.math.BigDecimal.valueOf;

public class SalesTaxesDisplay {
    private Iterable<Pair<Item, Double>> taxedItems;

    public SalesTaxesDisplay(Iterable<Pair<Item, Double>> taxedItems) {
        this.taxedItems = taxedItems;
    }

    public final String displayBill() {
        final StringBuilder result = new StringBuilder("");
        BigDecimal totalTax = new BigDecimal(0.0);
        BigDecimal total = new BigDecimal(0.0);

        for (final Pair<Item, Double> taxedItem : taxedItems) {
            final Item itemTek = taxedItem.fst;
            final Double payedTax = taxedItem.snd;
            totalTax = totalTax.add(valueOf(payedTax));
            result.append("1 ");
            result.append(itemTek.getName());
            result.append(": ");
            result.append(format(itemTek.getPrice() + payedTax));
            result.append(System.getProperty("line.separator"));
            total = total.add(valueOf(payedTax)).add(valueOf(itemTek.getPrice()));
        }
        result.append("Sales Taxes: ").append(format(totalTax));
        result.append(System.getProperty("line.separator"));
        result.append("Total: ").append(format(total));

        return result.toString().trim();
    }
}

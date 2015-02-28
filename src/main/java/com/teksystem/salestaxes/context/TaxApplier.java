package com.teksystem.salestaxes.context;

import com.sun.tools.javac.util.Pair;
import com.teksystem.salestaxes.model.*;
import com.teksystem.salestaxes.visitor.TaxVisitorImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static com.teksystem.salestaxes.utils.CustomFormatter.format;
import static java.math.BigDecimal.valueOf;
import static java.util.Collections.unmodifiableCollection;


public final class TaxApplier {
    private final ArrayList<Pair<Item, Double>> taxedItems = new ArrayList<Pair<Item, Double>>();
    private final TaxVisitorImpl taxVisitor;

    public TaxApplier(final Double basicRate, final Double importationRate) {
        taxVisitor = new TaxVisitorImpl(basicRate, importationRate);
    }

    public final void addItem(final Item itemTek) {
        //TODO: not really elegant, should be changed
        if (itemTek instanceof TaxableItem) {
            taxedItems.add(taxVisitor.visit((TaxableItem) itemTek));
            return;
        }
        if (itemTek instanceof NoneTaxableItem) {
            taxedItems.add(taxVisitor.visit((NoneTaxableItem) itemTek));
            return;
        }
        if (itemTek instanceof TaxableImportedItem) {
            taxedItems.add(taxVisitor.visit((TaxableImportedItem) itemTek));
            return;
        }
        if (itemTek instanceof NoneTaxableImportedItem) {
            taxedItems.add(taxVisitor.visit((NoneTaxableImportedItem) itemTek));
        }
    }

    public final String displayTotal() {
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

    public final void clearItemsList() {
        taxedItems.clear();
    }

    public final Collection<Pair<Item, Double>> getTaxedItems() {
        return unmodifiableCollection(taxedItems);
    }
}

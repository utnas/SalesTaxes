package com.teksystem.salestaxes.context;

import com.sun.tools.javac.util.Pair;
import com.teksystem.salestaxes.model.*;
import com.teksystem.salestaxes.visitor.TaxVisitorImpl;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import static java.util.Collections.unmodifiableCollection;


public class TaxApplier {
    private final ArrayList<Pair<Item, Double>> taxedItems = new ArrayList<Pair<Item, Double>>();
    private TaxVisitorImpl taxVisitor;

    public TaxApplier(final Double basicRate, final Double importationRate) {
        taxVisitor = new TaxVisitorImpl(basicRate, importationRate);
    }

    public void addItem(final Item itemTek) {
        //TODO: not really elegant solution, should be changed
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


    public String displayTotal() {
        final StringBuilder result = new StringBuilder("");
        Double totalTax = 0.0;
        Double total = 0.0;
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,##0.00");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));

        for (final Pair<Item, Double> taxedItem : taxedItems) {
            final Item itemTek = taxedItem.fst;
            final Double payedTax = taxedItem.snd;
            totalTax += payedTax;
            formatMessage(result, //
                    decimalFormat.format(itemTek.getPrice() + payedTax),//
                    itemTek.getName());
            total += payedTax + itemTek.getPrice();
        }
        result.append("Sales Taxes: ").append(decimalFormat.format(totalTax));
        result.append(System.getProperty("line.separator"));
        result.append("Total: ").append(decimalFormat.format(total));

        return result.toString().trim();
    }

    private static void formatMessage(StringBuilder result, String format, String itemName) {
        result.append("1 ");
        result.append(itemName);
        result.append(": ");
        result.append(format);
        result.append(System.getProperty("line.separator"));
    }

    public void clearItemsList() {
        taxedItems.clear();
    }

    public Collection<Pair<Item, Double>> getTaxedItems() {
        return unmodifiableCollection(taxedItems);
    }
}

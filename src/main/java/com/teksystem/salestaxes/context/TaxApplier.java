package com.teksystem.salestaxes.context;

import com.sun.tools.javac.util.Pair;
import com.teksystem.salestaxes.model.*;
import com.teksystem.salestaxes.visitor.TaxVisitorImpl;

import java.util.ArrayList;


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
        final StringBuilder result = new StringBuilder("OUTPUT");
        Double totalTax = 0.0;
        Double total = 0.0;

        for (final Pair<Item, Double> taxedItem : taxedItems) {
            final Item itemTek = taxedItem.fst;
            final Double payedTax = taxedItem.snd;
            totalTax += payedTax;
            result.append("1 ");
            result.append(itemTek.getName());
            result.append(" : ");
            result.append(itemTek.getPrice());
            result.append(payedTax);
            result.append(System.getProperty("line.separator"));
            total += totalTax + itemTek.getPrice();
        }

        result.append("Sales Total: ").append(totalTax);
        result.append(System.getProperty("line.separator"));
        result.append("Total: ").append(total);

        return result.toString();
    }
}

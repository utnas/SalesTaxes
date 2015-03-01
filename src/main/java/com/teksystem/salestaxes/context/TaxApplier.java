package com.teksystem.salestaxes.context;

import com.sun.tools.javac.util.Pair;
import com.teksystem.salestaxes.model.items.*;
import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitorImpl;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;


public class TaxApplier {
    private final ArrayList<Pair<Item, Double>> taxedItems = new ArrayList<Pair<Item, Double>>();
    private final TaxVisitorImpl taxVisitor;

    public TaxApplier(final TaxVisitorImpl taxVisitor) {
        this.taxVisitor = taxVisitor;
    }

    public void applyTaxOn(final Item item) {
        //TODO: not really elegant, should be refactored
        if (item instanceof TaxableItem) {
            taxedItems.add(taxVisitor.visit((TaxableItem) item));
            return;
        }
        if (item instanceof NonTaxableItem) {
            taxedItems.add(taxVisitor.visit((NonTaxableItem) item));
            return;
        }
        if (item instanceof TaxableImportedItem) {
            taxedItems.add(taxVisitor.visit((TaxableImportedItem) item));
            return;
        }
        if (item instanceof NonTaxableImportedItem) {
            taxedItems.add(taxVisitor.visit((NonTaxableImportedItem) item));
        }
    }

    public final Collection<Pair<Item, Double>> getTaxedItems() {
        return unmodifiableCollection(taxedItems);
    }

    public Double calculateSalesTaxes() {
        Double result = 0.0;
        for (final Pair<Item, Double> taxedItem : taxedItems) {
            result += taxedItem.snd;
        }
        return result;
    }

    public Double calculateTaxedItemsTotal() {
        Double result = 0.0;
        for (final Pair<Item, Double> taxedItem : taxedItems) {
            result += taxedItem.fst.getPrice();
        }
        return result;
    }
}
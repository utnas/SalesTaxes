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

    public void applyTaxOn(final Item itemTek) {
        //TODO: not really elegant, should be refactored
        if (itemTek instanceof TaxableItem) {
            taxedItems.add(taxVisitor.visit((TaxableItem) itemTek));
            return;
        }
        if (itemTek instanceof NonTaxableItem) {
            taxedItems.add(taxVisitor.visit((NonTaxableItem) itemTek));
            return;
        }
        if (itemTek instanceof TaxableImportedItem) {
            taxedItems.add(taxVisitor.visit((TaxableImportedItem) itemTek));
            return;
        }
        if (itemTek instanceof NonTaxableImportedItem) {
            taxedItems.add(taxVisitor.visit((NonTaxableImportedItem) itemTek));
        }
    }

    public final Collection<Pair<Item, Double>> getTaxedItems() {
        return unmodifiableCollection(taxedItems);
    }
}
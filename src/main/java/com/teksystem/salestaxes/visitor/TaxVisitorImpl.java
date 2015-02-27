package com.teksystem.salestaxes.visitor;

import com.sun.tools.javac.util.Pair;
import com.teksystem.salestaxes.model.*;

public class TaxVisitorImpl implements TaxVisitor {
    private final Double importationRate;
    private final Double basicRate;

    public TaxVisitorImpl(final Double basicRate, final Double importationRate) {
        this.importationRate = importationRate;
        this.basicRate = basicRate;
    }

    @Override
    public Pair<Item, Double> visit(final TaxableItem taxableItem) {
        return new Pair<Item, Double>(taxableItem, taxableItem.getPrice() * basicRate);
    }

    @Override
    public Pair<Item, Double> visit(final TaxableImportedItem taxableItem) {
        return new Pair<Item, Double>(taxableItem, (taxableItem.getPrice() * importationRate) + (taxableItem.getPrice() * basicRate));
    }

    @Override
    public Pair<Item, Double> visit(final NoneTaxableItem nonetaxableItem) {
        return new Pair<Item, Double>(nonetaxableItem, 0.0);
    }

    @Override
    public Pair<Item, Double> visit(final NoneTaxableImportedItem noneTaxableImportedItem) {
        return new Pair<Item, Double>(noneTaxableImportedItem, noneTaxableImportedItem.getPrice() * importationRate);
    }
}

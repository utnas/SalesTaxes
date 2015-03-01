package com.teksystem.salestaxes.units.visitor;

import com.sun.tools.javac.util.Pair;
import com.teksystem.salestaxes.units.model.*;

public interface TaxVisitor {
    public Pair<Item, Double> visit(final TaxableItem taxableItem);

    public Pair<Item, Double> visit(final TaxableImportedItem taxableItem);

    public Pair<Item, Double> visit(final NonTaxableItem nonTaxableItem);

    public Pair<Item, Double> visit(final NonTaxableImportedItem noneTaxableItem);
}

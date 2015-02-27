package com.teksystem.salestaxes.visitor;

import com.sun.tools.javac.util.Pair;
import com.teksystem.salestaxes.model.*;

public interface TaxVisitor {
    public Pair<Item, Double> visit(final TaxableItem taxableItem);

    public Pair<Item, Double> visit(final TaxableImportedItem taxableItem);

    public Pair<Item, Double> visit(final NoneTaxableItem nonetaxableItem);

    public Pair<Item, Double> visit(final NoneTaxableImportedItem noneTaxableItem);
}

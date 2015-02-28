package com.teksystem.salestaxes.visitor;

import com.sun.tools.javac.util.Pair;
import com.teksystem.salestaxes.model.*;
import com.teksystem.salestaxes.model.NoneTaxableImportedItem;
import com.teksystem.salestaxes.model.NoneTaxableItem;
import com.teksystem.salestaxes.model.TaxableImportedItem;
import com.teksystem.salestaxes.model.TaxableItem;

public interface TaxVisitor {
    public Pair<Item, Double> visit(final TaxableItem taxableItem);

    public Pair<Item, Double> visit(final TaxableImportedItem taxableItem);

    public Pair<Item, Double> visit(final NoneTaxableItem nonetaxableItem);

    public Pair<Item, Double> visit(final NoneTaxableImportedItem noneTaxableItem);
}

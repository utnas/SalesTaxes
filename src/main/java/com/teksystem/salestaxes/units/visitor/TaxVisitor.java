package com.teksystem.salestaxes.units.visitor;

import com.sun.tools.javac.util.Pair;
import com.teksystem.salestaxes.units.model.*;
import com.teksystem.salestaxes.units.model.NoneTaxableImportedItem;
import com.teksystem.salestaxes.units.model.NoneTaxableItem;
import com.teksystem.salestaxes.units.model.TaxableImportedItem;
import com.teksystem.salestaxes.units.model.TaxableItem;

public interface TaxVisitor {
    public Pair<Item, Double> visit(final TaxableItem taxableItem);

    public Pair<Item, Double> visit(final TaxableImportedItem taxableItem);

    public Pair<Item, Double> visit(final NoneTaxableItem nonetaxableItem);

    public Pair<Item, Double> visit(final NoneTaxableImportedItem noneTaxableItem);
}

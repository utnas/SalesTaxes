package com.teksystem.salestaxes.model.taxes.visitor;

import com.teksystem.salestaxes.model.items.*;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import com.teksystem.salestaxes.utils.Pair;

public interface TaxVisitor {
    public Pair<Item, Double> visit(final TaxableItem taxableItem) throws NegativeDecimalException;

    public Pair<Item, Double> visit(final TaxableImportedItem taxableItem) throws NegativeDecimalException;

    public Pair<Item, Double> visit(final NonTaxableItem nonTaxableItem);

    public Pair<Item, Double> visit(final NonTaxableImportedItem noneTaxableItem) throws NegativeDecimalException;
}

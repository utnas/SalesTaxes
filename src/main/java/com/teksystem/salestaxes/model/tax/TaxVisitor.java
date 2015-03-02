package com.teksystem.salestaxes.model.tax;

import com.teksystem.salestaxes.model.items.*;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import com.teksystem.salestaxes.utils.Pair;

import java.math.BigDecimal;

public interface TaxVisitor {
    public Pair<Item, BigDecimal> visit(final TaxableItem item) throws NegativeDecimalException;

    public Pair<Item, BigDecimal> visit(final TaxableImportedItem taxableItem) throws NegativeDecimalException;

    public Pair<Item, BigDecimal> visit(final NonTaxableItem nonTaxableItem);

    public Pair<Item, BigDecimal> visit(final NonTaxableImportedItem noneTaxableItem) throws NegativeDecimalException;
}

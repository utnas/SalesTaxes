package com.teksystem.salestaxes.model.tax;

import com.teksystem.salestaxes.model.items.NonTaxableImportedItem;
import com.teksystem.salestaxes.model.items.NonTaxableItem;
import com.teksystem.salestaxes.model.items.TaxableImportedItem;
import com.teksystem.salestaxes.model.items.TaxableItem;
import com.teksystem.salestaxes.utils.NegativeDecimalException;

public interface TaxVisitor<T> {
    public T visit(final TaxableItem item) throws NegativeDecimalException;

    public T visit(final TaxableImportedItem taxableItem) throws NegativeDecimalException;

    public T visit(final NonTaxableItem nonTaxableItem);

    public T visit(final NonTaxableImportedItem noneTaxableItem) throws NegativeDecimalException;
}
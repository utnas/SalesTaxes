package com.teksystem.salestaxes.receipt.calculator.tax;

import com.teksystem.salestaxes.model.items.*;
import com.teksystem.salestaxes.model.tax.TaxVisitor;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import com.teksystem.salestaxes.utils.Pair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static com.teksystem.salestaxes.utils.CustomDecimalFormatter.format;
import static java.lang.System.getProperty;
import static java.util.Collections.unmodifiableCollection;


public class TaxApplierImpl implements TaxApplier {
    private final ArrayList<Pair<Item, BigDecimal>> taxedItems = new ArrayList<>();
    private final TaxVisitor<Pair<Item, BigDecimal>> taxVisitor;

    public TaxApplierImpl(final TaxVisitor<Pair<Item, BigDecimal>> taxVisitor) {
        this.taxVisitor = taxVisitor;
    }

    @Override
    public void applyTaxOn(final Item item) throws NegativeDecimalException {
        //TODO: not really elegant
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

    @Override
    public String formatTaxedItems() {
        final StringBuilder result = new StringBuilder("");
        for (final Pair<Item, BigDecimal> taxedItem : taxedItems) {
            result.append("1 ");
            result.append(taxedItem.first().getName());
            result.append(": ");
            result.append(format(taxedItem.first().getPrice()));
            result.append(getProperty("line.separator"));
        }
        return result.toString();
    }

    public final Collection<Pair<Item, BigDecimal>> getTaxedItems() {
        return unmodifiableCollection(taxedItems);
    }

    @Override
    public void clearItemsList() {
        taxedItems.clear();
    }
}
package com.teksystem.salestaxes.context;

import com.teksystem.salestaxes.model.items.*;
import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitorImpl;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import com.teksystem.salestaxes.utils.Pair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static com.teksystem.salestaxes.utils.CustomDecimalFormatter.format;
import static java.lang.System.getProperty;
import static java.util.Collections.unmodifiableCollection;


public class TaxApplier {
    private final ArrayList<Pair<Item, BigDecimal>> taxedItems = new ArrayList<>();
    private final TaxVisitorImpl taxVisitor;

    public TaxApplier(final TaxVisitorImpl taxVisitor) {
        this.taxVisitor = taxVisitor;
    }

    public void applyTaxOn(final Item item) throws NegativeDecimalException {
        //TODO: not really elegant, should be refactored
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

    public final Collection<Pair<Item, BigDecimal>> getTaxedItems() {
        return unmodifiableCollection(taxedItems);
    }

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
}
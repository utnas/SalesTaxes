package com.teksystem.salestaxes.model.items;

import com.teksystem.salestaxes.model.tax.TaxVisitor;

import java.math.BigDecimal;

public class NonTaxableImportedItem extends AbstractItem {

    public NonTaxableImportedItem(final String name, final BigDecimal price) {
        super(name, price);
    }

    @Override
    public void accept(final TaxVisitor taxVisitor) {
        taxVisitor.visit(this);
    }
}

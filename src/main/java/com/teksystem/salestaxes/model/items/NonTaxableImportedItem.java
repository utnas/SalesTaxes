package com.teksystem.salestaxes.model.items;

import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitor;

public class NonTaxableImportedItem extends AbstractItem {

    public NonTaxableImportedItem(final String name, final Double price) {
        super(name, price);
    }

    @Override
    public void accept(final TaxVisitor taxVisitor) {
        taxVisitor.visit(this);
    }
}

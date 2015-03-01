package com.teksystem.salestaxes.model.items;

import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitor;

public class NonTaxableItem extends AbstractItem {

    public NonTaxableItem(final String name, final Double price) {
        super(name, price);
    }

    @Override
    public void accept(final TaxVisitor taxVisitor) {
        taxVisitor.visit(this);
    }
}

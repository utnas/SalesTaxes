package com.teksystem.salestaxes.model.items;

import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitor;

public class TaxableItem extends AbstractItem {

    public TaxableItem(final String name, final Double price) {
        super(name, price);
    }

    @Override
    public void accept(final TaxVisitor taxVisitor) {
        taxVisitor.visit(this);
    }
}

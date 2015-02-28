package com.teksystem.salestaxes.units.model;

import com.teksystem.salestaxes.units.visitor.TaxVisitor;

public class NoneTaxableItem extends AbstractItem {

    public NoneTaxableItem(final String name, final Double price) {
        super(name, price);
    }

    @Override
    public void accept(final TaxVisitor taxVisitor) {
        taxVisitor.visit(this);
    }
}

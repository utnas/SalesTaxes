package com.teksystem.salestaxes.model;

import com.teksystem.salestaxes.visitor.TaxVisitor;

public final class NoneTaxableItem implements Item {
    private final String name;
    private final Double price;

    public NoneTaxableItem(final String name, final Double price) {
        this.price = price;
        this.name = name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void accept(final TaxVisitor taxVisitor) {
        taxVisitor.visit(this);
    }
}

package com.teksystem.salestaxes.model;

import com.teksystem.salestaxes.visitor.TaxVisitor;

public class TaxableItem implements Item {
    private final String name;
    private final Double price;

    public TaxableItem(final String name, final Double price) {
        this.name = name;
        this.price = price;
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

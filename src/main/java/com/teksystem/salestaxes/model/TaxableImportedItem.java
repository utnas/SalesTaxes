package com.teksystem.salestaxes.model;

import com.teksystem.salestaxes.visitor.TaxVisitor;

public class TaxableImportedItem implements Item {
    private final Double price;
    private final String name;

    public TaxableImportedItem(final String name, final Double price) {
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
    public void accept(TaxVisitor taxVisitor) {
        taxVisitor.visit(this);
    }
}

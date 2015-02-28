package com.teksystem.salestaxes.units.model;

import com.teksystem.salestaxes.units.visitor.TaxVisitor;

public class TaxableImportedItem extends AbstractItem {

    public TaxableImportedItem(final String name, final Double price) {
        super(name, price);
    }


    @Override
    public void accept(TaxVisitor taxVisitor) {
        taxVisitor.visit(this);
    }
}

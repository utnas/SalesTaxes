package com.teksystem.salestaxes.model.items;

import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitor;

public class TaxableImportedItem extends AbstractItem {

    public TaxableImportedItem(final String name, final Double price) {
        super(name, price);
    }


    @Override
    public void accept(TaxVisitor taxVisitor) {
        taxVisitor.visit(this);
    }
}

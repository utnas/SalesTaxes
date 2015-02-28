package com.teksystem.salestaxes.model;

import com.teksystem.salestaxes.model.AbstractItem;
import com.teksystem.salestaxes.visitor.TaxVisitor;

public class TaxableImportedItem extends AbstractItem {

    public TaxableImportedItem(final String name, final Double price) {
        super(name, price);
    }


    @Override
    public void accept(TaxVisitor taxVisitor) {
        taxVisitor.visit(this);
    }
}

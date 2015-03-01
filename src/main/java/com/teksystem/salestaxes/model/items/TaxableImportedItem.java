package com.teksystem.salestaxes.model.items;

import com.teksystem.salestaxes.utils.NegativeDecimalException;
import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitor;

public class TaxableImportedItem extends AbstractItem {

    public TaxableImportedItem(final String name, final Double price) throws NegativeDecimalException {
        super(name, price);
    }


    @Override
    public void accept(TaxVisitor taxVisitor) throws NegativeDecimalException {
        taxVisitor.visit(this);
    }
}

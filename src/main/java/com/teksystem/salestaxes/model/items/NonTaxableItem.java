package com.teksystem.salestaxes.model.items;

import com.teksystem.salestaxes.utils.NegativeDecimalException;
import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitor;

public class NonTaxableItem extends AbstractItem {

    public NonTaxableItem(final String name, final Double price) throws NegativeDecimalException {
        super(name, price);
    }

    @Override
    public void accept(final TaxVisitor taxVisitor) {
        taxVisitor.visit(this);
    }
}

package com.teksystem.salestaxes.model.items;

import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitor;
import com.teksystem.salestaxes.utils.NegativeDecimalException;

import java.math.BigDecimal;

public class TaxableImportedItem extends AbstractItem {

    public TaxableImportedItem(final String name, final BigDecimal price) throws NegativeDecimalException {
        super(name, price);
    }

    @Override
    public void accept(TaxVisitor taxVisitor) throws NegativeDecimalException {
        taxVisitor.visit(this);
    }
}

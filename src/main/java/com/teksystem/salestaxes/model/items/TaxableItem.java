package com.teksystem.salestaxes.model.items;

import com.teksystem.salestaxes.model.taxes.TaxVisitor;
import com.teksystem.salestaxes.utils.NegativeDecimalException;

import java.math.BigDecimal;

public class TaxableItem extends AbstractItem {

    public TaxableItem(final String name, final BigDecimal price) throws NegativeDecimalException {
        super(name, price);
    }

    @Override
    public void accept(final TaxVisitor taxVisitor) throws NegativeDecimalException {
        taxVisitor.visit(this);
    }
}

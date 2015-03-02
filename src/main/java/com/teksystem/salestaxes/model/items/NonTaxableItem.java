package com.teksystem.salestaxes.model.items;

import com.teksystem.salestaxes.model.tax.TaxVisitor;
import com.teksystem.salestaxes.utils.NegativeDecimalException;

import java.math.BigDecimal;

public class NonTaxableItem extends AbstractItem {

    public NonTaxableItem(final String name, final BigDecimal price) throws NegativeDecimalException {
        super(name, price);
    }

    @Override
    public void accept(final TaxVisitor taxVisitor) {
        taxVisitor.visit(this);
    }
}

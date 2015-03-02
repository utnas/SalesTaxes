package com.teksystem.salestaxes.model.items;

import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitor;
import com.teksystem.salestaxes.utils.NegativeDecimalException;

import java.math.BigDecimal;

public interface Item {

    public BigDecimal getPrice();

    public String getName();

    public void accept(final TaxVisitor taxVisitor) throws NegativeDecimalException;
}

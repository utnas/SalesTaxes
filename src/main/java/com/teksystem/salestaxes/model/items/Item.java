package com.teksystem.salestaxes.model.items;

import com.teksystem.salestaxes.model.tax.TaxVisitor;

import java.math.BigDecimal;

public interface Item {

    public BigDecimal getPrice();

    public String getName();

    public void accept(final TaxVisitor taxVisitor);
}

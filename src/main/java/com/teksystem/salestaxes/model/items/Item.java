package com.teksystem.salestaxes.model.items;

import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitor;

public interface Item {

    public Double getPrice();

    public String getName();

    public void accept(final TaxVisitor taxVisitor);
}

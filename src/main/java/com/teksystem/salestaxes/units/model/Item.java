package com.teksystem.salestaxes.units.model;

import com.teksystem.salestaxes.units.visitor.TaxVisitor;

public interface Item {

    public Double getPrice();

    public String getName();

    public void accept(final TaxVisitor taxVisitor);
}

package com.teksystem.salestaxes.model;

import com.teksystem.salestaxes.visitor.TaxVisitor;

public interface Item {

    public double getPrice();

    public String getName();

    public void accept(final TaxVisitor taxVisitor);
}

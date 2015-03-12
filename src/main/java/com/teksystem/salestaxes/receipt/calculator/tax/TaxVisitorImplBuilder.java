package com.teksystem.salestaxes.receipt.calculator.tax;

import com.teksystem.salestaxes.model.tax.TaxVisitorImpl;
import com.teksystem.salestaxes.utils.NegativeDecimalException;

import static java.lang.String.format;

public class TaxVisitorImplBuilder {
    private final double basicRate;
    private final double importRate;

    public TaxVisitorImplBuilder(final double basicRate, final double importRate) {
        this.basicRate = basicRate;
        this.importRate = importRate;
    }

    public TaxVisitorImpl make() throws NegativeDecimalException {
        if (basicRate < 0 || importRate < 0) {
            throw new NegativeDecimalException(format("Some values provided %s or %s as rate are negatives.", basicRate, importRate));
        }
        return new TaxVisitorImpl(basicRate, importRate);
    }
}
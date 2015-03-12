package com.teksystem.salestaxes.units.receipt.calculator;

import com.teksystem.salestaxes.model.tax.TaxVisitorImpl;
import com.teksystem.salestaxes.receipt.calculator.tax.TaxApplierImpl;
import com.teksystem.salestaxes.receipt.calculator.total.TotalsCalculator;
import com.teksystem.salestaxes.receipt.calculator.total.TotalsCalculatorImpl;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestHelper {

    public static TotalsCalculator mockTotalsCalculator(final double salesTaxes, final double taxesItems) {
        final TotalsCalculator totalsCalculator = mock(TotalsCalculatorImpl.class);
        when(totalsCalculator.calculateTotalOfSalesTaxes()).thenReturn(new BigDecimal(salesTaxes));
        when(totalsCalculator.calculateTotalOfTaxedItems()).thenReturn(new BigDecimal(taxesItems));
        return totalsCalculator;
    }

    public static TaxApplierImpl createTaxApplier(final double basicRate, final double importationRate) {
        return new TaxApplierImpl(new TaxVisitorImpl(basicRate, importationRate));
    }
}

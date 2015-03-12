package com.teksystem.salestaxes.units.receipt.calculator;

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
}

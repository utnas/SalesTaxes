package com.teksystem.salestaxes.receipt.calculator.total;

import java.math.BigDecimal;

public interface TotalsCalculator {

    public BigDecimal calculateTotalOfSalesTaxes();

    public BigDecimal calculateTotalOfTaxedItems();
}

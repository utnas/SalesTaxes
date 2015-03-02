package com.teksystem.salestaxes.receipt.calculator.total;

import java.math.BigDecimal;

public interface ITotalsCalculator {

    public BigDecimal calculateTotalOfSalesTaxes();

    public BigDecimal calculateTotalOfTaxedItems();
}

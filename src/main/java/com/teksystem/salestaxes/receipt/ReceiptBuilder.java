package com.teksystem.salestaxes.receipt;

import com.teksystem.salestaxes.receipt.calculator.tax.TaxITaxApplier;
import com.teksystem.salestaxes.receipt.calculator.total.TotalsITotalsCalculator;

import static com.teksystem.salestaxes.utils.CustomDecimalFormatter.format;
import static java.lang.System.getProperty;

public class ReceiptBuilder {
    private final TotalsITotalsCalculator totalsCalculator;
    private final TaxITaxApplier taxApplier;

    public ReceiptBuilder(final TotalsITotalsCalculator totalsCalculator, final TaxITaxApplier taxApplier) {
        this.totalsCalculator = totalsCalculator;
        this.taxApplier = taxApplier;
    }

    public final String displayBill() {
        return (""
                + taxApplier.formatTaxedItems()
                + "Sales Taxes: " + format(totalsCalculator.calculateTotalOfSalesTaxes())
                + getProperty("line.separator")
                + "Total: " + format(totalsCalculator.calculateTotalOfTaxedItems())
        ).trim();
    }
}
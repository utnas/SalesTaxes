package com.teksystem.salestaxes.context;

import static com.teksystem.salestaxes.utils.CustomDecimalFormatter.format;
import static java.lang.System.getProperty;

public class BillBuilder {
    private final TotalsCalculator totalsCalculator;
    private final TaxApplier taxApplier;

    public BillBuilder(final TotalsCalculator totalsCalculator, final TaxApplier taxApplier) {
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
package com.teksystem.salestaxes.receipt;

import com.teksystem.salestaxes.receipt.calculator.tax.TaxApplier;
import com.teksystem.salestaxes.receipt.calculator.total.TotalsCalculator;

import static com.teksystem.salestaxes.utils.CustomDecimalFormatter.format;
import static java.lang.System.getProperty;

public class ReceiptBuilder {
    private final TotalsCalculator totalsCalculator;
    private final TaxApplier taxApplier;

    public ReceiptBuilder(final TotalsCalculator totalsCalculator, final TaxApplier taxApplier) {
        this.totalsCalculator = totalsCalculator;
        this.taxApplier = taxApplier;
    }

    public final String displayBill() {
        final String receipt =
                (taxApplier.formatTaxedItems()
                        + "Sales Taxes: " + format(totalsCalculator.calculateTotalOfSalesTaxes())
                        + getProperty("line.separator")
                        + "Total: " + format(totalsCalculator.calculateTotalOfTaxedItems())
                ).trim();
        taxApplier.clearItemsList();
        return receipt;
    }
}
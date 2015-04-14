package com.teksystem.salestaxes.units.receipt;

import com.teksystem.salestaxes.model.items.NonTaxableItem;
import com.teksystem.salestaxes.receipt.ReceiptBuilder;
import com.teksystem.salestaxes.receipt.calculator.tax.TaxApplier;
import com.teksystem.salestaxes.receipt.calculator.tax.TaxApplierImpl;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import org.junit.Test;

import static com.teksystem.salestaxes.units.model.items.ItemMockHelper.mockItem;
import static com.teksystem.salestaxes.units.receipt.calculator.TestHelper.createTaxApplier;
import static com.teksystem.salestaxes.units.receipt.calculator.TestHelper.mockTotalsCalculator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReceiptBuilderTest {

    @Test
    public void itShouldDisplayZeroAsTaxTotalForNonItem() {
        final TaxApplierImpl taxApplier = createTaxApplier(10.0, 5.0);
        final ReceiptBuilder receiptBuilder = createReceiptBuilder(0.0, 0.0, taxApplier);

        assertThat(receiptBuilder.displayBill(), is("Sales Taxes: 0.00\nTotal: 0.00"));
    }

    @Test
    public void itShouldDisplayZeroAsTaxTotalForNonTaxableItem() {
        final TaxApplierImpl taxApplier = createTaxApplier(10.0, 5.0);
        taxApplier.applyTaxOn(mockItem("chocolate bar", 0.85, NonTaxableItem.class));
        final ReceiptBuilder receiptBuilder = createReceiptBuilder(0.0, 0.85, taxApplier);

        assertThat(receiptBuilder.displayBill(), is("1 chocolate bar: 0.85\nSales Taxes: 0.00\nTotal: 0.85"));
    }

    @Test
    public void itShouldDisplayTaxTotalForTaxableItem() {
        final TaxApplierImpl taxApplier = createTaxApplier(10.0, 5.0);
        taxApplier.applyTaxOn(mockItem("chocolate bar", 0.85, NonTaxableItem.class));
        final ReceiptBuilder receiptBuilder = createReceiptBuilder(0.0, 0.85, taxApplier);

        assertThat(receiptBuilder.displayBill(), is("1 chocolate bar: 0.85\nSales Taxes: 0.00\nTotal: 0.85"));
    }

    private static ReceiptBuilder createReceiptBuilder(final double baseRate, final double importRate, final TaxApplier taxApplier) {
        return new ReceiptBuilder(mockTotalsCalculator(baseRate, importRate), taxApplier);
    }
}
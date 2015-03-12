package com.teksystem.salestaxes.units.receipt;

import com.teksystem.salestaxes.model.items.NonTaxableItem;
import com.teksystem.salestaxes.model.tax.TaxVisitorImpl;
import com.teksystem.salestaxes.receipt.ReceiptBuilder;
import com.teksystem.salestaxes.receipt.calculator.tax.TaxApplierImpl;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import org.junit.Test;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.units.receipt.calculator.TestHelper.mockTotalsCalculator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReceiptBuilderTest {

    @Test
    public void itShouldDisplayZeroAsTaxTotalForNonItem() throws NegativeDecimalException {
        final TaxApplierImpl taxApplier = new TaxApplierImpl(new TaxVisitorImpl(10.0, 5.0));
        final ReceiptBuilder receiptBuilder = new ReceiptBuilder(mockTotalsCalculator(0.0, 0.0), taxApplier);

        assertThat(receiptBuilder.displayBill(), is("Sales Taxes: 0.00\nTotal: 0.00"));
    }

    @Test
    public void itShouldDisplayZeroAsTaxTotalForNonTaxableItem() throws NegativeDecimalException {
        final TaxApplierImpl taxApplier = new TaxApplierImpl(new TaxVisitorImpl(10.0, 5.0));
        taxApplier.applyTaxOn(new NonTaxableItem("chocolate bar", new BigDecimal(0.85)));
        final ReceiptBuilder receiptBuilder = new ReceiptBuilder(mockTotalsCalculator(0.0, 0.85), taxApplier);

        assertThat(receiptBuilder.displayBill(), is("1 chocolate bar: 0.85\nSales Taxes: 0.00\nTotal: 0.85"));
    }

    @Test
    public void itShouldDisplayTaxTotalForTaxableItem() throws NegativeDecimalException {
        final TaxApplierImpl taxApplier = new TaxApplierImpl(new TaxVisitorImpl(10.0, 5.0));
        taxApplier.applyTaxOn(new NonTaxableItem("chocolate bar", new BigDecimal(0.85)));
        final ReceiptBuilder receiptBuilder = new ReceiptBuilder(mockTotalsCalculator(0.0, 0.85), taxApplier);

        assertThat(receiptBuilder.displayBill(), is("1 chocolate bar: 0.85\nSales Taxes: 0.00\nTotal: 0.85"));
    }
}
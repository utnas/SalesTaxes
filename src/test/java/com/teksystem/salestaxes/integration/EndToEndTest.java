package com.teksystem.salestaxes.integration;

import com.teksystem.salestaxes.receipt.ReceiptBuilder;
import com.teksystem.salestaxes.receipt.calculator.tax.TaxITaxApplier;
import com.teksystem.salestaxes.receipt.calculator.total.TotalsITotalsCalculator;
import com.teksystem.salestaxes.model.items.NonTaxableImportedItem;
import com.teksystem.salestaxes.model.items.NonTaxableItem;
import com.teksystem.salestaxes.model.items.TaxableImportedItem;
import com.teksystem.salestaxes.model.items.TaxableItem;
import com.teksystem.salestaxes.model.taxes.TaxVisitorImpl;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import org.junit.Test;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.receipt.calculator.tax.TaxApplierHelper.addItemsTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class EndToEndTest {

    @Test
    public void itShouldComputerTaxesForInputsOne() throws NegativeDecimalException {
        final TaxITaxApplier taxApplier = new TaxITaxApplier(new TaxVisitorImpl(10.0, 5.0));
        addItemsTo(taxApplier,
                new NonTaxableItem("book", new BigDecimal(12.49)),
                new TaxableItem("music CD", new BigDecimal(14.99)),
                new NonTaxableItem("chocolate bar", new BigDecimal(0.85))
        );
        final ReceiptBuilder receiptBuilder = new ReceiptBuilder(new TotalsITotalsCalculator(taxApplier.getTaxedItems()), taxApplier);

        assertThat(receiptBuilder.displayBill(),
                is("1 book: 12.49"
                                + "\n1 music CD: 16.49"
                                + "\n1 chocolate bar: 0.85"
                                + "\nSales Taxes: 1.50"
                                + "\nTotal: 29.83"
                )
        );
    }

    @Test
    public void itShouldComputerTaxesForInputsTwo() throws NegativeDecimalException {
        final TaxITaxApplier taxApplier = new TaxITaxApplier(new TaxVisitorImpl(10.0, 5.0));
        addItemsTo(taxApplier,
                new NonTaxableImportedItem("imported box chocolates", new BigDecimal(10.00)),
                new TaxableImportedItem("imported bottle of perfume", new BigDecimal(47.50))
        );
        final ReceiptBuilder receiptBuilder = new ReceiptBuilder(new TotalsITotalsCalculator(taxApplier.getTaxedItems()), taxApplier);

        assertThat(receiptBuilder.displayBill(),
                is("1 imported box chocolates: 10.50" +
                                "\n1 imported bottle of perfume: 54.63" +
                                "\nSales Taxes: 7.63" +
                                "\nTotal: 65.13"
                )
        );
    }

    @Test
    public void itShouldComputerTaxesForInputsTwoNotEqualToSpecification() throws NegativeDecimalException {
        final TaxITaxApplier taxApplier = new TaxITaxApplier(new TaxVisitorImpl(10.0, 5.0));
        addItemsTo(taxApplier,
                new NonTaxableImportedItem("imported box chocolates", new BigDecimal(10.00)),
                new TaxableImportedItem("imported bottle of perfume", new BigDecimal(47.50))
        );
        final ReceiptBuilder receiptBuilder = new ReceiptBuilder(new TotalsITotalsCalculator(taxApplier.getTaxedItems()), taxApplier);

        assertThat(receiptBuilder.displayBill(),
                not("1 imported box chocolates: 10.50"
                                + "\n1 imported bottle of perfume: 54.65"
                                + "\nSales Taxes: 7.65"
                                + "\nTotal: 65.15"
                )
        );
    }

    @Test
    public void itShouldComputerTaxesForInputsThree() throws NegativeDecimalException {
        final TaxITaxApplier taxApplier = new TaxITaxApplier(new TaxVisitorImpl(10.0, 5.0));
        addItemsTo(taxApplier,
                new TaxableImportedItem("imported bottle of perfume", new BigDecimal(27.99)),
                new TaxableItem("bottle of perfume", new BigDecimal(18.99)),
                new NonTaxableItem("medical of headache pills", new BigDecimal(9.75)),
                new NonTaxableImportedItem("box of imported chocolates", new BigDecimal(11.25))
        );
        final ReceiptBuilder receiptBuilder = new ReceiptBuilder(new TotalsITotalsCalculator(taxApplier.getTaxedItems()), taxApplier);

        assertThat(receiptBuilder.displayBill(),
                is("1 imported bottle of perfume: 32.19"
                                + "\n1 bottle of perfume: 20.89"
                                + "\n1 medical of headache pills: 9.75"
                                + "\n1 box of imported chocolates: 11.81"
                                + "\nSales Taxes: 6.66"
                                + "\nTotal: 74.64"
                )
        );
    }

    @Test
    public void itShouldComputerTaxesForInputsThreeNotEqualsToSpecification() throws NegativeDecimalException {
        final TaxITaxApplier taxApplier = new TaxITaxApplier(new TaxVisitorImpl(10.0, 5.0));
        addItemsTo(taxApplier,
                new TaxableImportedItem("imported bottle of perfume", new BigDecimal(27.99)),
                new TaxableItem("bottle of perfume", new BigDecimal(18.99)),
                new NonTaxableItem("medical of headache pills", new BigDecimal(9.75)),
                new NonTaxableImportedItem("box of imported chocolates", new BigDecimal(11.25))
        );
        final TotalsITotalsCalculator totalsCalculator = new TotalsITotalsCalculator(taxApplier.getTaxedItems());
        final ReceiptBuilder receiptBuilder = new ReceiptBuilder(totalsCalculator, taxApplier);

        assertThat(receiptBuilder.displayBill(),
                not("1 imported bottle of perfume: 32.19"
                                + "\n1 bottle of perfume: 20.89"
                                + "\n1 medical of headache pills: 9.75"
                                + "\n1 box of imported chocolates: 11.85"
                                + "\nSales Taxes: 6.70" + "\nTotal: 74.68"
                )
        );
    }
}
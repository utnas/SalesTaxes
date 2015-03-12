package com.teksystem.salestaxes.starting;

import com.teksystem.salestaxes.model.items.NonTaxableImportedItem;
import com.teksystem.salestaxes.model.items.NonTaxableItem;
import com.teksystem.salestaxes.model.items.TaxableImportedItem;
import com.teksystem.salestaxes.model.items.TaxableItem;
import com.teksystem.salestaxes.model.tax.TaxVisitorImpl;
import com.teksystem.salestaxes.receipt.ReceiptBuilder;
import com.teksystem.salestaxes.receipt.calculator.tax.TaxApplierImpl;
import com.teksystem.salestaxes.receipt.calculator.total.TotalsCalculatorImpl;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.receipt.calculator.tax.TaxApplierHelper.addItemsTo;

public class Launcher {

    public static void main(String[] args) {
        //OUTPUT 1

        final TaxApplierImpl taxApplier = new TaxApplierImpl(new TaxVisitorImpl(10.0, 5.0));
        addItemsTo(taxApplier,
                new NonTaxableItem("book", new BigDecimal(12.49)),
                new TaxableItem("music CD", new BigDecimal(14.99)),
                new NonTaxableItem("chocolate bar", new BigDecimal(0.85))
        );
        TotalsCalculatorImpl totalsCalculator = new TotalsCalculatorImpl(taxApplier.getTaxedItems());
        final ReceiptBuilder receiptBuilder = new ReceiptBuilder(totalsCalculator, taxApplier);

        System.out.println("\nOutput 1:");
        System.out.println(receiptBuilder.displayBill());

        //OUTPUT 2

        addItemsTo(taxApplier,
                new NonTaxableImportedItem("imported box chocolates", new BigDecimal(10.00)),
                new TaxableImportedItem("imported bottle of perfume", new BigDecimal(47.50))
        );

        System.out.println("\nOutput 2:");
        System.out.println(receiptBuilder.displayBill());

        //OUTPUT3

        addItemsTo(taxApplier,
                new TaxableImportedItem("imported bottle of perfume", new BigDecimal(27.99)),
                new TaxableItem("bottle of perfume", new BigDecimal(18.99)),
                new NonTaxableItem("medical of headache pills", new BigDecimal(9.75)),
                new NonTaxableImportedItem("box of imported chocolates", new BigDecimal(11.25))
        );
        System.out.println("\nOutput 3:");
        System.out.println(receiptBuilder.displayBill());
    }
}

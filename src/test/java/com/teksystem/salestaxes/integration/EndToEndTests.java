package com.teksystem.salestaxes.integration;

import com.teksystem.salestaxes.units.context.TaxApplier;
import com.teksystem.salestaxes.units.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;


public class EndToEndTests {

    @Test
    public void itShouldComputerTaxesForInputsOne() {
        final TaxApplier taxApplier = new TaxApplier(10.0, 5.0);
        addItemsTo(taxApplier,
                new NoneTaxableItem("book", 12.49),
                new TaxableItem("music CD", 14.99),
                new NoneTaxableItem("chocolate bar", 0.85)
        );

        assertThat(taxApplier.displayTotal(), is("1 book: 12.49\n1 music CD: 16.49\n1 chocolate bar: 0.85\nSales Taxes: 1.5\n" +
                "Total: 29.83"));
    }

    @Test
    public void itShouldComputerTaxesForInputsTwo() {
        final TaxApplier taxApplier = new TaxApplier(10.0, 5.0);
        addItemsTo(taxApplier,
                new NoneTaxableImportedItem("imported box chocolates", 10.00),
                new TaxableImportedItem("imported bottle of perfume", 47.50)
        );

        assertThat(taxApplier.displayTotal(), is("1 imported box chocolates: 10.5\n1 imported bottle of perfume: 54.63\nSales Taxes: 7.63\nTotal: 65.13"));
    }

    @Test
    public void itShouldComputerTaxesForInputsTwoNotEqualToSpecification() {
        final TaxApplier taxApplier = new TaxApplier(10.0, 5.0);
        addItemsTo(taxApplier,
                new NoneTaxableImportedItem("imported box chocolates", 10.00),
                new TaxableImportedItem("imported bottle of perfume", 47.50)
        );

        assertNotEquals(taxApplier.displayTotal(), is("1 imported box chocolates: 10.50\n1 imported bottle of perfume: 54.65\nSales Taxes: 7.65\nTotal: 65.15"));
    }

    @Test
    public void itShouldComputerTaxesForInputsThree() {
        final TaxApplier taxApplier = new TaxApplier(10.0, 5.0);
        addItemsTo(taxApplier,
                new TaxableImportedItem("imported bottle of perfume", 27.99),
                new TaxableItem("bottle of perfume", 18.99),
                new NoneTaxableItem("medical of headache pills", 9.75),
                new NoneTaxableImportedItem("box of imported chocolates", 11.25)
        );

        assertThat(taxApplier.displayTotal(), is("1 imported bottle of perfume: 32.19\n1 bottle of perfume: 20.89\n1 medical of headache pills: 9.75\n1 box of imported chocolates: 11.81\nSales Taxes: 6.66\nTotal: 74.64"));
    }

    @Test
    public void itShouldComputerTaxesForInputsThreeNotEqualsToSpecification() {
        final TaxApplier taxApplier = new TaxApplier(10.0, 5.0);
        addItemsTo(taxApplier,
                new TaxableImportedItem("imported bottle of perfume", 27.99),
                new TaxableItem("bottle of perfume", 18.99),
                new NoneTaxableItem("medical of headache pills", 9.75),
                new NoneTaxableImportedItem("box of imported chocolates", 11.25)
        );

        assertNotEquals(taxApplier.displayTotal(), is("1 imported bottle of perfume: 32.19\n1 bottle of perfume: 20.89\n1 medical of headache pills: 9.75\n1 box of imported chocolates: 11.85\nSales Taxes: 6.70\nTotal: 74.68"));
    }

    private static Collection addItemsTo(final TaxApplier taxApplier, final Item... items) {
        final Collection result = new ArrayList();
        for (final Item item : items) {
            taxApplier.applyTaxOn(item);
        }
        return Collections.unmodifiableCollection(result);
    }
}
package com.teksystem.salestaxes.visitor;

import com.sun.tools.javac.util.Pair;
import com.teksystem.salestaxes.model.*;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaxVisitorImplTest {

    @Test
    public void itShouldReturnSameItemAfterComputingForTaxTaxableItem() {
        final TaxableItem taxableItem = new TaxableItem("music CD", 14.99);
        final Pair<Item, Double> itemTax = new TaxVisitorImpl(0.10, 0.05).visit(taxableItem);

        assertThat(itemTax.fst.getName(), is(taxableItem.getName()));
        assertThat(itemTax.fst.getPrice(), is(taxableItem.getPrice()));
    }

    @Test
    public void itShouldComputeTaxForTaxableItem() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(0.10, 0.05);

        assertThat(taxVisitor.visit(new TaxableItem("music CD", 14.99)).snd, is(1.499));
    }

    @Test
    public void itShouldComputeTaxForNoneTaxableItem() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(0.10, 0.05);

        assertThat(taxVisitor.visit(new NoneTaxableItem("book", 12.49)).snd, is(0.0));
    }

    @Test
    public void itShouldComputeTaxForTaxableImportedItem() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(0.10, 0.05);

        assertThat(taxVisitor.visit(new TaxableImportedItem("bottle of perfume", 10.00)).snd, is(1.5));
    }


    @Test
    public void itShouldComputeTaxForNoneTaxableImportedItem() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(0.10, 0.05);

        assertThat(taxVisitor.visit(new NoneTaxableImportedItem("box of imported chocolates", 11.25)).snd, is(0.5625));
    }
}
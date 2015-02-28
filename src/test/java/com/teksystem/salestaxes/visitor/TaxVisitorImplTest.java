package com.teksystem.salestaxes.visitor;

import com.sun.tools.javac.util.Pair;
import com.teksystem.salestaxes.model.*;
import com.teksystem.salestaxes.model.NoneTaxableImportedItem;
import com.teksystem.salestaxes.model.NoneTaxableItem;
import com.teksystem.salestaxes.model.TaxableImportedItem;
import com.teksystem.salestaxes.model.TaxableItem;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaxVisitorImplTest {

    @Test
    public void itShouldReturnSameItemAfterComputingForTaxTaxableItem() {
        final TaxableItem taxableItem = new TaxableItem("music CD", 14.99);
        final Pair<Item, Double> itemTax = new TaxVisitorImpl(10.0, 5.0).visit(taxableItem);

        assertThat(itemTax.fst.getName(), is(taxableItem.getName()));
        assertThat(itemTax.fst.getPrice(), is(taxableItem.getPrice()));
    }

    @Test
    public void itShouldComputeTaxForTaxableItem() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);

        assertThat(taxVisitor.visit(new TaxableItem("music CD", 14.99)).snd, is(1.50));
    }

    @Test
    public void itShouldComputeTaxForNoneTaxableItem() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);

        assertThat(taxVisitor.visit(new NoneTaxableItem("book", 12.49)).snd, is(0.0));
    }

    @Test
    public void itShouldComputeTaxForTaxableImportedItem() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);

        assertThat(taxVisitor.visit(new TaxableImportedItem("bottle of perfume", 10.00)).snd, is(1.50));
    }


    @Test
    public void itShouldComputeTaxForNoneTaxableImportedItem() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);

        assertThat(taxVisitor.visit(new NoneTaxableImportedItem("box of imported chocolates", 11.25)).snd, is(0.56));
    }
}
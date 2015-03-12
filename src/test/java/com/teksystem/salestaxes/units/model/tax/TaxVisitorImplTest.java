package com.teksystem.salestaxes.units.model.tax;

import com.teksystem.salestaxes.model.items.*;
import com.teksystem.salestaxes.model.tax.TaxVisitorImpl;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import com.teksystem.salestaxes.utils.Pair;
import org.junit.Test;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.units.model.items.ItemMockHelper.mockItem;
import static com.teksystem.salestaxes.utils.CustomDecimalFormatter.format;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaxVisitorImplTest {

    @Test
    public void itShouldReturnComputedTaxItemAfterComputingForTaxTaxableItem() {
        final Item taxableItem = mockItem("music CD", 14.99, TaxableItem.class);
        final Pair<Item, BigDecimal> itemTax = createTaxVisitorImpl(10.0, 5.0).visit((TaxableItem) taxableItem);

        assertThat(itemTax.first().getName(), is(taxableItem.getName()));
        assertThat(itemTax.first().getPrice(), not(taxableItem.getPrice()));
        assertThat(format(itemTax.first().getPrice()), is("16.49"));
    }


    @Test
    public void itShouldComputeTaxForTaxableItem() {
        final Item taxableItem = mockItem("music CD", 14.99, TaxableItem.class);
        final TaxVisitorImpl taxVisitor = createTaxVisitorImpl(10.0, 5.0);

        assertThat(taxVisitor.visit((TaxableItem) taxableItem).second().doubleValue(), is(1.50));
    }

    @Test
    public void itShouldComputeTaxForNonTaxableItem() {
        final Item nonTaxableItem = mockItem("book", 12.49, NonTaxableItem.class);
        final TaxVisitorImpl taxVisitor = createTaxVisitorImpl(10.0, 5.0);

        assertThat(taxVisitor.visit((NonTaxableItem) nonTaxableItem).second().doubleValue(), is(0.0));
    }

    @Test
    public void itShouldComputeTaxForTaxableImportedItem() {
        final Item taxableImportedItem = mockItem("bottle of perfume", 10.0, TaxableImportedItem.class);
        final TaxVisitorImpl taxVisitor = createTaxVisitorImpl(10.0, 5.0);

        assertThat(taxVisitor.visit((TaxableImportedItem) taxableImportedItem).second().doubleValue(), is(1.50));
    }


    @Test
    public void itShouldComputeTaxForTaxableImportedItemWithNonZeroDecimal() {
        final Item taxableImportedItem = mockItem("bottle of perfume", 47.50, TaxableImportedItem.class);
        final TaxVisitorImpl taxVisitor = createTaxVisitorImpl(10.0, 5.0);

        assertThat(taxVisitor.visit((TaxableImportedItem) taxableImportedItem).second().doubleValue(), is(7.13));
    }

    @Test
    public void itShouldComputeTaxForTaxableImportedItemButNotAsExpectedInSpecification() {
        final Item taxableImportedItem = mockItem("bottle of perfume", 47.50, TaxableImportedItem.class);
        final TaxVisitorImpl taxVisitor = createTaxVisitorImpl(10.0, 5.0);

        assertThat(taxVisitor.visit((TaxableImportedItem) taxableImportedItem).second().doubleValue(), not(7.15));
    }


    @Test
    public void itShouldComputeTaxForNonTaxableImportedItem() {
        final Item item = mockItem("box of imported chocolates", 11.25, NonTaxableImportedItem.class);
        final TaxVisitorImpl taxVisitor = createTaxVisitorImpl(10.0, 5.0);

        assertThat(taxVisitor.visit((NonTaxableImportedItem) item).second().doubleValue(), is(0.563));
    }

    @Test
    public void itShouldComputeTaxForNonTaxableImportedItemNotEqualsToExpectedValueFromSpecification() {
        final Item item = mockItem("box of imported chocolates", 11.25, NonTaxableImportedItem.class);
        final TaxVisitorImpl taxVisitor = createTaxVisitorImpl(10.0, 5.0);

        assertThat(taxVisitor.visit((NonTaxableImportedItem) item).second().doubleValue(), not(0.60));
    }

    private static TaxVisitorImpl createTaxVisitorImpl(final double baseRate, final double importRate) {
        return new TaxVisitorImpl(baseRate, importRate);
    }
}

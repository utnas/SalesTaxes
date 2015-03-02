package com.teksystem.salestaxes.units.model.taxes.visitor;

import com.teksystem.salestaxes.model.items.*;
import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitorImpl;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import com.teksystem.salestaxes.utils.Pair;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.utils.CustomDecimalFormatter.format;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaxVisitorImplTest {

    @Test
    public void itShouldReturnComputedTaxItemAfterComputingForTaxTaxableItem() throws NegativeDecimalException {
        final Item taxableItem = mockItem("music CD", new BigDecimal(14.99), TaxableItem.class);
        final Pair<Item, BigDecimal> itemTax = new TaxVisitorImpl(10.0, 5.0).visit((TaxableItem) taxableItem);

        assertThat(itemTax.first().getName(), is(taxableItem.getName()));
        assertThat(itemTax.first().getPrice(), not(taxableItem.getPrice()));
        assertThat(format(itemTax.first().getPrice()), is("16.49"));
    }


    @Test
    public void itShouldComputeTaxForTaxableItem() throws NegativeDecimalException {
        final Item taxableItem = mockItem("music CD", new BigDecimal(14.99), TaxableItem.class);
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);

        assertThat(taxVisitor.visit((TaxableItem) taxableItem).second().doubleValue(), is(1.50));
    }

    @Test
    public void itShouldComputeTaxForNonTaxableItem() throws NegativeDecimalException {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);

        final NonTaxableItem nonTaxableItem = Mockito.mock(NonTaxableItem.class);
        Mockito.when(nonTaxableItem.getName()).thenReturn("book");
        Mockito.when(nonTaxableItem.getPrice()).thenReturn(new BigDecimal(12.49));

        assertThat(taxVisitor.visit(nonTaxableItem).second().doubleValue(), is(0.0));
    }

    @Test
    public void itShouldComputeTaxForTaxableImportedItem() throws NegativeDecimalException {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);
        final Item taxableImportedItem = mockItem("bottle of perfume", new BigDecimal(10.0), TaxableImportedItem.class);

        assertThat(taxVisitor.visit((TaxableImportedItem) taxableImportedItem).second().doubleValue(), is(1.50));
    }


    @Test
    public void itShouldComputeTaxForTaxableImportedItemWithNonZeroDecimal() throws NegativeDecimalException {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);
        Item taxableImportedItem = mockItem("bottle of perfume", new BigDecimal(47.50), TaxableImportedItem.class);

        assertThat(taxVisitor.visit((TaxableImportedItem) taxableImportedItem).second().doubleValue(), is(7.13));
    }

    @Test
    public void itShouldComputeTaxForTaxableImportedItemButNotAsExpectedInSpecification() throws NegativeDecimalException {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);
        final Item taxableImportedItem = mockItem("bottle of perfume", new BigDecimal(47.50), TaxableImportedItem.class);

        assertThat(taxVisitor.visit((TaxableImportedItem) taxableImportedItem).second().doubleValue(), not(7.15));
    }


    @Test
    public void itShouldComputeTaxForNonTaxableImportedItem() throws NegativeDecimalException {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);
        final Item item = mockItem("box of imported chocolates", new BigDecimal(11.25), NonTaxableImportedItem.class);

        assertThat(taxVisitor.visit((NonTaxableImportedItem) item).second().doubleValue(), is(0.563));
    }

    @Test
    public void itShouldComputeTaxForNonTaxableImportedItemNotEqualsToExpectedValueFromSpecification() throws NegativeDecimalException {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);
        final Item item = mockItem("box of imported chocolates", new BigDecimal(11.25), NonTaxableImportedItem.class);

        assertThat(taxVisitor.visit((NonTaxableImportedItem) item).second().doubleValue(), not(0.60));
    }

    private static Item mockItem(final String itemName, final BigDecimal itemPrice, Class<? extends Item> classToMock) {
        final Item item = Mockito.mock(classToMock);
        Mockito.when(item.getName()).thenReturn(itemName);
        Mockito.when(item.getPrice()).thenReturn(itemPrice);

        return item;
    }
}

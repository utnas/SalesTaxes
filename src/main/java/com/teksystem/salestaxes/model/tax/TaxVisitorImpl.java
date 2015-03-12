package com.teksystem.salestaxes.model.tax;

import com.teksystem.salestaxes.model.items.*;
import com.teksystem.salestaxes.utils.Pair;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.utils.RateCalculator.calculateRate;

public class TaxVisitorImpl implements TaxVisitor<Pair<Item, BigDecimal>> {
    private final Double importationRate;
    private final Double basicRate;

    public TaxVisitorImpl(final Double basicRate, final Double importationRate) {
        this.importationRate = importationRate;
        this.basicRate = basicRate;
    }

    @Override
    public Pair<Item, BigDecimal> visit(final TaxableItem item) {
        final BigDecimal formattedTax = calculateRate(item.getPrice(), basicRate);

        return new Pair<Item, BigDecimal>(new TaxableItem(item.getName(), formattedTax.add(item.getPrice())), formattedTax);
    }

    @Override
    public Pair<Item, BigDecimal> visit(final TaxableImportedItem item) {
        final BigDecimal calculatedRate = calculateRate(item.getPrice(), importationRate).add(calculateRate(item.getPrice(), basicRate));

        return new Pair<Item, BigDecimal>(new TaxableImportedItem(item.getName(), calculatedRate.add(item.getPrice())), calculatedRate);
    }

    @Override
    public Pair<Item, BigDecimal> visit(final NonTaxableItem nonTaxableItem) {
        return new Pair<Item, BigDecimal>(nonTaxableItem, new BigDecimal(0.0));
    }

    @Override
    public Pair<Item, BigDecimal> visit(final NonTaxableImportedItem item) {
        final BigDecimal formattedTax = (calculateRate(item.getPrice(), importationRate));

        return new Pair<Item, BigDecimal>(new NonTaxableImportedItem(item.getName(), formattedTax.add(item.getPrice())), formattedTax);
    }
}

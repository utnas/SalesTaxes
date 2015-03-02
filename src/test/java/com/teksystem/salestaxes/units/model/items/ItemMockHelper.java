package com.teksystem.salestaxes.units.model.items;


import com.teksystem.salestaxes.model.items.Item;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class ItemMockHelper {

    public static Item mockItem(final String itemName, final BigDecimal itemPrice, Class<? extends Item> classToMock) {
        final Item item = Mockito.mock(classToMock);
        Mockito.when(item.getName()).thenReturn(itemName);
        Mockito.when(item.getPrice()).thenReturn(itemPrice);
        return item;
    }
}

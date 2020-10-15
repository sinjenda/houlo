package com.sin.lifesim.Item;


import org.jetbrains.annotations.NotNull;

public class Item {
    public String name;
    public boolean consumable;
    public int price;


    public Item(String name, boolean consumable, int price) {
        this.name = name;
        this.consumable = consumable;
        this.price = price;
    }

    @Override
    public boolean equals(@NotNull Object obj) {
        if (obj instanceof Item) {
            Item item = (Item) obj;
            return name.equals(item.name);
        }
        throw new RuntimeException("not an item");
    }
}

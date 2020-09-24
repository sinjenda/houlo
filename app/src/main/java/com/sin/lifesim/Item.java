package com.sin.lifesim;

import org.jetbrains.annotations.Nullable;

public class Item {
    public String name;
    @Nullable
    public Weapon weapon;
    public boolean consumable;
    public int price;


    public Item(String name, @Nullable Weapon weapon, boolean consumable, int price) {
        this.name = name;
        this.weapon = weapon;
        this.consumable = consumable;
        this.price = price;
    }


}

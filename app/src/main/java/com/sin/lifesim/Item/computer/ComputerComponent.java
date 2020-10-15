package com.sin.lifesim.Item.computer;

import androidx.annotation.IntRange;

import com.sin.lifesim.Item.Item;

public class ComputerComponent extends Item {
    int tier;

    public ComputerComponent(String name, int price, @IntRange(from = 0, to = 10) int tier) {
        super(name, false, price);
        this.tier = tier;
    }
}

package com.sin.lifesim;

import androidx.annotation.IntRange;

import com.sin.lifesim.entity.Effect;

import org.jetbrains.annotations.Nullable;

public class ItemWeapon extends Item {
    public int damage;
    @Nullable
    public Effect effect;
    @Nullable
    public method.onmet.withoutParam weaponEffect;
    @IntRange(from = 0, to = 2000)
    public int durability;

    public ItemWeapon(int damage, @Nullable Effect effect, @IntRange(from = 0, to = 2000) int durability, String name, int price) {
        super(name, false, price);
        this.damage = damage;
        this.effect = effect;
        this.durability = durability;

    }
}

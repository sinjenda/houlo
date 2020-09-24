package com.sin.lifesim;

import androidx.annotation.IntRange;

import com.sin.lifesim.entity.Effect;

import org.jetbrains.annotations.Nullable;

public class Weapon {
    public int damage;
    @Nullable
    public Effect effect;
    @Nullable
    public method.onmet.withoutParam weaponEffect;
    @IntRange(from = 0, to = 2000)
    public int durability;

    public Weapon(int damage, @Nullable Effect effect, int durability) {
        this.damage = damage;
        this.effect = effect;
        this.durability = durability;
    }
}

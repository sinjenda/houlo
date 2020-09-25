package com.sin.lifesim;


import androidx.annotation.IntRange;

import com.sin.lifesim.entity.DataClass;
import com.sin.lifesim.entity.Effect;
import com.sin.lifesim.entity.Entity;
import com.sin.lifesim.entity.EntityRender;

import org.jetbrains.annotations.Nullable;

public class ItemExtended extends Item implements consumable {
    Effect effect;
    @IntRange(from = 0, to = 100)
    int energy;

    public ItemExtended(String name, int price, @Nullable Effect effect, int energy) {
        super(name, true, price);
        this.effect = effect;
        if (energy != -1)
            this.energy = energy;
    }

    // TODO: 24.09.2020 repair this to return new entity 
    @Override
    public void OnConsumeEffect(EntityRender render, Entity toEntity, int length, DataClass data) {
        if (effect != null)
            render.renderEffect(effect, toEntity, length, data);
    }


}

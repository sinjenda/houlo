package com.sin.lifesim.Item;


import android.util.Log;

import androidx.annotation.IntRange;

import com.sin.lifesim.entity.DataClass;
import com.sin.lifesim.entity.Effect;
import com.sin.lifesim.entity.Entity;
import com.sin.lifesim.entity.EntityRender;

import org.jetbrains.annotations.Nullable;

import static android.content.ContentValues.TAG;

public class ItemExtended extends Item implements com.sin.lifesim.Item.consumable {
    Effect effect;
    @IntRange(from = 0, to = 100)
    int energy;

    public ItemExtended(String name, int price, @Nullable Effect effect, int energy) {
        super(name, true, price);
        this.effect = effect;
        if (energy != -1)
            this.energy = energy;
    }


    @Override
    public void OnConsumeEffect(EntityRender render, Entity toEntity, DataClass data) {
        if (effect != null)
            render.renderEffect(effect, toEntity, effect.countRendered, data);
        else Log.i(TAG, "OnConsumeEffect: effect=null; doing nothing");

    }


}

package com.sin.lifesim;


import com.sin.lifesim.entity.DataClass;
import com.sin.lifesim.entity.Effect;
import com.sin.lifesim.entity.Entity;
import com.sin.lifesim.entity.EntityRender;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("ConstantConditions")
public class ItemExtended extends Item implements consumable {
    Effect effect;

    public ItemExtended(String name, @Nullable Weapon weapon, boolean consumable, int price, Effect effect) {
        super(name, weapon, consumable, price);
        this.effect = effect;
    }

    // TODO: 24.09.2020 repair this to return new entity 
    @Override
    public void OnConsumeEffect(EntityRender render, Entity toEntity, int length, DataClass data) {
        render.renderEffect(effect, toEntity, length, data);
    }

    @Override
    public void weaponEffect(@NotNull Weapon weapon) {
        weapon.weaponEffect.methodaB();
    }

    public void weaponEffect(Entity target, EntityRender render, int length, DataClass data) {
        render.renderEffect(effect, target, length, data);
    }
}

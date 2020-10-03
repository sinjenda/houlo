package com.sin.lifesim.Item;

import com.sin.lifesim.entity.DataClass;
import com.sin.lifesim.entity.Entity;
import com.sin.lifesim.entity.EntityRender;

public interface consumable {
    void OnConsumeEffect(EntityRender render, Entity toEntity, DataClass data);


}

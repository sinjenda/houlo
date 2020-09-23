package com.sin.lifesim.entity;

import java.util.ArrayList;

public class EntityRender {
    ArrayList<Entity> renderedEntities;

    public EntityRender() {
        renderedEntities = new ArrayList<>();
    }

    public boolean renderedTest(Entity entity) {
        return renderedEntities.contains(entity);
    }

    public void renderEntity(Entity entity) {
        renderedEntities.add(entity);
    }

    public Effect renderEffect(Effect effect) {

    }
}

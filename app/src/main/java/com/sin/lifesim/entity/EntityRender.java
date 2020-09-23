package com.sin.lifesim.entity;

import androidx.annotation.IntRange;

import java.util.ArrayList;

public class EntityRender {
    ArrayList<Entity> renderedEntities;
    public effectRender render;

    public EntityRender() {
        renderedEntities = new ArrayList<>();
    }

    public boolean renderedTest(Entity entity) {
        return renderedEntities.contains(entity);
    }

    public void renderEntity(Entity entity) {
        renderedEntities.add(entity);
    }

    public Effect renderEffect(Effect effect, Entity toEntity, @IntRange(from = 1, to = 5) int length, DataClass clicked) {
        effect.countRendered = length;
        effect.rendered = true;
        render = new effectRender(effect, toEntity, clicked);
        return effect;
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    public class effectRender extends Thread {
        int count;
        boolean active;
        DataClass data;
        Entity entity;

        @Override
        public void run() {

        }

        effectRender(Effect effect, Entity entity, DataClass data) {
            count = effect.countRendered;
            this.data = data;
            this.entity = entity;
            active = true;
            start();
        }

        public boolean getActive() {
            return active;
        }
    }
}

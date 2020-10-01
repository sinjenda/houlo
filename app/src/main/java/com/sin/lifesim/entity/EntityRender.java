package com.sin.lifesim.entity;

import android.util.Log;

import androidx.annotation.IntRange;

import com.sin.lifesim.Item;

import java.util.ArrayList;

public class EntityRender {
    ArrayList<Entity> renderedEntities;
    public effectRender render;

    public EntityRender() {
        renderedEntities = new ArrayList<>();
    }

    public void renderedTest(Entity entity) {
        if (!renderedEntities.contains(entity)) {
            throw new EntityError("not rendered");
        }
    }

    public void renderHp(int hp, String action, Entity entity) {
        if (action.equals("set")) {
            entity.hp = hp;
        }
        if (action.equals("increase")) {
            entity.hp = entity.hp + hp;
        }
        if (action.equals("decrease")) {
            entity.hp = entity.hp - hp;
        }
    }

    public boolean renderedTestRenderedNotRequired(Entity entity) {
        return renderedEntities.contains(entity);
    }

    public void renderEntity(Entity entity) {
        if (renderedEntities.contains(entity))
            throw new EntityError("already rendered");
        renderedEntities.add(entity);
    }

    public void renderEffect(Effect effect, Entity toEntity, @IntRange(from = 1, to = 5) int length, DataClass clicked) {
        renderedTest(toEntity);
        effect.countRendered = length;
        if (!renderedEntities.contains(toEntity))
            throw new EntityError("");
        effect.rendered = true;
        render = new effectRender(effect, toEntity, clicked);
    }

    public void renderItem(Item item, Entity entity) {
        renderedTest(entity);
        entity.items.add(item);
    }

    public void renderSkill(String skill, Entity toEntity) {
        toEntity.skills.add(skill);
    }

    public void renderRemoveItem(Item item, Entity entity) {
        entity.items.remove(item);
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    public class EntityError extends RuntimeException {
        public EntityError() {
            super();
        }

        public EntityError(String msg) {
            super(msg);
        }

        public EntityError(Throwable cause) {
            super(cause);
        }

        public EntityError(String msg, Throwable cause) {
            super(msg, cause);
        }
    }


    public class effectRender extends Thread {
        int count;
        boolean active;
        DataClass data;
        Entity entity;

        @Override
        public void run() {
            int last = 0;
            int krat = 1;
            while (count != 0) {
                if (data.clicked) {
                    data.clicked = false;
                    count--;
                    for (int i = 0; i != entity.effects.size() - 1; i++) {
                        Effect effect = entity.effects.get(i);
                        effect.effect.effect(entity);
                        Log.i("effect render/run", "applied effect" + effect.name);
                    }
                    render = null;
                }
                last++;
                if (last > 1000) {
                    System.out.println(last * krat);
                    krat++;
                }
            }
        }

        effectRender(Effect effect, Entity entity, DataClass data) {
            renderedTest(entity);
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

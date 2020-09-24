package com.sin.lifesim.entity;

import android.util.Log;

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
        if (!renderedEntities.contains(toEntity))
            throw new EntityError("");
        effect.rendered = true;
        render = new effectRender(effect, toEntity, clicked);
        return effect;
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


    @SuppressWarnings("InnerClassMayBeStatic")
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
                        effect.effect.methodaB();
                        Log.i("effect render/run", "applied effect" + effect.name);
                    }

                }
                last++;
                if (last > 1000) {
                    System.out.println(last * krat);
                    krat++;
                }
            }
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

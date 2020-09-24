package com.sin.lifesim.entity;

import androidx.annotation.IntRange;

import com.sin.lifesim.method;

public class Effect {
    boolean rendered = false;
    boolean closed = false;
    String name;
    @IntRange(from = 1, to = 5)
    int countRendered;
    method.onmet.withoutParam effect;

    public Effect(method.onmet.withoutParam effect, String name) {
        this.effect = effect;
        this.name = name;
    }

    public void close() {
        closed = true;
        rendered = false;
    }
}

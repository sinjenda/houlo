package com.sin.lifesim.entity;

import androidx.annotation.IntRange;

import com.sin.lifesim.interfaces.method;

public class Effect {
    boolean rendered = false;
    boolean closed = false;
    String name;
    @IntRange(from = 1, to = 5)
    public int countRendered;
    method effect;

    public Effect(method effect, String name) {
        this.effect = effect;
        this.name = name;
    }

    public void close() {
        closed = true;
        rendered = false;
    }
}

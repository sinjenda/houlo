package com.sin.lifesim;

import com.sin.lifesim.entity.Entity;

public class Prisoner extends Entity {
    public Prisoner(String name, int inteligence) {
        super(name, inteligence);
    }

    @Override
    protected randomEvents createRandomEvents(MainActivity ctx) {
        return new randomEvents(ctx);
    }
}

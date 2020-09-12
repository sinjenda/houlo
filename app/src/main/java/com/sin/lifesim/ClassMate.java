package com.sin.lifesim;

public class ClassMate implements entity {
    randomEvents events;

    @Override
    public randomEvents createRandomEvents() {
        return events;
    }

    ClassMate(MainActivity m) {
        events = new randomEvents(m);
    }
}

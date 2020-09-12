package com.sin.lifesim;

import java.util.concurrent.ThreadLocalRandom;

public class ClassMate implements entity {
    randomEvents events;
    String name;

    @Override
    public String returnName() {
        return name;
    }

    @Override
    public randomEvents createRandomEvents() {
        return events;
    }

    ClassMate(MainActivity m) {
        events = new randomEvents(m);
        name = Prison.nams[ThreadLocalRandom.current().nextInt(0, Prison.nams.length - 1)];
    }
}

package com.sin.lifesim.entity;

import com.sin.lifesim.MainActivity;
import com.sin.lifesim.Prison;
import com.sin.lifesim.randomEvents;

import java.util.concurrent.ThreadLocalRandom;

public abstract class entity {
    protected String name;


    public void setName() {
        name = Prison.nams[ThreadLocalRandom.current().nextInt(0, Prison.nams.length - 1)];
    }

    protected abstract randomEvents createRandomEvents(MainActivity ctx);


}

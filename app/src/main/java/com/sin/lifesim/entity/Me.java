package com.sin.lifesim.entity;

import com.sin.lifesim.MainActivity;
import com.sin.lifesim.randomEvents;

public class Me extends Entity {

    @Override
    protected randomEvents createRandomEvents(MainActivity ctx) {
        throw new UnsupportedOperationException("class Me cant have random events");
    }

}

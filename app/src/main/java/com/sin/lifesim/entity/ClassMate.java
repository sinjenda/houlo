package com.sin.lifesim.entity;

import com.sin.lifesim.MainActivity;
import com.sin.lifesim.randomEvents;

import org.jetbrains.annotations.Nullable;

public class ClassMate extends entity {
    randomEvents events;
    private String[] goodSubjects;

    @Override
    public randomEvents createRandomEvents(MainActivity ctx) {
        events = new randomEvents(ctx);
        return events;
    }

    private void createGoodSubjects(@Nullable String[] subjects) {
        if (subjects != null) {
            goodSubjects = subjects;
        } else
            goodSubjects = createSubjects();
    }

    private String[] createSubjects() {

    }

    ClassMate(MainActivity m) {
        setName();
    }
}

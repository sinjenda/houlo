package com.sin.lifesim.entity;

import androidx.annotation.IntRange;

import com.sin.lifesim.Item;
import com.sin.lifesim.MainActivity;
import com.sin.lifesim.Prison;
import com.sin.lifesim.randomEvents;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Entity {
    public String name;
    public ArrayList<Effect> effects;
    ArrayList<String> skills;
    public ArrayList<Item> items;
    @IntRange(from = 0, to = 100)
    public int hp = 100;
    @IntRange(from = 0, to = 100)
    public int inteligence;

    public void setInteligence(int inteligence) {
        if (inteligence != -1) {
            this.inteligence = inteligence;
        } else
            this.inteligence = ThreadLocalRandom.current().nextInt(0, 100);
    }

    public void setName(@Nullable String name) {
        if (name != null) {
            this.name = name;
        } else
            this.name = Prison.nams[ThreadLocalRandom.current().nextInt(0, Prison.nams.length - 1)];
    }

    protected abstract randomEvents createRandomEvents(MainActivity ctx);

    public Entity(String name, int inteligence) {
        this.name = name;
        this.inteligence = inteligence;
        skills = new ArrayList<>();
        effects = new ArrayList<>();
        items = new ArrayList<>();
    }
}

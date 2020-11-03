package com.sin.lifesim.crime;

import androidx.annotation.IntRange;

import com.sin.lifesim.entity.Entity;

import org.jetbrains.annotations.NotNull;

public abstract class Crime {
    int place;
    String name;
    @IntRange(from = 0, to = 100)
    int risk;
    int myStage;
    @IntRange(from = 0, to = 50)
    int hardness;

    public static class stages {
        public static final int low = 1;
        public static final int medium = 2;
        public static final int university = 3;
        public static final int business = 4;
    }

    public Crime(String name, int risk, int place) {
        this.name = name;
        this.risk = risk;
        this.place = place;
        this.risk = this.risk + place;
    }

    abstract void onSuccess();

    public void cached(Entity who) {

    }

    protected static class places {
        public static final int HOME = 10;
        public static final int STREET = 50;
        public static final int SHOP = 30;
    }

    @Override
    public boolean equals(@NotNull Object obj) {
        if (obj instanceof Crime) {
            return ((Crime) obj).name.equals(name);
        }
        return false;
    }
}

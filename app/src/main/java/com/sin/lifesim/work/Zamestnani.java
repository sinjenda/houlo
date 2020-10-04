package com.sin.lifesim.work;

import android.content.Context;

import com.sin.lifesim.database.databaseZamestnani;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Zamestnani {
    public static final String[] posts = {"jr", "sr", "manager", "boss"};
    public String name;
    int money;
    public saver saver;
    int requiredKnowledge;
    String type;
    @Nullable
    String school;

    public Zamestnani(String name, int money, int requiredKnowledge, String type, @Nullable String school) {
        this.name = name;
        this.money = money;
        this.requiredKnowledge = requiredKnowledge;
        this.type = type;
        this.school = school;
        saver saver = new saver();
    }

    public class saver {
        databaseZamestnani databaseZamestnani;
        ArrayList<Object> save = new ArrayList<>();

        saver() {

        }

        public void save(Context ctx) {
            databaseZamestnani = new databaseZamestnani(ctx);
            databaseZamestnani.insert(new String[]{name, String.valueOf(money), String.valueOf(requiredKnowledge), school});
        }

    }
}

package com.sin.lifesim.crime;

import com.sin.lifesim.Item.computer.Computer;
import com.sin.lifesim.Item.computer.ComputerComponent;
import com.sin.lifesim.MainActivity;
import com.sin.lifesim.Window;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class hacker extends Crime {
    int experience;
    Computer computer;

    public hacker(MainActivity m) {
        super("hack", 30, places.HOME);
        int exp = 0;
        for (String s : m.schools) {
            switch (s) {
                case "programmer":
                    exp = exp + 20;
                case "engineer":
                    exp = exp + 10;
            }
        }
        for (String s : m.courses) {
            switch (s) {
                case "programmer":
                    exp = exp + 20;
                case "hacker":
                    exp = exp + 50;
            }
        }
    }

    public void goHack(Computer target, MainActivity ctx) {
        int risk = super.risk;
        // TODO: 23.10.2020 continue this
        risk = risk - experience;
        boolean net = false;
        for (ComputerComponent component : computer.usedComponents) {
            switch (component.name) {
                case "cpu":
                    risk = risk - component.tier * 2;
                case "memory":
                    if (component.tier < ThreadLocalRandom.current().nextInt(0, 3)) {
                        if (ThreadLocalRandom.current().nextInt(0, 2) > 1) {
                            cached(target.owner);
                        }
                    }

            }
            if (component.name.equals("net")) {
                net = true;
            }
        }
        if (!net) {
            new Window(ctx).informationDialog("you don't have a network card");
            return;
        }
        if (ThreadLocalRandom.current().nextInt(0, 30) > risk) {
            onSuccess(new Object[]{target.data, ctx});
        } else {
            cached(target.owner);
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    void onSuccess(Object[] data) {
        ArrayList<String> datas = (ArrayList<String>) data[0];
        int money = 0;
        for (String s : datas) {
            switch (s) {
                case "card":
                    money = money + 50;
                case "personalInformation":
                    money = money + 20;
                case "pin":
                    money = money + 100;
            }
        }
        ((MainActivity) data[1]).money = ((MainActivity) data[1]).money + money;
    }
}

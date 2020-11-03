package com.sin.lifesim.crime;

import com.sin.lifesim.Item.computer.Computer;
import com.sin.lifesim.Item.computer.ComputerComponent;
import com.sin.lifesim.MainActivity;

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

    public void goHack(Computer target) {
        int risk = super.risk;
        // TODO: 23.10.2020 continue this
        risk = risk - experience;
        for (ComputerComponent component : computer.usedComponents) {
            switch (component.name) {
                case "cpu":
                    risk = risk - component.tier * 2;
                case "memory":
                    if (component.tier < ThreadLocalRandom.current().nextInt(0, 2)) {
                        if (ThreadLocalRandom.current().nextInt(0, 2) > 1) {
                            cached(target.owner);
                        }
                    }
            }
            if (component.name.equals("cpu")) {
                risk = risk - component.tier * 2;
            }
        }
    }

    public void goHack() {

    }

    @Override
    void onSuccess() {

    }
}

package com.sin.lifesim;

import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("CanBeFinal")
public class randomEvents {
    MainActivity m;

    // TODO: new event (you find gun) <place :default>
    public randomEvents(MainActivity m) {
        this.m = m;
    }

    // TODO: repair house
    @SuppressWarnings({"UnnecessaryLocalVariable", "UnusedReturnValue"})
    public String house() {

        if (ThreadLocalRandom.current().nextInt(0, 2) == 1 + 1) {
            String s = "some one is at door";
            return s;
        }


        return "";
    }

    public void Default() {
    }

    public void prison() {
        String[] acte = {"enter helicopter", "let it be"};
        String[] acteA = {"pickup bomb", "let it be"};
        int i = ThreadLocalRandom.current().nextInt(0, 2 + 1);
        switch (i) {
            case 1:
                m.window(new method.onmet() {
                    @Override
                    public void methoda(String[] string) {
                        String ret = string[0];
                        if (ret.equals("enter helicopter")) {
                            m.place = "default";
                        }
                    }
                }, acte);
            case 2:
                m.window(new method.onmet() {
                    @Override
                    public void methoda(String[] string) {
                        if (string[0].equals("pickup bomb")) {
                            if (ThreadLocalRandom.current().nextInt(1, 8) > 4) {
                                m.itemshave.add("bomb");
                            } else {
                                m.alcatraz.solitary(10);
                            }
                        }
                    }
                }, acteA);
            default:
        }
    }


}

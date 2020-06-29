package com.sin.lifesim;

import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings({"CanBeFinal", "ConstantConditions"})
public class randomEvents {
    MainActivity m;
    Window w = new Window(m);
    // TODO: new event (you find gun) <place :default>
    public randomEvents(MainActivity m) {
        this.m = m;
    }

    // TODO: repair house
    @SuppressWarnings({"UnusedReturnValue"})
    public void house() {

        if (ThreadLocalRandom.current().nextInt(0, 2) == 1 + 1) {
            String s = "some one is at door";
            w.windowTwoButtons(new method.onmet.withoutParam() {
                @Override
                public void methodaB() {
                    int i = ThreadLocalRandom.current().nextInt(2, 8);
                    if (i > 6) {
                        w.informationDialog("he is running with you");
                        // TODO: add handling now
                    } else {
                        // TODO: add other things
                    }
                }
            }, new method.onmet.withoutParam() {
                @Override
                public void methodaB() {

                }
            }, s + " :open or not");
        }
    }

    public void Default() {

    }

    public void prison() {
        String[] acte = {"enter helicopter", "let it be"};
        String[] acteA = {"pickup bomb", "let it be"};
        int i = ThreadLocalRandom.current().nextInt(0, 2 + 1);
        switch (i) {
            case 1:
                w.windowItems(new method.onmet() {
                    @Override
                    public void methoda(String[] string) {
                        String ret = string[0];
                        if (ret.equals("enter helicopter")) {
                            m.place = "default";
                        }
                    }
                }, acte);
            case 2:
                w.windowItems(new method.onmet() {
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

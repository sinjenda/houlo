package com.sin.lifesim;

import com.sin.lifesim.trade.Trade;
import com.sin.lifesim.trade.Trader;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings({"CanBeFinal", "ConstantConditions"})
public class randomEvents {
    MainActivity m;
    Window w;

    public randomEvents(MainActivity m) {
        this.m = m;
        w = new Window(m);
    }

    @SuppressWarnings({"UnusedReturnValue"})
    public void house() {

        if (ThreadLocalRandom.current().nextInt(0, 4) > 2) {
            final String s = "some one is at door";
            w.windowTwoButtons(new method.onmet.withoutParam() {
                @Override
                public void methodaB() {

                    int i = ThreadLocalRandom.current().nextInt(2, 8);
                    if (i > 6) {
                        w.informationDialog("he is running with you");
                        if (ThreadLocalRandom.current().nextInt(0, 8) > 6) {
                            w.informationDialog("you are tied up in his house");
                            w.informationDialog("he wants ransom from your family");
                            if (ThreadLocalRandom.current().nextInt(0, 8) > 2) {
                                w.informationDialog("your family paid ransom");
                            } else {
                                w.informationDialog("your family don't paid ransom");
                                m.place = "enemy'sHouse";
                            }
                        } else {
                            w.informationDialog("he grabs your pocket and run");
                            m.money = 0;
                        }

                    } else {
                        if (ThreadLocalRandom.current().nextInt(0, 8) > 6) {
                            Scanner scnr = new Scanner(Trade.items.ITEMS_TRADE);
                            int test = 0;
                            while (scnr.hasNext()) {
                                scnr.next();
                                test++;
                            }
                            scnr = new Scanner(Trade.items.ITEMS_TRADE);
                            for (int i1 = ThreadLocalRandom.current().nextInt(0, test - 1); i != 0; i--) {
                                scnr.next();
                            }
                            String s1 = scnr.next();
                            w.informationDialog("he gives " + s1);
                            m.itemshave.add(s1);
                        } else {
                            w.informationDialog("he wants to trade with you");
                            new Trade(m).trade(new Trader(new Trade.items(Trade.items.NEUTRAL), null, null));

                        }
                    }
                }
            }, null, s + " :open or not");
        }
    }




    public void Default() {
        if (ThreadLocalRandom.current().nextInt(0, 8) > 6) {
            w.informationDialog("you find a gun");
            w.windowTwoButtons(new method.onmet.withoutParam() {
                @Override
                public void methodaB() {
                    m.itemshave.add("gun");
                }
            }, null, m.getString(R.string.pick));
        }
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



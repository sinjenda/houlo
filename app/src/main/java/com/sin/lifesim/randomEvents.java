package com.sin.lifesim;

import com.sin.lifesim.Item.Item;
import com.sin.lifesim.Item.ItemWeapon;
import com.sin.lifesim.entity.Effect;
import com.sin.lifesim.entity.Entity;
import com.sin.lifesim.interfaces.method;
import com.sin.lifesim.trade.Trade;
import com.sin.lifesim.trade.Trader;

import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings({"CanBeFinal"})
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
                            Item s1 = Trade.items.ITEMS_TRADE[ThreadLocalRandom.current().nextInt(0, Trade.items.ITEMS_TRADE.length)];
                            w.informationDialog("he gives " + s1.name);
                            m.me.items.add(s1);
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
            w.windowTwoButtons(new method.onmet.withoutParam() {
                @Override
                public void methodaB() {
                    m.me.items.add(m.itsellPrepare[2]);
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
                                m.me.items.add(new ItemWeapon(80, new Effect(new method() {
                                    @Override
                                    public void effect(Entity entity) {
                                    }
                                }, "blow"), 1, "bomb", 150));
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



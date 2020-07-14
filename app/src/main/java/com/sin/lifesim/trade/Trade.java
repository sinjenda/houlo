package com.sin.lifesim.trade;

import com.sin.lifesim.Krmic;
import com.sin.lifesim.MainActivity;
import com.sin.lifesim.Window;
import com.sin.lifesim.method;

import java.util.ArrayList;

public class Trade {
    MainActivity m;
    Window w;

    public static class items {
        public static int HOSTILE = -1;
        public static int NEUTRAL = 0;
        public static int FRIEND = 1;
        public static String ITEMS_TRADE = "apple banana pancakes knife axe medicine";
        int relation;

        public items(int relation) {
            this.relation = relation;
        }
    }

    public Trade(MainActivity m) {
        this.m = m;
        w = new Window(m);
    }

    public void trade(final Trader trader) {
        switch (trader.relation) {
            case 0:
                ArrayList<String> it = new ArrayList<>();
                for (int i = trader.items.length; i != 0; i--) {
                    it = new ArrayList<>();
                    it.add(trader.items[i] + " " + trader.prices[i]);
                }
                final String[] items;
                items = Krmic.poleConverter(Krmic.polepull(it));
                w.multiChoiceWindow(new method.onmet() {
                    @Override
                    public void methoda(String[] string) {
                        Krmic k = new Krmic();
                        ArrayList<Integer> integers = k.polePut(trader.prices);
                        int biggestNumber = 0;

                        for (int i1 : integers) {
                            if (i1 > biggestNumber) {
                                biggestNumber = i1;
                            }
                        }

                        ArrayList<String> strings = new ArrayList<>();
                        for (String s : string) {
                            for (int i2 = 0; i2 != biggestNumber; i2++) {
                                s = s.replaceAll(" ", "");
                                strings.add(s.replace(String.valueOf(i2), ""));
                            }
                        }
                        int i;
                        for (String s:strings){

                        }
                        if (m.money < i) {
                            trade(trader);
                        } else {
                            m.money = m.money - i;
                            for (String item : trader.items) {
                                m.itemshave.add();
                            }
                        }
                    }
                }, items);
            case 1:
            case -1:
        }
    }
}

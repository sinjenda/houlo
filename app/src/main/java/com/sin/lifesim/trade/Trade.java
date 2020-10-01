package com.sin.lifesim.trade;

import com.sin.lifesim.Item;
import com.sin.lifesim.ItemExtended;
import com.sin.lifesim.ItemWeapon;
import com.sin.lifesim.Krmic;
import com.sin.lifesim.MainActivity;
import com.sin.lifesim.R;
import com.sin.lifesim.Window;
import com.sin.lifesim.entity.Effect;
import com.sin.lifesim.entity.Entity;
import com.sin.lifesim.method;

import java.util.ArrayList;

@SuppressWarnings("ConstantConditions")
public class Trade {
    final MainActivity m;
    final Window w;

    public static class items {
        public static int HOSTILE = -1;
        public static final int NEUTRAL = 0;
        public static int FRIEND = 1;
        public static final Item[] ITEMS_TRADE = {new Item("trash", false, 0), new ItemExtended("banana", 10, null, 12), new ItemWeapon(150, new Effect(new method() {
            @Override
            public void effect(Entity entity) {
                entity.hp = entity.hp - 10;
            }
        }, "bleeding"), 30, "knife", 60)};
        final int relation;

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
                for (int i = trader.items.size() - 1; i != 0; i--) {
                    it.add(trader.items.get(i) + " " + trader.prices[i]);
                }
                final String[] items;
                items = Krmic.poleConverter(Krmic.polepull(it));
                w.multiChoiceWindow(new method.onmet() {
                    @Override
                    public void methoda(String[] itemNames) {
                        Krmic k = new Krmic();
                        ArrayList<Integer> integers = Krmic.polePut(trader.prices);
                        int biggestNumber = 0;

                        for (int i1 : integers) {
                            if (i1 > biggestNumber) {
                                biggestNumber = i1;
                            }
                        }

                        ArrayList<Item> items1 = new ArrayList<>();
                        for (String s : itemNames) {
                            for (Item i : m.itemssell) {
                                if (i.name.equals(s)) {
                                    items1.add(i);
                                }
                            }
                        }
                        int i = 0;
                        for (Item s : items1) {
                            ArrayList<Item> items = trader.items;
                            int i1 = items.indexOf(s);
                            i = i + trader.prices[i1];
                        }
                        if (m.money < i) {
                            w.informationDialog(m.getString(R.string.err2));
                            trade(trader);
                        } else {
                            m.money = m.money - i;
                            m.editor.putInt("money", m.money - i);
                            m.me.items.addAll(items1);
                        }
                    }
                }, items);
            case 1:
            case -1:
        }
    }
}

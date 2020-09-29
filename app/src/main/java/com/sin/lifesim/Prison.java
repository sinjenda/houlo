package com.sin.lifesim;


import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static android.content.ContentValues.TAG;


@SuppressWarnings({"AccessStaticViaInstance"})
public class Prison {
    public static String[] nams = {"marek", "honza", "noob", "luis", "peter", "rychard", "arnocht", "emil", "alex", "george", "john", "milan", "pavel", "roman"};
    private Item used;
    private final MainActivity m;
    ArrayList<Prisoner> vezni = new ArrayList<>();
    private final Krmic krmic = new Krmic();
    private int sentence;
    Window window;

    public void prison(int sentence1) {
        sentence = sentence1;
    }

    public void solitary(int time) {
        m.out.setText(R.string.note6);
        // TODO: 28.09.2020 add remove item to entity render
        m.me.items.clear();
        if (ThreadLocalRandom.current().nextInt(1, 24 + 1) > time) {
            m.out.setText(R.string.note7);
            m.dat3 = "death";
        }
    }

    public void prison() {
        if (sentence == 0) {

            m.place = "dafault";
        }
    }

    public void kill() {
        ArrayList<Item> itemshave = m.me.items;
        final Window w = new Window(m);
        if (m.skills.contains("killer")) {
            int chance = 5;
        }
        ArrayList<String> weapons = new ArrayList<>();
        for (Item item : m.itemshave) {
            weapons.add(item.name);
        }
        w.windowItems(new method.onmet() {
            @Override
            public void methoda(String[] string) {
                for (final Item item : m.itemshave) {
                    if (item.name.equals(string[0])) {
                        used = item;
                        ArrayList<String> names = new ArrayList<>();
                        for (Prisoner prisoner : vezni) {
                            names.add(prisoner.name);
                        }
                        final String[] jmena = krmic.poleConverter(krmic.polepull(names));
                        window.windowItems(new method.onmet() {
                            @Override
                            public void methoda(String[] string) {
                                ItemWeapon weapon = (ItemWeapon) item;
                                for (Prisoner prisoner : vezni) {
                                    if (prisoner.name.equals(string[0])) {
                                        for (int i = 0; true; i++) {
                                            m.render.renderHp(weapon.damage, "decrease", prisoner);
                                            m.render.renderHp(ThreadLocalRandom.current().nextInt(1, 25), "decrease", m.me);
                                            if (prisoner.hp < 1) {
                                                w.informationDialog("you killed him");
                                                m.money = m.money + ThreadLocalRandom.current().nextInt(15, 100);
                                                Log.i(TAG, "killProcces ended after " + i + "rounds");
                                                break;
                                            }
                                            if (m.me.hp < 1) {
                                                int i1 = ThreadLocalRandom.current().nextInt(15, 100);
                                                m.money = m.money - i1;
                                                w.informationDialog("he killed you you lose " + i1 + "coins");
                                                Log.i(TAG, "killProcces ended after " + i + "rounds");
                                                break;
                                            }
                                        }
                                    }
                                }

                            }
                        }, jmena);
                        break;
                    }
                }
            }
        }, Krmic.poleConverter(Krmic.polepull(weapons)));


    }


    public Prison(MainActivity m) {
        this.m = m;
        window = new Window(m);
    }

}
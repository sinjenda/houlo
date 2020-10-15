package com.sin.lifesim.school.schools;

import android.util.Log;

import com.sin.lifesim.Item.Item;
import com.sin.lifesim.Item.ItemWeapon;
import com.sin.lifesim.Krmic;
import com.sin.lifesim.MainActivity;
import com.sin.lifesim.Window;
import com.sin.lifesim.entity.Me;
import com.sin.lifesim.interfaces.method;
import com.sin.lifesim.school.School;
import com.sin.lifesim.school.entity.ClassMate;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static android.content.ContentValues.TAG;

public class KomensSchool extends School {
    static String[] events = {"bully", "someone want's money from you"};

    public KomensSchool(MainActivity m) {
        super(m.render);
        super.name = "komensSchool";
        types.choose = types.lowSchool;
        subjects = new int[]{School.subjects.math, School.subjects.history, School.subjects.nature, School.subjects.languages.english};
        super.StudyTime = 45;
        maxStudents = 40;
    }

    @Override
    public int study(final MainActivity m) {
        int i = super.study(m);
        if (i > events.length - 1) {
            i = events.length - 1;
        }
        final Window w = new Window(m);
        for (int i1 = i; i1 != 0; i1--) {
            switch (event()) {
                case "bully":
                    bully(w, m);
                case "someone want's  money from you":
                    someoneWantMoney(w, m, classMates.get(ThreadLocalRandom.current().nextInt(0, classMates.size() - 1)));
            }
        }

        return 0;
    }

    private String event() {
        return events[ThreadLocalRandom.current().nextInt(0, events.length - 1)];
    }

    public boolean beatClassMate(ArrayList<ClassMate> enemies, int yourWeaponDamage, Me me) {
        for (int i = 0; true; i++) {
            for (ClassMate mate : enemies) {
                if (mate.items.size() != 0) {
                    for (Item item : mate.items) {
                        try {
                            ItemWeapon weapon = (ItemWeapon) item;
                            me.hp = me.hp - weapon.damage;
                            break;
                        } catch (ClassCastException ignore) {
                        }

                    }
                } else {
                    me.hp = me.hp - 1;
                }
                if (mate.hp < 1) {
                    enemies.remove(mate);
                }
            }
            if (me.hp < 1) {
                me.hp = 30;
                Log.i(TAG, "beatClassMate: beating class mate was " + i + " long");
                return false;
            }
            if (enemies.size() == 0) {
                Log.i(TAG, "beatClassMate: beating class mate was " + i + " long");
                return true;
            }
        }

    }

    private void bully(final Window w, final MainActivity m) {
        w.informationDialog("they bully you and want 150 crowns");
        w.windowItems(new method.onmet() {
            @Override
            public void methoda(String[] string) {
                switch (string[0]) {
                    case "try to beat them":
                        w.informationDialog("choose weapon");
                        final ArrayList<String> items = new ArrayList<>();
                        for (Item item : m.me.items) {
                            items.add(item.name);
                        }
                        final ArrayList<ClassMate> enemies = new ArrayList<>();
                        for (int i = ThreadLocalRandom.current().nextInt(1, 3); i != 0; i--) {
                            enemies.add(classMates.get(ThreadLocalRandom.current().nextInt(0, classMates.size() - 1)));
                        }
                        if (m.me.items.size() != 0) {
                            w.windowItems(new method.onmet() {
                                @Override
                                public void methoda(String[] string) {
                                    ItemWeapon item = null;
                                    for (Item item1 : m.me.items) {
                                        if (item1.name.equals(string[0])) {
                                            item = (ItemWeapon) item1;
                                        }
                                    }
                                    if (beatClassMate(enemies, item.damage, m.me)) {
                                        for (ClassMate mate : enemies) {
                                            mate.relationShip = mate.relationShip + 10;
                                        }
                                    } else {
                                        m.money = m.money - 300;
                                        for (ClassMate mate : enemies) {
                                            mate.relationShip = mate.relationShip - 10;
                                        }
                                    }
                                }
                            }, Krmic.poleConverter(Krmic.polepull(items)));
                        } else {
                            if (beatClassMate(enemies, 1, m.me)) {
                                for (ClassMate mate : enemies) {
                                    mate.relationShip = mate.relationShip + 10;
                                }
                            } else {
                                m.money = m.money - 300;
                                for (ClassMate mate : enemies) {
                                    mate.relationShip = mate.relationShip - 10;
                                }
                            }
                        }
                    case "pay 150 crowns":
                        m.money = m.money - 150;
                    case "try to escape":
                        if (ThreadLocalRandom.current().nextInt(0, 1) == 1) {
                            m.money = m.money - 300;
                        } else w.informationDialog("you successfully escaped");
                }
            }
        }, new String[]{"try to beat them", "pay 150 crowns", "try to escape"});
    }

    private void someoneWantMoney(Window w, final MainActivity m, final ClassMate classMate) {
        w.windowTwoButtons(new method.onmet.withoutParam() {
            @Override
            public void methodaB() {
                classMate.relationShip = classMate.relationShip + 20;
                m.money = m.money - 150;
            }
        }, null, "someone want's 150crowns from you");
    }
}
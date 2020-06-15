package com.sin.lifesim;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Prison {
    private String used = "";
    private int chance = 0;
    private ArrayList<String> itemshave;
    private MainActivity m;
    ArrayList<String> prisonNames = new ArrayList<String>();
    HashMap<String, HashMap<String, Integer>> vezni = new HashMap<>();
    private Krmic krmic = new Krmic();
    private int sentence;

    public void prison(int sentence1) {
        String[] nams = {"marek", "honza", "noob", "luis", "peter", "rychard", "arnocht", "emil", "alex", "george", "john", "milan", "pavel", "roman"};
        prisonNames = krmic.polePut(nams);
        prisonNames = krmic.prisonersChoose(prisonNames);
        sentence = sentence1;
        vezni = krmic.createPrisoners(prisonNames);
        m.nms = prisonNames;
    }

    public void solitary(int time) {
        m.out.setText(R.string.note6);
        m.itemshave.clear();
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

    @SuppressWarnings("ConstantConditions")
    public void kill(String weapon) {
        itemshave = m.itemshave;


        if (m.skills.contains("killer")) {
            chance = 5;
        }
        //noinspection StatementWithEmptyBody
        if (itemshave.isEmpty()) {

        } else {

            String[] weapons = {"knife", "gun", "baton", "screwdriver"};
            int[] ints = {3, 10, 5, 1};
            if (weapons.length != ints.length) {
                throw new UnsupportedOperationException("length of weapons does not equals length of ints");
            }
            HashMap<String, Integer> wEapons;
            wEapons = krmic.nakrmDataHash(krmic.polePut(weapons), krmic.polePut(ints));
            int i = 0;
            while (i != weapon.length()) {
                if (itemshave.contains(weapon)) {
                    if (wEapons.containsKey(weapon)) {
                        chance = chance + wEapons.get(weapon);
                        used = weapon;
                    }
                } else {
                    i++;
                }
            }


        }
        final String[] jmena = krmic.poleConverter(krmic.polepull(prisonNames));
        m.window(new method.onmet() {
            @Override
            public void methoda(String[] string) {
                fkill(string[0]);
            }
        }, jmena);

    }


    public void fkill(String prisoner) {
        Object o = vezni.get(prisoner);
        String s = String.valueOf(o);

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(s);
        for (int i = 0; ; i++) {
            if (matcher.find()) {
                s = i + ": " + matcher.group();
                System.out.println(i + ": " + matcher.group());
            } else
                break;

        }


        if (chance + ThreadLocalRandom.current().nextInt(1, 120 + 1) > Integer.parseInt(s)) {
            if (!used.equals("")) {
                if (ThreadLocalRandom.current().nextInt(1, 8 + 1) < 4) {
                    itemshave.remove(used);

                }

            }
            solitary(15);


        } else {
            m.out.setText(R.string.note11);
            m.dat3 = "death";
        }
    }

    public Prison(MainActivity m) {
        this.m = m;
    }

}
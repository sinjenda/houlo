package com.sin.lifesim;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("unused")
public class Krmic implements Serializable {
    private int hp;
    private int energy;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }


    public int[] poleConverter(String[] o) {
        ArrayList<Integer> i = new ArrayList<Integer>();
        ArrayList<String> s = new ArrayList<String>();
        for (Object o1 : o) {
            s.add(String.valueOf(o1));
        }
        for (String s2 : s) {
            i.add(Integer.parseInt(s2));
        }

        int[] ret = new int[i.size()];
        for (int x = 0; x < ret.length; x++) {
            ret[x] = i.get(x);
        }
        return ret;
    }

    public String[] poleConverter(Object[] o) {
        ArrayList<String> s = new ArrayList<String>();
        for (Object o1 : o) {
            s.add(String.valueOf(o1));
        }
        String[] s2 = new String[s.size()];
        s2 = s.toArray(s2);
        return s2;
    }

    @SuppressWarnings({"UseBulkOperation", "ManualArrayToCollectionCopy"})
    public ArrayList<String> polePut(String[] pole) {
        ArrayList<String> nams = new ArrayList<String>();

        for (String s : pole) {
            nams.add(s);
        }
        return nams;
    }

    public ArrayList<Integer> polePut(int[] pole) {
        ArrayList<Integer> nams = new ArrayList<Integer>();
        for (int s : pole) {
            nams.add(s);
        }
        return nams;
    }

    @SuppressWarnings({"ManualArrayToCollectionCopy", "UseBulkOperation"})
    public ArrayList<Object> polePut(Object[] pole) {
        ArrayList<Object> nams = new ArrayList<Object>();
        for (Object o : pole) {
            nams.add(o);
        }
        return nams;
    }

    public Object[] polepull(@SuppressWarnings("rawtypes") ArrayList plou) {

        Object[] stockArr = new Object[plou.size()];
        stockArr = plou.toArray(stockArr);
        return stockArr;


    }

    public HashMap<String, HashMap<String, Integer>> createPrisoners(ArrayList<String> names) {


        HashMap<String, Integer> skili = new HashMap<String, Integer>();
        HashMap<String, HashMap<String, Integer>> uloziste = new HashMap<String, HashMap<String, Integer>>();
        for (String s : names) {


            hp = (ThreadLocalRandom.current().nextInt(1, 100 + 1));
            energy = (ThreadLocalRandom.current().nextInt(1, 100 + 1));
            skili.put("hp", hp);
            skili.put("energy", energy);

            uloziste.put(s, skili);


        }

        return uloziste;
    }

    public ArrayList<String> prisonersChoose(ArrayList<String> str) {
        ArrayList<String> result = new ArrayList<String>();
        for (int it = 0; it <= ThreadLocalRandom.current().nextInt(5, 20 + 1); it++) {


            int iml = ThreadLocalRandom.current().nextInt(1, str.size());
            if (!result.contains(str.get(iml))) {
                result.add(str.get(iml));
            } else {
                it--;
            }
        }
        return result;
    }

    public HashMap<String, Integer> nakrmDataHash(ArrayList<String> names, ArrayList<Integer> datas) {
        int i = 0;
        int i1 = 0;
        HashMap<String, Integer> hash = new HashMap<String, Integer>();
        for (String s : names) {
            i++;
            if (i > i1) {
                i1++;
                for (int i2 : datas) {
                    hash.put(s, i2);
                }

            }
        }
        return hash;
    }


}

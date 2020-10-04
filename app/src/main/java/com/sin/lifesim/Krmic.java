package com.sin.lifesim;

import com.sin.lifesim.entity.EntityRender;
import com.sin.lifesim.interfaces.stream;
import com.sin.lifesim.work.smlouva.Smlouva;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Krmic implements Serializable {
    private int hp;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }



    public static int[] poleConverter(String[] o) {
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

    public static String[] poleConverter(Object[] o) {
        ArrayList<String> s = new ArrayList<String>();
        for (Object o1 : o) {
            s.add(String.valueOf(o1));
        }
        String[] s2 = new String[s.size()];
        s2 = s.toArray(s2);
        return s2;
    }

    @SuppressWarnings({"UseBulkOperation", "ManualArrayToCollectionCopy"})
    public static ArrayList<String> polePut(@NotNull String[] pole) {
        ArrayList<String> nams = new ArrayList<String>();

        for (String s : pole) {
            nams.add(s);
        }
        return nams;
    }

    public static Object objectSaveHandler(stream klop, Object write) throws IOException {

        if (klop.input() != null) {
            try {
                ObjectInputStream ois = new ObjectInputStream(klop.input());
                return ois.readObject();
            } catch (ClassNotFoundException e) {
                System.exit(1);
            }
        } else if (klop.output() != null && write != null) {
            ObjectOutputStream oos = new ObjectOutputStream(klop.output());

            oos.writeObject(write);
            return true;
        } else throw new IOException();
        return false;
    }

    Set<String> smlouvasGetTitles(@NotNull HashMap<Smlouva, Boolean> smlouvas) {

        Set<Smlouva> entry;
        entry = smlouvas.keySet();
        HashMap<String, Integer> strings = new HashMap<>();
        for (Smlouva smlouva : entry) {

            strings.put(smlouva.getTitle(), 1);
        }

        return strings.keySet();
    }

    Set<String> smlouvasGetPodminky(HashMap<Smlouva, Boolean> smlouvas) {
        HashMap<String, Integer> strings = new HashMap<>();
        Set<Smlouva> entry;
        entry = smlouvas.keySet();

        for (Smlouva smlouva : entry) {

            strings.put(String.valueOf(smlouva.getPodminky()), 1);
        }
        return strings.keySet();

    }

    Set<String> smlouvasGetZkusenost(HashMap<Smlouva, Boolean> smlouvas) {
        Set<Smlouva> entry;
        entry = smlouvas.keySet();
        HashMap<String, Integer> strings = new HashMap<>();
        for (Smlouva smlouva : entry) {

            strings.put(String.valueOf(smlouva.getZkusenost()), 1);
        }
        return strings.keySet();

    }

    Set<String> smlouvaGetBooleans(HashMap<Smlouva, Boolean> smlouvas) {
        Collection<Boolean> entry;
        entry = smlouvas.values();
        HashMap<String, Integer> ret = new HashMap<>();
        for (Boolean b : entry) {
            ret.put(Boolean.toString(b), 1);
        }

        return ret.keySet();
    }

    ArrayList<Boolean> booleanconverter(Set<String> entry) {
        ArrayList<Boolean> ret = new ArrayList<>();
        for (String s : entry) {
            ret.add(Boolean.getBoolean(s));
        }
        return ret;
    }

    public static ArrayList<Integer> polePut(int[] pole) {
        ArrayList<Integer> nams = new ArrayList<Integer>();
        for (int s : pole) {
            nams.add(s);
        }
        return nams;
    }

    @SuppressWarnings({"ManualArrayToCollectionCopy", "UseBulkOperation"})
    public static ArrayList<Object> polePut(Object[] pole) {
        ArrayList<Object> nams = new ArrayList<Object>();
        for (Object o : pole) {
            nams.add(o);
        }
        return nams;
    }

    public static Object[] polepull(@SuppressWarnings("rawtypes") @NotNull ArrayList plou) {

        Object[] stockArr = new Object[plou.size()];
        stockArr = plou.toArray(stockArr);
        return stockArr;


    }

    public ArrayList<Prisoner> createPrisoners(ArrayList<String> names) {


        ArrayList<Prisoner> uloziste = new ArrayList<>();
        for (int i = ThreadLocalRandom.current().nextInt(2, Prison.nams.length); i != 0; i--) {


            hp = (ThreadLocalRandom.current().nextInt(1, 100 + 1));
            String name = Prison.nams[ThreadLocalRandom.current().nextInt(0, Prison.nams.length)];
            Prisoner prisoner = new Prisoner(name, ThreadLocalRandom.current().nextInt(0, 100));
            EntityRender render = new EntityRender();
            render.renderHp(hp, "set", prisoner);
            uloziste.add(prisoner);


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

package com.sin.lifesim.work.smlouva.podminka;

import com.sin.lifesim.Krmic;
import com.sin.lifesim.work.smlouva.Smlouva;
import com.sin.lifesim.work.work;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("unused")
public class podminka implements Serializable {
    String[] podminkyLow = {"free work time", "many promotions"};
    String[] podminkyMedium = {"4 work hours", "promotion avaible"};
    String[] podminkyHard = {"8 work hours", "noPromotion"};
    ArrayList<String> strings;
    ArrayList<String> strings1;
    ArrayList<String> strings2;
    Krmic k = new Krmic();

    public void setWorktime(int worktime) {
        this.worktime = worktime;
    }

    public int getWorktime() {
        return worktime;
    }

    private int worktime;

    public podminka() {
        strings = k.polePut(podminkyHard);
        strings1 = k.polePut(podminkyLow);
        strings2 = k.polePut(podminkyMedium);
    }

    public String[] generate(int low, int medium, int hard) {
        ArrayList<String> ret = new ArrayList<String>();
        if (low < strings1.size() + 1) {
            for (; low == 0; ) {
                int i = ThreadLocalRandom.current().nextInt(0, strings1.size());
                if (ret.contains(strings1.get(i))) {
                    ret.remove(strings1.get(i));
                    low++;
                }
                ret.add(strings1.get(i));
                low--;

            }
        } else throw new podminkaError("podminka generate problem");
        if (low < strings2.size() + 1) {
            for (; medium == 0; ) {
                int i = ThreadLocalRandom.current().nextInt(0, strings2.size());
                if (ret.contains(strings2.get(i))) {
                    ret.remove(strings2.get(i));
                    medium++;
                }
                ret.add(strings2.get(i));
                medium--;

            }
        } else throw new podminkaError("podminka generate problem");
        if (low < strings.size() + 1) {
            for (; hard == 0; ) {
                int i = ThreadLocalRandom.current().nextInt(0, strings.size());
                if (ret.contains(strings.get(i))) {
                    ret.remove(strings.get(i));
                    hard++;
                }
                ret.add(strings.get(i));
                hard--;

            }
        } else throw new podminkaError("podminka generate problem");
        return k.poleConverter(k.polepull(ret));
    }

    public void test(String[] data, work work, Smlouva smlouva) {
        HashMap<Smlouva, Boolean> hash = work.getSmlouvyHistorie();
        for (String s : data) {
            switch (s) {
                case "4 work hours":
                    if (worktime < 4) {
                        work.setZamestnani("");
                        hash.remove(smlouva);
                        hash.put(smlouva, false);
                    }
                    break;
                case "8 work hours":
                    if (worktime < 8) {
                        work.setZamestnani("");
                        hash.remove(smlouva);
                        hash.put(smlouva, false);
                    }
                    break;
            }
        }
    }


}

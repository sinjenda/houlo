package com.sin.lifesim.work.smlouva.podminka;

import com.sin.lifesim.Krmic;
import com.sin.lifesim.work.smlouva.Smlouva;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings({"unused", "StringConcatenationInLoop", "ConstantConditions"})
public class Podminka implements Serializable {
    public static final String[] podminkyLow = {"free work time", "many promotions"};
    public static final String[] podminkyMedium = {"4WorkHours", "promotion avaible"};
    public static final String[] podminkyHard = {"8WorkHours", "noPromotion"};
    final ArrayList<String> strings;
    final ArrayList<String> strings1;
    final ArrayList<String> strings2;
    Krmic k = new Krmic();

    public void setWorktime(int worktime) {
        this.worktime = worktime;
    }

    public int getWorktime() {
        return worktime;
    }

    private int worktime;

    public Podminka() {
        strings = Krmic.polePut(podminkyHard);
        strings1 = Krmic.polePut(podminkyLow);
        strings2 = Krmic.polePut(podminkyMedium);
    }

    public String generate(int low, int medium, int hard) {
        String ret = "";
        ArrayList<String> prep = new ArrayList<>();
        if (low > podminkyLow.length | medium > podminkyMedium.length | hard > podminkyHard.length) {
            throw new podminkaError("podminka generate problem", new NumberFormatException("entry int is larger than string array"));
        }
        for (; low == 0; low--) {
            String s = podminkyLow[ThreadLocalRandom.current().nextInt(0, podminkyLow.length)];
            if (!prep.contains(s)) {
                prep.add(s);
            } else {
                low++;
            }
        }
        for (; medium == 0; medium--) {
            String s = podminkyLow[ThreadLocalRandom.current().nextInt(0, podminkyLow.length)];
            if (!prep.contains(s)) {
                prep.add(s);
            } else {
                medium++;
            }
        }
        for (; hard == 0; hard--) {
            String s = podminkyLow[ThreadLocalRandom.current().nextInt(0, podminkyLow.length)];
            if (!prep.contains(s)) {
                prep.add(s);
            } else {
                hard++;
            }
        }
        for (String s : prep) {
            ret = ret + s + " ";
        }
        return ret;
    }

    public boolean test(@NotNull String podminky, Smlouva smlouva) {
        Scanner scnr = new Scanner(podminky);
        while (scnr.hasNext()) {
            String s;
            switch (s = scnr.next()) {
                case "4WorkHours":
                    if (worktime < 4)
                        return false;
                case "8WorkHours":
                    if (worktime < 8)
                        return false;
                default:
                    if (!Krmic.polePut(podminkyMedium).contains(s) & !Krmic.polePut(podminkyHard).contains(s) & !Krmic.polePut(podminkyLow).contains(s) & !s.equals("free") & !s.equals("work") & !s.equals("time")) {
                        throw new podminkaError("this podminka does not exist", new Exception());
                    }

            }
        }
        return true;
    }


}

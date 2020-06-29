package com.sin.lifesim.work.smlouva.podminka;

import com.sin.lifesim.Krmic;
import com.sin.lifesim.work.smlouva.Smlouva;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings({"unused", "StringConcatenationInLoop"})
public class podminka {
    public static String[] podminkyLow = {"free work time", "many promotions"};
    public static String[] podminkyMedium = {"4WorkHours", "promotion avaible"};
    public static String[] podminkyHard = {"8WorkHours", "noPromotion"};
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
                    if (!k.polePut(podminkyMedium).contains(s) | !k.polePut(podminkyHard).contains(s) | !k.polePut(podminkyLow).contains(s)) {
                        throw new podminkaError("this podminka does not exist", new Exception());
                    }

            }
        }
        return true;
    }


}

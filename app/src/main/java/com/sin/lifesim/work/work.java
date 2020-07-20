package com.sin.lifesim.work;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;

import androidx.annotation.StringRes;

import com.sin.lifesim.Krmic;
import com.sin.lifesim.MainActivity;
import com.sin.lifesim.R;
import com.sin.lifesim.work.smlouva.Smlouva;
import com.sin.lifesim.work.smlouva.display_smlouva_activity;

import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings({"unused"})
public class work {
    Krmic k = new Krmic();

    public ArrayList<Smlouva> getSmlouvy() {
        return smlouvy;
    }

    ArrayList<Smlouva> smlouvy = new ArrayList<Smlouva>();
    Smlouva smlouva;

    public HashMap<Smlouva, Boolean> getSmlouvyHistorie() {
        return smlouvyHistorie;
    }

    String[] mista = {"garbage", "janitor"};
    int[] ints = {15, 20};

    public void setSmlouvyHistorie(HashMap<Smlouva, Boolean> smlouvyHistorie) {
        this.smlouvyHistorie = smlouvyHistorie;
    }

    HashMap<Smlouva, Boolean> smlouvyHistorie = new HashMap<Smlouva, Boolean>();

    public String getZamestnani() {
        return zamestnani;
    }

    public void setZamestnani(String zamestnani) {
        this.zamestnani = zamestnani;
    }

    String zamestnani;
    MainActivity m;

    public work(MainActivity m, Context context) {
        this.m = m;


    }


    public void first() {

        String[] strings = {"free work time", "15 crowns per hour", "no promotion avaible"};
        Smlouva garbage = new Smlouva(getStringByIdName(m.getApplicationContext(), R.string.collector), strings[0] + strings[1] + strings[2], 0, m);
        smlouvy.add(garbage);
        smlouvyHistorie.put(garbage, false);
    }

    public void apply() {

        //noinspection SuspiciousMethodCalls
        smlouvy.remove(zamestnani);

        if (smlouvy.size() < 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(m);
            builder.setTitle("now there is not any contract");
            builder.setNeutralButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.out.println("hvn");
                }
            });
            android.app.AlertDialog alertDialogObject = builder.create();
            alertDialogObject.show();
        } else {
            Intent i = new Intent(m, display_smlouva_activity.class);
            i.putExtra(display_smlouva_activity.SMLOUVA, smlouvy);
            m.startActivity(i);
        }
    }


    private void garbageCollector(int hours) {

        for (int i = hours; i == 0; i--) {
            m.money = m.money + 15;
        }
    }


    public static String getStringByIdName(Context context, @StringRes int idName) {
        Resources res = context.getResources();
        return context.getResources().getString(idName);
    }

    public void goWork(int time) {
        if (Krmic.polePut(mista).contains(zamestnani)) {
            for (Smlouva s : smlouvy) {
                if (s.getTitle().equals(zamestnani)) {
                    s.send(time);
                }
            }
        }
        switch (zamestnani) {
            case "garbage":
                garbageCollector(time);
            case "janitor":

        }
    }

    public void test(Smlouva smlouva) {
        if (!smlouva.test(smlouva)) {
            HashMap<Smlouva, Boolean> ret;
            ret = getSmlouvyHistorie();
            ret.remove(smlouva);
            ret.put(smlouva, false);
            setSmlouvyHistorie(ret);
        }
    }
}

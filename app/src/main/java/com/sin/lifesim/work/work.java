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
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings({"unused"})
public class work {
    Krmic k = new Krmic();
    HashMap<String, Integer> zkusenost = new HashMap<>();
    public ArrayList<Smlouva> getSmlouvy() {
        return smlouvyForApply;
    }

    final ArrayList<Smlouva> smlouvyForApply = new ArrayList<Smlouva>();
    Smlouva smlouva;
    public int worked = 0;
    public HashMap<Smlouva, Boolean> getSmlouvyHistorie() {
        return smlouvyHistorie;
    }

    final static String[] mista = {"garbage", "janitor"};
    static int[] money = {15, 20};
    int[] zkusenosti = {0, 2};
    int[] zkusenostiGet = {1, 1};
    String[] zkusenostiTyp = {"uklid", "uklid"};

    private static class typy {
        public String[] typy = {"uklid"};
    }

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
    final MainActivity m;

    public work(MainActivity m, Context context) {
        this.m = m;


    }

    @SuppressWarnings("ConstantConditions")
    public Smlouva generateSmlouvaForApply() {
        int i1 = ThreadLocalRandom.current().nextInt(0, mista.length - 1);
        if (zkusenosti[i1] >= zkusenost.get("uklid"))
            return new Smlouva(mista[i1], zkusenosti[i1]);
        return null;
    }

    public void first() {

        String[] strings = {"free work time", "15 crowns per hour", "no promotion avaible"};
        Smlouva garbage = new Smlouva(getStringByIdName(m.getApplicationContext(), R.string.collector), strings[0] + strings[1] + strings[2], 0, m);
        smlouvyHistorie.put(garbage, true);
        zkusenost.put("uklid", 0);
    }

    public void apply() {

        //noinspection SuspiciousMethodCalls
        smlouvyForApply.remove(zamestnani);

        if (smlouvyForApply.size() < 1) {
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
            i.putExtra(display_smlouva_activity.SMLOUVA, smlouvyForApply);
            m.startActivity(i);
        }
    }


    public static String getStringByIdName(Context context, @StringRes int idName) {
        Resources res = context.getResources();
        return context.getResources().getString(idName);
    }

    @SuppressWarnings("ConstantConditions")
    public void goWork(int time) {
        if (Krmic.polePut(mista).contains(zamestnani)) {
            for (Smlouva s : smlouvyHistorie.keySet()) {
                if (s.getTitle().equals(zamestnani)) {
                    s.send(time);
                }
            }
            for (int i = time; i == 0; i--)
                m.money = m.money + money[Krmic.polePut(mista).indexOf(zamestnani)];
            worked = worked + time;
            if (worked > 24) {
                worked = worked - 24;
                zkusenost.replace(zkusenostiTyp[Krmic.polePut(mista).indexOf(zamestnani)], zkusenost.get(zkusenostiTyp[Krmic.polePut(mista).indexOf(zamestnani)]) + 1);
            }

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

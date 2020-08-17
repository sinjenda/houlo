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
import com.sin.lifesim.stream;
import com.sin.lifesim.work.smlouva.Smlouva;
import com.sin.lifesim.work.smlouva.display_smlouva_activity;
import com.sin.lifesim.work.smlouva.podminka.Podminka;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings({"unused", "unchecked"})
public class work {
    Krmic k = new Krmic();
    public static final String PATH = "storage/emulated/0/zkusenost";
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

    //zamestnani zacatek
    final static String[] mista = {"garbage", "janitor"};
    static int[] money = {15, 20};
    int[] zkusenosti = {0, 2};
    int[] zkusenostiGet = {1, 1};
    String[] zkusenostiTyp = {"uklid", "uklid"};
    //zamestnani konec

    TypZamestnani[] typy = {new uklid()};


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

        String[] strings = {Podminka.podminkyLow[0], Podminka.podminkyHard[1]};
        Smlouva garbage = new Smlouva(getStringByIdName(m.getApplicationContext(), R.string.collector), strings[0] + " " + strings[1], 0, m);
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
                m.editor.putInt("worked", worked);
                try {
                    Krmic.objectSaveHandler(new stream() {
                        @Override
                        public FileInputStream input() {
                            return null;
                        }

                        @Override
                        public FileOutputStream output() throws IOException {
                            return new FileOutputStream(new File(PATH));
                        }
                    }, zkusenost);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void normal() {
        try {
            zkusenost = (HashMap<String, Integer>) Krmic.objectSaveHandler(new stream() {
                @Override
                public FileInputStream input() {
                    try {
                        return new FileInputStream(new File(PATH));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return null;
                    }
                }

                @Override
                public FileOutputStream output() {
                    return null;
                }
            }, null);
        } catch (IOException e) {
            e.printStackTrace();
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

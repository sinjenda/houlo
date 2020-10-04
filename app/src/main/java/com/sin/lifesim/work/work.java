package com.sin.lifesim.work;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;

import androidx.annotation.StringRes;

import com.sin.lifesim.Krmic;
import com.sin.lifesim.MainActivity;
import com.sin.lifesim.R;
import com.sin.lifesim.interfaces.stream;
import com.sin.lifesim.work.smlouva.Smlouva;
import com.sin.lifesim.work.smlouva.display_smlouva_activity;
import com.sin.lifesim.work.smlouva.podminka.Podminka;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import static android.content.ContentValues.TAG;

@SuppressWarnings({"unchecked", "ConstantConditions"})
public class work {
    Krmic k = new Krmic();
    public static final String PATH = "storage/emulated/0/zkusenost";
    public static final String applyPath = "storage/emulated/0/smlouvyApply";
    public static final Zamestnani[] mistaPrepare = {new Zamestnani("SupermarketJanitor", 10, 0, "uklid", null)};
    ArrayList<Zamestnani> mista = new ArrayList<>();
    HashMap<String, Integer> zkusenost = new HashMap<>();

    public ArrayList<Smlouva> getSmlouvyForApply() {
        return smlouvyForApply;
    }

    ArrayList<Smlouva> smlouvyForApply = new ArrayList<Smlouva>();
    Smlouva smlouva;
    public int worked = 0;

    public HashMap<Smlouva, Boolean> getSmlouvyHistorie() {
        return smlouvyHistorie;
    }

    public void setSmlouvyForApply(ArrayList<Smlouva> array) {
        this.smlouvyForApply = array;
    }




    //potomci konec
    public void setSmlouvyHistorie(HashMap<Smlouva, Boolean> smlouvyHistorie) {
        this.smlouvyHistorie = smlouvyHistorie;
    }


    HashMap<Smlouva, Boolean> smlouvyHistorie = new HashMap<Smlouva, Boolean>();

    public Zamestnani getZamestnani() {
        return zamestnani;
    }

    public void setZamestnani(Zamestnani zamestnani) {
        this.zamestnani = zamestnani;
    }

    // TODO: 04.10.2020 put zamestnani to database
    Zamestnani zamestnani;
    final MainActivity m;

    public work(MainActivity m, Context context) {
        this.m = m;
        mista.addAll(Arrays.asList(mistaPrepare));

    }

    @SuppressWarnings("ConstantConditions")
    public Smlouva generateSmlouvaForApply() {
        int i1 = ThreadLocalRandom.current().nextInt(0, mista.size() - 1);
        Zamestnani zamestnani = mista.get(i1);
        if (m.schools.contains(zamestnani.school)) {
            if (zamestnani.requiredKnowledge >= zkusenost.get(zamestnani.type)) {
                return new Smlouva(zamestnani.name, zamestnani.requiredKnowledge);
            }
        }
        Log.i(TAG, "generateSmlouvaForApply: conditions doesn't met");
        return null;
    }

    public void first() throws IOException {
        String[] strings = {Podminka.podminkyLow[0], Podminka.podminkyHard[1]};
        Smlouva garbage = new Smlouva(getStringByIdName(m.getApplicationContext(), R.string.collector), strings[0] + " " + strings[1], 0, m);
        smlouvyHistorie.put(garbage, true);
        zkusenost.put("uklid", 0);
        zamestnani = mista.get(0);
        saveZkusenost();
        Krmic.objectSaveHandler(new stream() {
            @Override
            public FileInputStream input() {
                return null;
            }

            @Override
            public FileOutputStream output() throws FileNotFoundException {
                return new FileOutputStream(work.applyPath);
            }
        }, smlouvyForApply);
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
            m.startActivityForResult(i, 0);
        }
    }


    public static String getStringByIdName(Context context, @StringRes int idName) {
        Resources res = context.getResources();
        return context.getResources().getString(idName);
    }

    @SuppressWarnings("ConstantConditions")
    public void goWork(int time) {
        for (Zamestnani zamestnani : mista) {
            if (zamestnani.name.equals(this.zamestnani.name)) {
                for (Smlouva s : smlouvyHistorie.keySet()) {
                    if (s.getTitle().equals(zamestnani.name)) {
                        s.send(time);
                    }
                }
                for (int i = time; i == 0; i--)
                    m.money = m.money + zamestnani.money;
                worked = worked + time;
                if (worked > 24) {
                    worked = worked - 24;
                    zkusenost.replace(zamestnani.type, zkusenost.get(zamestnani.type) + 1);
                    m.editor.putInt("worked", worked);
                    saveZkusenost();
                }
                return;

            }
        }
    }

    public void saveZkusenost() {
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
    public void normal() {
        try {
            Krmic.objectSaveHandler(new stream() {
                @Override
                public FileInputStream input() throws FileNotFoundException {
                    return new FileInputStream(work.applyPath);
                }

                @Override
                public FileOutputStream output() {
                    return null;
                }
            }, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

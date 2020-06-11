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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

@SuppressWarnings({"unused", "ConstantConditions", "unchecked"})
public class work implements Serializable {
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
        try {
            smlouvyCteni();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


    }

    private void smlouvyCteni() {
        File file = new File(m.getDir("data", MODE_PRIVATE), "map");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read and print an object and cast it as string
        try {
            smlouvyHistorie = (HashMap<Smlouva, Boolean>) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        // read and print an object and cast it as string


    }

    public void first() {
        Smlouva garbage = new Smlouva(getStringByIdName(m.getApplicationContext(), R.string.collector), "free work time \n 15 crowns per hour \n no promotion avaible", 0, this);
        smlouvy.add(garbage);
        smlouvyHistorie.put(garbage, true);
        smlouvyZapis();
    }

    public void apply() {
        smlouvyZapis();
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

    private void smlouvyZapis() {
        try {
            File file = new File(m.getDir("data", MODE_PRIVATE), "map");
            ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));

            // write something in the file
            oout.writeObject(smlouvyHistorie);
            oout.flush();
            oout.close();
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
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
        if (k.polePut(mista).contains(zamestnani)) {
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
}

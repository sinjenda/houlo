package com.sin.lifesim;

import android.app.AlertDialog;
import android.content.DialogInterface;

import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("CanBeFinal")
public class randomEvents {
    MainActivity m;
    Prison p;

    // TODO: new event (you find gun) <place :default>
    public randomEvents(MainActivity m) {
        this.m = m;
    }

    // TODO: repair house
    @SuppressWarnings({"UnnecessaryLocalVariable", "UnusedReturnValue"})
    public String house() {

        if (ThreadLocalRandom.current().nextInt(0, 2) == 1 + 1) {
            String s = "some one is at door";
            return s;
        }


        return "";
    }

    public void Default() {
    }

    public void prison() {
        int i = ThreadLocalRandom.current().nextInt(0, 2 + 1);
        switch (i) {
            case 1:
                a();
            case 2:
                b();
            default:
        }
    }

    private void a() {
        String[] acte = {"enter helicopter", "let it be"};
        final String[] selectedText = new String[1];
        final String[] jmena = acte;
        AlertDialog.Builder builder = new AlertDialog.Builder(m);
        builder.setTitle("helicopter come to prison");
        builder.setItems(jmena, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                selectedText[0] = jmena[item];
                String ret = selectedText[0];
                if (ret.equals("enter helicopter")) {
                    m.place = "default";
                }
            }
        });

        AlertDialog alertDialogObject = builder.create();
        alertDialogObject.show();
    }

    private void b() {
        String[] acte = {"pickup bomb", "let it be"};
        final String[] selectedText = new String[1];
        final String[] jmena = acte;
        AlertDialog.Builder builder = new AlertDialog.Builder(m);
        builder.setTitle("you found bomb");
        builder.setItems(jmena, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                selectedText[0] = jmena[item];
                String ret = selectedText[0];
                if (ret.equals("pickup bomb")) {
                    if (ThreadLocalRandom.current().nextInt(1, 8) > 4) {
                        m.itemshave.add("bomb");
                    } else {
                        p = new Prison(m);
                        p.solitary(10);
                    }
                }
            }
        });

        AlertDialog alertDialogObject = builder.create();
        alertDialogObject.show();
    }

}

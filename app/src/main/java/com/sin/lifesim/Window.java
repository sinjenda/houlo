package com.sin.lifesim;

import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Scanner;

public class Window {
    MainActivity m;
    ArrayList<Boolean> isShowed = new ArrayList<>();

    public void windowItems(final method.onmet method, final String[] items) {
        final String[] selectedText = new String[1];
        final String[] ret = new String[1];
        AlertDialog.Builder builder = new AlertDialog.Builder(m);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                selectedText[0] = items[item];
                ret[0] = selectedText[0];
                method.methoda(ret);
            }
        });

        AlertDialog alertDialogObject = builder.create();
        alertDialogObject.show();
    }

    public Window(MainActivity m) {
        this.m = m;
    }

    public void windowTwoButtons(@NotNull final method.onmet.withoutParam yesButton, @Nullable final method.onmet.withoutParam cancelButton, @NotNull String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(m);
        builder.setTitle(title);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                yesButton.methodaB();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (cancelButton != null) {
                    cancelButton.methodaB();
                }
            }
        });
        final AlertDialog alertDialogObject = builder.create();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isShowed.contains(true)) {
                    isShowed.set(1, alertDialogObject.isShowing());
                }
            }
        });
        thread.start();
        if (!isShowed.contains(true)) {
            alertDialogObject.show();
            isShowed.clear();
        }
    }

    public void windowTwoButtons() {

    }

    public void informationDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(m);
        int i = 0;
        Scanner scnr = new Scanner(message);
        while (scnr.hasNext())
            i++;

        if (i > 5) {
            builder.setMessage(message);
        } else builder.setTitle(message);
        builder.setNeutralButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        final AlertDialog alertDialog = builder.create();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isShowed.contains(true)) {
                    isShowed.set(0, alertDialog.isShowing());
                }
            }
        });
        thread.start();
        if (!isShowed.contains(true)) {
            alertDialog.show();
            isShowed.clear();
        }
    }

    public void multiChoiceWindow(final method.onmet metod, final String[] items) {
        AlertDialog.Builder builder = new AlertDialog.Builder(m);

        final boolean[] booleans1 = new boolean[items.length];
        builder.setMultiChoiceItems(items, booleans1, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                booleans1[which] = isChecked;
            }
        });
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ArrayList<String> ret = new ArrayList<>();
                for (int i = 0; i < booleans1.length; i++) {
                    boolean checked = booleans1[i];
                    if (checked) {
                        ret.add(items[i]);
                    }
                }
                String[] s = {"hvn"};
                metod.methoda(Krmic.poleConverter(Krmic.polepull(ret)));
            }
        });
        final AlertDialog alertDialog = builder.create();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isShowed.contains(true)) {
                    isShowed.set(2, alertDialog.isShowing());
                }
            }
        });
        thread.start();
        if (!isShowed.contains(true)) {
            alertDialog.show();
            isShowed.clear();
        }
    }
}
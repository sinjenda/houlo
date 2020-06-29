package com.sin.lifesim;

import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class Window {
    MainActivity m;

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
        if (title != null)
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
        AlertDialog alertDialogObject = builder.create();
        alertDialogObject.show();
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
    }
}
package com.sin.lifesim.work.smlouva;

import android.content.Context;

import com.sin.lifesim.work.smlouva.podminka.Podminka;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;


public class Smlouva implements Serializable {


    String title;
    final String podminky;
    int zkusenost;
    final Podminka podminka;
    private static Context ctx;

    public int getZkusenost() {
        return zkusenost;
    }


    public String getTitle() {
        return title;
    }


    public String getPodminky() {
        return podminky;
    }


    public Smlouva(@NotNull String title, String podminky, int zkusenost, Context ctx) {
        this.title = title;
        this.podminky = podminky;
        this.zkusenost = zkusenost;
        podminka = new Podminka();
        Smlouva.ctx = ctx;
        Smlouva s;
    }

    public Smlouva(@NotNull String title, int zkusenost) {
        podminka = new Podminka();
        podminky = podminka.generate(ThreadLocalRandom.current().nextInt(1, 2), ThreadLocalRandom.current().nextInt(1, 2), ThreadLocalRandom.current().nextInt(1, 2));
    }

    public boolean test(Smlouva smlouva) {
        return podminka.test(podminky, smlouva);
    }

    public void send(int hours) {
        podminka.setWorktime(podminka.getWorktime() + hours);
    }



}

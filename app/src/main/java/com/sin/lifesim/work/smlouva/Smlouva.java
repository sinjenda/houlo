package com.sin.lifesim.work.smlouva;

import com.sin.lifesim.work.smlouva.podminka.podminka;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class Smlouva {
    String title;
    String podminky;
    int zkusenost;
    podminka podminka;
    public int getZkusenost() {
        return zkusenost;
    }


    public String getTitle() {
        return title;
    }


    public String getPodminky() {
        return podminky;
    }


    public Smlouva(@NotNull String title, String podminky, int zkusenost) {
        this.title = title;
        this.podminky = podminky;
        this.zkusenost = zkusenost;

    }

    public Smlouva(String title, int zkusenost) {
        podminka = new podminka();
        podminky = podminka.generate(ThreadLocalRandom.current().nextInt(1, 2), ThreadLocalRandom.current().nextInt(1, 2), ThreadLocalRandom.current().nextInt(1, 2));
    }

    public boolean test(String[] data, Smlouva smlouva) {
        return podminka.test(data, smlouva);
    }

    public void send(int hours) {
        podminka.setWorktime(podminka.getWorktime() + hours);
    }
}

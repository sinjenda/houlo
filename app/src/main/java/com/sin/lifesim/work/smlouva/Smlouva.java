package com.sin.lifesim.work.smlouva;

import com.sin.lifesim.work.smlouva.podminka.podminka;
import com.sin.lifesim.work.work;

import java.io.Serializable;

public class Smlouva implements Serializable {
    String title;
    String podminky;
    int zkusenost;
    podminka podminka = new podminka();
    work work;

    public int getZkusenost() {
        return zkusenost;
    }


    public String getTitle() {
        return title;
    }


    public String getPodminky() {
        return podminky;
    }


    public Smlouva(String title, String podminky, int zkusenost, work work) {
        this.title = title;
        this.podminky = podminky;
        this.zkusenost = zkusenost;
        this.work = work;
    }

    public void send(int hours) {
        podminka.setWorktime(podminka.getWorktime() + hours);
    }
}

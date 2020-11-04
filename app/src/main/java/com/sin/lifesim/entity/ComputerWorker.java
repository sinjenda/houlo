package com.sin.lifesim.entity;

import com.sin.lifesim.Item.computer.Computer;

import java.util.concurrent.ThreadLocalRandom;

public class ComputerWorker extends Entity {
    Computer computer;
    public final String[] datas = {"pin", "card", "personalInformation"};

    public ComputerWorker(String name) {
        super(name, 20);
    }

    public void linkComputer(Computer computer) {
        this.computer = computer;
        for (int count = ThreadLocalRandom.current().nextInt(0, 10); count != 0; count--) {
            this.computer.data.add(datas[ThreadLocalRandom.current().nextInt(0, datas.length - 1)]);
        }
    }
}

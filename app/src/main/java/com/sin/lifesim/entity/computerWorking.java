package com.sin.lifesim.entity;

import com.sin.lifesim.Item.computer.Computer;
import com.sin.lifesim.Item.computer.ComputerComponent;
import com.sin.lifesim.MainActivity;
import com.sin.lifesim.Prison;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("CollectionAddAllCanBeReplacedWithConstructor")
public class computerWorking {
    public ComputerComponent[] components = {new ComputerComponent("cpu", 1, 2), new ComputerComponent("ram", 1, 2), new ComputerComponent("screen", 1, 2), new ComputerComponent("keyboard", 1, 2), new ComputerComponent("mouse", 1, 2)};
    public ArrayList<ComputerWorker> workers = new ArrayList<>();

    computerWorking(MainActivity m) {
        ArrayList<ComputerComponent> computerComponents = new ArrayList<>();
        computerComponents.addAll(Arrays.asList(components));

        for (int i = 20; i != 0; i--) {
            ComputerWorker worker = new ComputerWorker(Prison.nams[ThreadLocalRandom.current().nextInt(0, Prison.nams.length - 1)]);
            m.render.renderEntity(worker);
            Computer computer = new Computer(computerComponents, worker, m);
            worker.linkComputer(computer);
            workers.add(worker);
        }
    }
}

package com.sin.lifesim.Item.computer;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings({"CloneDoesntDeclareCloneNotSupportedException", "MethodDoesntCallSuperMethod"})
public class Computer {
    HashMap<String, Integer> data = new HashMap<>();
    ArrayList<ComputerComponent> components = new ArrayList<>();
    boolean started = false;
    boolean build = false;

    public boolean start() {
        if (!started) {
            if (build) {
                started = true;
                return true;
            } else return false;
        }
        throw new ComputerError("computer is already started");
    }

    Computer(ArrayList<ComputerComponent> components) {
        boolean cpu = false;
        boolean ram = false;
        boolean screen = false;
        boolean keyboard = false;
        boolean mouse = false;
        ArrayList<String> componentNames = new ArrayList<>();
        for (ComputerComponent component : components) {
            componentNames.add(component.name);
        }
        for (String s : componentNames) {
            if (s.equals("screen")) {
                screen = true;
            }
            if (s.equals("cpu")) {
                cpu = true;
            }
            if (s.equals("ram")) {
                ram = true;
            }
            if (s.equals("mouse")) {
                mouse = true;
            }
            if (s.equals("keyboard")) {
                keyboard = true;
            }
        }
        if (cpu & ram & screen & keyboard & mouse) {
            cpu = false;
            ram = false;
            screen = false;
            keyboard = false;
            mouse = false;
            for (ComputerComponent comp : components) {
                switch (comp.name) {
                    case "cpu":
                        if (!cpu) {
                            components.remove(comp);
                            cpu = true;
                        }
                    case "ram":
                        if (!ram) {
                            components.remove(comp);
                            ram = true;
                        }
                    case "screen":
                        if (!screen) {
                            components.remove(comp);
                            screen = true;
                        }
                    case "keyboard":
                        if (!keyboard) {
                            components.remove(comp);
                            keyboard = true;
                        }
                    case "mouse":
                        if (!mouse) {
                            components.remove(comp);
                            mouse = true;
                        }
                }
            }
            this.components = components;
            build = true;
        }
    }

    @NonNull
    @Override
    protected Object clone() {
        Computer c = new Computer(components);
        if (c.build = false) {
            return "you need more components to clone your pc";
        } else {
            return c;
        }
    }

    public static class ComputerError extends RuntimeException {
        public ComputerError() {
            super();
        }

        public ComputerError(String message) {
            super(message);
        }

        public ComputerError(String message, Throwable cause) {
            super(message, cause);
        }

        public ComputerError(Throwable cause) {
            super(cause);
        }
    }
}

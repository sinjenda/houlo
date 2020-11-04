package com.sin.lifesim.Item.computer;

import androidx.annotation.NonNull;

import com.sin.lifesim.MainActivity;
import com.sin.lifesim.entity.Entity;
import com.sin.lifesim.entity.EntityRender;

import java.util.ArrayList;

@SuppressWarnings({"CloneDoesntDeclareCloneNotSupportedException", "MethodDoesntCallSuperMethod"})
public class Computer {
    public ArrayList<String> data = new ArrayList<>();
    private static MainActivity m;
    ArrayList<ComputerComponent> components = new ArrayList<>();
    public ArrayList<ComputerComponent> usedComponents = new ArrayList<>();
    boolean started = false;
    public Entity owner;
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

    public boolean insertComponent(ComputerComponent component) {
        switch (component.name) {
            case "net":
                components.add(component);
                return true;
            default:
                return false;
        }
    }

    public Computer(ArrayList<ComputerComponent> components, Entity owner, MainActivity m) {
        Computer.m = m;
        try {
            m.render.renderedTest(owner);

        } catch (EntityRender.EntityError e) {
            throw new ComputerError("notRendered", e);
        }
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
                            usedComponents.add(comp);
                        }
                    case "ram":
                        if (!ram) {
                            components.remove(comp);
                            ram = true;
                            usedComponents.add(comp);
                        }
                    case "screen":
                        if (!screen) {
                            components.remove(comp);
                            screen = true;
                            usedComponents.add(comp);
                        }
                    case "keyboard":
                        if (!keyboard) {
                            components.remove(comp);
                            keyboard = true;
                            usedComponents.add(comp);
                        }
                    case "mouse":
                        if (!mouse) {
                            components.remove(comp);
                            mouse = true;
                            usedComponents.add(comp);
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
        Computer c = new Computer(components, owner, m);
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

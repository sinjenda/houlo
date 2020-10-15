package com.sin.lifesim.work;

import com.sin.lifesim.Krmic;
import com.sin.lifesim.interfaces.stream;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Zamestnani {
    public static final String[] posts = {"jr", "sr", "manager", "boss"};
    public String name;
    int money;
    public saver saver = new saver();
    int requiredKnowledge;
    String type;
    @Nullable
    String school;

    private Zamestnani getZamestnani() {
        return this;
    }

    public Zamestnani(String name, int money, int requiredKnowledge, String type, @Nullable String school) {
        this.name = name;
        this.money = money;
        this.requiredKnowledge = requiredKnowledge;
        this.type = type;
        this.school = school;
    }

    public class saver {
        public void save() {
            try {
                Krmic.objectSaveHandler(new stream() {
                    @Override
                    public FileInputStream input() {
                        return null;
                    }

                    @Override
                    public FileOutputStream output() throws IOException {
                        return new FileOutputStream(new File("storage/emulated/0/lifesim/zamestnani"));
                    }
                }, getZamestnani());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void load(work w) {
            try {
                w.setZamestnani((Zamestnani) Krmic.objectSaveHandler(new stream() {
                    @Override
                    public FileInputStream input() throws FileNotFoundException {
                        return new FileInputStream(new File("storage/emulated/0/lifesim/zamestnani"));
                    }

                    @Override
                    public FileOutputStream output() {
                        return null;
                    }
                }, null));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

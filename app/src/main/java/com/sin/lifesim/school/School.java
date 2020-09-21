package com.sin.lifesim.school;

import com.sin.lifesim.Krmic;
import com.sin.lifesim.MainActivity;
import com.sin.lifesim.Window;
import com.sin.lifesim.method;
import com.sin.lifesim.school.schools.KomensSchool;
import com.sin.lifesim.school.schools.PragueGymnasiumSchool;
import com.sin.lifesim.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public abstract class School implements Serializable {
    protected String name;
    protected int StudyTime;
    protected int[] subjects;
    protected int studied;

    protected static class types {
        public static final int lowSchool = 1;
        public static final int mediumSchool = 2;
        public static final int highSchool = 3;
        public static final int university = 4;
        public static int choose;
    }

    public static String generate(MainActivity m) {
        if (m.schools.size() == 0) {
            m.school = new KomensSchool(m);
            return "komens";
        } else if (m.schools.contains("low")) {
            m.school = new PragueGymnasiumSchool(m);
            return "pragueGymnasium";
        }
        throw new UnsupportedOperationException("error occurred");
    }

    public void study(final MainActivity m) {
        Window w = new Window(m);
        w.windowItems(new method.onmet() {
            @Override
            public void methoda(String[] string) {
                int i = Integer.parseInt(string[0]);
                for (; i != 0; i--) {
                    studied = studied + 5;
                }
                m.saveSchool();
                Window w = new Window(m);

                w.informationDialog("you need to study" + " " + ((StudyTime - studied) / 5) + " " + "hours");
                if (studied > StudyTime) {
                    m.SchoolName = null;
                    m.school = null;
                    //schools
                    switch (name) {
                        case "komens":
                            m.schools.add("low");
                        case "prague":
                            m.schools.add("gymnasium");
                    }

                    try {
                        Krmic.objectSaveHandler(new stream() {
                            @Override
                            public FileInputStream input() throws FileNotFoundException {
                                return new FileInputStream(new File(MainActivity.PATH_SCHOOLS));
                            }

                            @Override
                            public FileOutputStream output() {
                                return null;
                            }
                        }, m.schools);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"});
    }
    public static class subjects {
        public static final int math = 1;

        public static class languages extends subjects {
            public static final int czech = 2;
            public static final int english = 3;
            public static final int french = 4;
            public static final int german = 5;
            public static final int spanish = 6;
        }

        public static final int physics = 7;
        public static final int nature = 8;
        public static final int history = 9;
        public static final int max = 9;
    }


}
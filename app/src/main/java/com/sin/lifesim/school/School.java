package com.sin.lifesim.school;

import com.sin.lifesim.MainActivity;
import com.sin.lifesim.school.schools.KomensSchool;
import com.sin.lifesim.school.schools.PragueGymnasiumSchool;

public abstract class School {
    protected String name;
    protected int StudyTime;
    protected types type;
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
            m.school = new PragueGymnasiumSchool();
            return "pragueGymnasium";
        }
        throw new UnsupportedOperationException("do not use this");
    }


    protected static class subjects {
        public static final int math = 1;

        public static class languages {
            public static final int czech = 2;
            public static final int english = 3;
            public static final int french = 4;
            public static final int german = 5;
        }

        public static final int physics = 6;
        public static final int nature = 7;
        public static final int history = 8;

    }

}
package com.sin.lifesim.school.schools;

import com.sin.lifesim.MainActivity;
import com.sin.lifesim.Window;
import com.sin.lifesim.method;
import com.sin.lifesim.school.School;

public class KomensSchool extends School {
    MainActivity m;

    public KomensSchool(MainActivity m) {
        super.name = "komensSchool";
        types.choose = types.lowSchool;
        subjects = new int[]{School.subjects.math, School.subjects.history, School.subjects.nature, School.subjects.languages.english};
        super.StudyTime = 45;
        this.m = m;
    }

    public void study() {
        Window w = new Window(m);
        w.windowItems(new method.onmet() {
            @Override
            public void methoda(String[] string) {
                int i = Integer.parseInt(string[0]);
                for (; i > 1; i--) {
                    studied = studied + 5;
                }
                m.saveSchool();
            }
        }, new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"});
    }
}
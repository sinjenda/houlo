package com.sin.lifesim.school.schools;

import com.sin.lifesim.MainActivity;
import com.sin.lifesim.school.School;

public class KomensSchool extends School {
    static MainActivity m;

    public KomensSchool(MainActivity m) {
        super(m.render);
        super.name = "komensSchool";
        types.choose = types.lowSchool;
        subjects = new int[]{School.subjects.math, School.subjects.history, School.subjects.nature, School.subjects.languages.english};
        super.StudyTime = 45;
        maxStudents = 40;
        KomensSchool.m = m;
    }

    public void study() {
        super.study(m);
    }
}
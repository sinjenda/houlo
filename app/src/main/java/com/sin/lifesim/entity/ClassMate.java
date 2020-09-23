package com.sin.lifesim.entity;

import android.util.Log;

import androidx.annotation.IntRange;

import com.sin.lifesim.Krmic;
import com.sin.lifesim.MainActivity;
import com.sin.lifesim.randomEvents;
import com.sin.lifesim.school.School;
import com.sin.lifesim.school.schools.classError;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static android.content.ContentValues.TAG;

public class ClassMate extends Entity {
    randomEvents events;
    public int[] goodSubjects;
    public int[] badSubjects;
    @IntRange(from = 0, to = 100)
    public int agresivity;


    @Override
    public randomEvents createRandomEvents(MainActivity ctx) {
        events = new randomEvents(ctx);
        return events;
    }

    private void createSubjects(@Nullable int[] goodSubjects, @Nullable int[] badSubjects) throws classError {
        if (goodSubjects != null) {
            this.goodSubjects = goodSubjects;
        } else
            this.goodSubjects = createSubjects(ThreadLocalRandom.current().nextInt(0, School.subjects.max));
        if (badSubjects != null) {
            this.badSubjects = badSubjects;
        } else {

            boolean test;
            do {
                test = false;
                this.goodSubjects = createSubjects(ThreadLocalRandom.current().nextInt(0, School.subjects.max));
                for (int i1 : this.goodSubjects) {
                    if (Krmic.polePut(this.badSubjects).contains(i1))
                        test = true;
                }
            } while (test);


        }
    }

    private int[] createSubjects(int count) throws classError {
        if (count > School.subjects.max) {
            throw new classError("count is larger than school subjects");
        }
        ArrayList<Integer> ints = new ArrayList<>();
        for (; count != 0; count--) {
            int i = ThreadLocalRandom.current().nextInt(0, School.subjects.max);
            if (!ints.contains(i)) {
                ints.add(i);
            } else {
                count++;
                Log.i(TAG, "createSubjects: this int is in ints starting round again");
            }
        }
        return Krmic.poleConverter(Krmic.poleConverter(Krmic.polepull(ints)));
    }

    ClassMate() throws classError {
        setName(null);
        createSubjects(null, null);
    }

    private ClassMate(String name, int agresivity) {
        this(agresivity);
        setName(name);
    }

    ClassMate(@Nullable int[] goodSubjects, @Nullable String name, @Nullable int[] badSubjects, int agresivity, int inteligence) throws classError {
        this(name, agresivity);
        effects = new ArrayList<>();
        setInteligence(inteligence);
        createSubjects(goodSubjects, badSubjects);
    }

    private ClassMate(String name) {
        setName(name);
    }

    ClassMate(int agresivity) {
        if (agresivity >= 0) {
            this.agresivity = agresivity;
        }
    }


}

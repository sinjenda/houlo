package com.sin.lifesim.entity;

import android.util.Log;

import com.sin.lifesim.Krmic;
import com.sin.lifesim.MainActivity;
import com.sin.lifesim.randomEvents;
import com.sin.lifesim.school.School;
import com.sin.lifesim.school.schools.classError;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static android.content.ContentValues.TAG;

public class ClassMate extends entity {
    randomEvents events;
    public int[] goodSubjects;

    @Override
    public randomEvents createRandomEvents(MainActivity ctx) {
        events = new randomEvents(ctx);
        return events;
    }

    private void createSubjects(@Nullable int[] subjects) throws classError {
        if (subjects != null) {
            goodSubjects = subjects;
        } else
            goodSubjects = createSubjects(ThreadLocalRandom.current().nextInt(0, School.subjects.max));
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

    ClassMate() {
        setName();
    }

    ClassMate(@NotNull int[] subjects) throws classError {
        setName();
        createSubjects(subjects);
    }

}

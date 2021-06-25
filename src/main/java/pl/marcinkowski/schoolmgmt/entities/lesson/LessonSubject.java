package pl.marcinkowski.schoolmgmt.entities.lesson;

import java.util.Random;

public enum LessonSubject {
    MATHS,
    ENGLISH,
    PE,
    DATABASES,
    FRONTEND,
    BACKEND,
    POLISH,
    BIOLOGY;

    public static LessonSubject random() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}

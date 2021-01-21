package com.yliu.utils;

import java.time.LocalDate;

public class ScoreUtils {

    public static Double date2Score(LocalDate date){
        long l = date.toEpochDay();
        return Double.valueOf(l);
    }

    public static LocalDate score2Date(Double score){
        long l = score.longValue();

        return LocalDate.ofEpochDay(l);
    }

}

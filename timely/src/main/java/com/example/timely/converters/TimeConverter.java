package com.example.timely.converters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeConverter {

    public static LocalDateTime convertStringToLocalDateTime(String date) {
        if (date.equals(""))
            return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

        return dateTime;
    }

    public static String convertLocalDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = localDateTime.format(formatter);
        return formatDateTime;
    }

    public static String convertSecondsToStringTimeFormat(long seconds) {
        long hours = seconds / 3600;
        seconds %= 3600;

        long minutes = seconds / 60;
        seconds %= 60;

        return hours + ":" + minutes + ":" + seconds;
    }

    public static String calculateDifferenceBetweenStartAndEnd(LocalDateTime startTime , LocalDateTime endTime){
        long secondDifference = ChronoUnit.SECONDS.between(startTime, endTime);
        return convertSecondsToStringTimeFormat(secondDifference);
    }


}

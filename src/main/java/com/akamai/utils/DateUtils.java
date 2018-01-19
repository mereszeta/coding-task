package com.akamai.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtils {
    public DateUtils() {
    }

    public long getSecondDiffFromNow(String timestamp) {
        LocalDateTime end = LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        if (end.isAfter(LocalDateTime.now())) {
            return LocalDateTime.now().until(end.plusSeconds(1), ChronoUnit.SECONDS);
        } else throw new IllegalArgumentException("Timestamp should be later than now");
    }

    public String getTimestampByBeginningAndDiff(String timestamp, int diff){
        LocalDateTime start = LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return start.plusSeconds(diff).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}

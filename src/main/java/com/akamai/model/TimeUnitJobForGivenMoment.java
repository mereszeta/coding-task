package com.akamai.model;

public class TimeUnitJobForGivenMoment {
    private TimeUnitJob timeUnitJob;
    private String timestamp;

    public TimeUnitJobForGivenMoment(TimeUnitJob timeUnitJob, String timestamp) {
        this.timeUnitJob = timeUnitJob;
        this.timestamp = timestamp;
    }

    public TimeUnitJob getTimeUnitJob() {
        return timeUnitJob;
    }

    public String getTimestamp() {
        return timestamp;
    }
}

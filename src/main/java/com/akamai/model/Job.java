package com.akamai.model;

public class Job {
    private int id;
    private int period;
    private int duration;
    private int cost;

    public Job(int id, int period, int duration, int cost) {
        this.id = id;
        this.period = period;
        this.duration = duration;
        this.cost = cost;
    }

    public Job(int[] vals) {
        if (vals.length != 4 || vals[2] > vals[1]) throw new IllegalArgumentException();
        this.id = vals[0];
        this.period = vals[1];
        this.duration = vals[2];
        this.cost = vals[3];
    }

    public int getId() {
        return id;
    }

    public int getPeriod() {
        return period;
    }

    public int getDuration() {
        return duration;
    }

    public int getCost() {
        return cost;
    }

    public TimeUnitJob toTimeUnitJob() {
        return new TimeUnitJob(this.id, this.cost);
    }
}

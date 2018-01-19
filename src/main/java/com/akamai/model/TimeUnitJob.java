package com.akamai.model;

public class TimeUnitJob {
    private int id;
    private int cost;

    public TimeUnitJob(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "TimeUnitJob{" +
                "id=" + id +
                ", cost=" + cost +
                '}';
    }
}

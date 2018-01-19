package com.akamai.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JobSchedule {
    private TimeUnit[] timeUnits;

    public JobSchedule(TimeUnit[] timeUnits) {
        this.timeUnits = timeUnits;
    }

    public List<Integer> getCostsForEveryTimeUnit() {
        return Arrays.stream(timeUnits)
                .map(TimeUnit::getCurrentCost)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (TimeUnit timeUnit : timeUnits) {
            out.append(timeUnit.toString()).append("\n");
        }
        return out.toString();
    }

    public int getTimeUnitsCount(){
        return timeUnits.length;
    }

    public int getCostForSpecificTimeUnit(int idx){
        if(idx<timeUnits.length){
            return timeUnits[idx].getCurrentCost();
        }
        else return 0;
    }

    public List<TimeUnitJob> getJobsForSpecificTimeUnit(int idx){
        if(idx<timeUnits.length){
            return timeUnits[idx].getJobsAt();
        }
        else return new ArrayList<>();
    }
}
